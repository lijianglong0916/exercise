package com.exercise.infra.util.Adapter;

import com.hscs.core.syscolumns.handle.domain.SysColumns;
import com.hscs.core.syscolumns.handle.impl.SysColumnsHandleImpl;
import lombok.Data;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.values.ValuesStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jianglong.li@hand-china.com
 * @date 2020-12-24 14:41
 **/
@Data
public class TenantSelectVisitorAdapter implements SelectVisitor {

    private TenantExpressionVisitorAdapter expressionVisitorAdapter;

    private Logger logger = LoggerFactory.getLogger(TenantSelectVisitorAdapter.class);

    public static ThreadLocal<SysColumnsHandleImpl> sysColumnsHandle=new ThreadLocal<>();

    public static ThreadLocal<Long> tenantId = new ThreadLocal<>();

    private static ThreadLocal<AtomicLong> increment = new ThreadLocal<>();

    private void init() {
        expressionVisitorAdapter = new TenantExpressionVisitorAdapter();
    }

    public TenantSelectVisitorAdapter() {
        init();
    }

    @Override
    public void visit(PlainSelect plainSelect) {
        if (!CollectionUtils.isEmpty(plainSelect.getJoins())) {
            plainSelect.getJoins().forEach(e -> {
                if (Objects.nonNull(e.getOnExpression())) {
                    e.getOnExpression().accept(expressionVisitorAdapter);
                }
                if (Objects.nonNull(e.getRightItem())) {
                    if (e.getRightItem() instanceof Table) {
                        if(Objects.nonNull(TenantSelectVisitorAdapter.sysColumnsHandle.get())){
                            e.setRightItem(tableAddSchema(e.getRightItem()));
                        }
                        if(Objects.nonNull(TenantSelectVisitorAdapter.tenantId.get())){
                            e.setRightItem(tableAddTenant(e.getRightItem()));
                        }
                    }
                    e.getRightItem().accept(expressionVisitorAdapter.getFromItemVisitorAdapter());
                }
            });
        }
        if (Objects.nonNull(plainSelect.getWhere())) {
            plainSelect.getWhere().accept(expressionVisitorAdapter);
        }
        if (Objects.nonNull(plainSelect.getFromItem())) {
            plainSelect.getFromItem().accept(expressionVisitorAdapter.getFromItemVisitorAdapter());
            if (plainSelect.getFromItem() instanceof Table) {
                if(Objects.nonNull(TenantSelectVisitorAdapter.sysColumnsHandle.get())){
                    plainSelect.setFromItem(tableAddSchema(plainSelect.getFromItem()));
                }
                if(Objects.nonNull(TenantSelectVisitorAdapter.tenantId.get())){
                    plainSelect.setFromItem(tableAddTenant(plainSelect.getFromItem()));
                }
            }
        }
        if (Objects.nonNull(plainSelect.getHaving())) {
            plainSelect.getHaving().accept(expressionVisitorAdapter);
        }
        if (Objects.nonNull(plainSelect.getGroupBy())) {
            if (!CollectionUtils.isEmpty(plainSelect.getGroupBy().getGroupByExpressions())) {
                plainSelect.getGroupBy().getGroupByExpressions().forEach(e -> e.accept(expressionVisitorAdapter));
            }
        }
        if (Objects.nonNull(plainSelect.getLimit())) {
            if (Objects.nonNull(plainSelect.getLimit().getOffset())) {
                plainSelect.getLimit().getOffset().accept(expressionVisitorAdapter);
            }
            if (Objects.nonNull(plainSelect.getLimit().getRowCount())) {
                plainSelect.getLimit().getRowCount().accept(expressionVisitorAdapter);
            }
        }
        if (!CollectionUtils.isEmpty(plainSelect.getSelectItems())) {
            plainSelect.getSelectItems().forEach(e -> e.accept(new TenantExpressionVisitorAdapter() {
                @Override
                public void visit(SubSelect subSelect) {
                    logger.info("字段子查询：" + subSelect.toString());
                    Statement stmt = null;
                    try {
                        stmt = CCJSqlParserUtil.parse(subSelect.getSelectBody().toString());
                    } catch (JSQLParserException e) {
                        e.printStackTrace();
                    }
                    Select select = (Select) stmt;
                    select.getSelectBody().accept(new TenantSelectVisitorAdapter());
                    subSelect.setSelectBody(select.getSelectBody());
                }
            }));
        }
    }


    @Override
    public void visit(SetOperationList setOpList) {

    }

    @Override
    public void visit(WithItem withItem) {

    }

    /**
     * 添加schema
     * @param fromItems fromItems
     * @return fromItems
     */
    private FromItem tableAddSchema(FromItem fromItems){
        Table table = (Table) fromItems;

        if(StringUtils.isEmpty(table.getSchemaName())){
            List<SysColumns> columnsList = TenantSelectVisitorAdapter.sysColumnsHandle.get().getSysColumns(null,table.getName().toLowerCase());
            if (!CollectionUtils.isEmpty(columnsList)){
                table.setSchemaName(columnsList.get(0).getTableSchema());
            }
        }

        return fromItems;
    }

    /**
     * 租户拼接
     * @param fromItems table
     * @return 拼接后的table字段
     */
    private FromItem tableAddTenant(FromItem fromItems) {
        Table table = (Table) fromItems;
        if (Objects.isNull(TenantSelectVisitorAdapter.increment.get())){
            TenantSelectVisitorAdapter.increment.set(new AtomicLong(1L));
        }
        Alias alias = new Alias(Objects.isNull(table.getAlias()) ? ("tab_"+ TenantSelectVisitorAdapter.increment.get().getAndIncrement()): table.getAlias().getName());
        table.setAlias(null);
        String newSql = "(SELECT * FROM " + table + " where tenant_id=" + TenantSelectVisitorAdapter.tenantId.get() + " )";
        FromItem fromItem = (FromItem) new Table(newSql);
        fromItem.setAlias(alias);
        return fromItem;
    }

    /**
     * 清除租户、schema调用、自增num属性
     */
    public void remove(){
        TenantSelectVisitorAdapter.tenantId.remove();
        TenantSelectVisitorAdapter.sysColumnsHandle.remove();
        TenantSelectVisitorAdapter.increment.remove();
    }

    @Override
    public void visit(ValuesStatement aThis) {

    }
}
