package com.exercise.infra.util.Adapter;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.values.ValuesStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author jianglong.li@hand-china.com
 * @date 2020-12-24 14:41
 **/
public class SelectVisitorAdapter implements SelectVisitor {

    private ExpressionVisitorAdapter expressionVisitorAdapter;

    private Logger logger = LoggerFactory.getLogger(SelectVisitorAdapter.class);

    private List<Expression> expressions;

    private void init() {
        expressionVisitorAdapter = new ExpressionVisitorAdapter();
        expressions = new ArrayList<>(4);
    }

    public SelectVisitorAdapter() {
        init();
    }

    @Override
    public void visit(PlainSelect plainSelect) {
        if (!CollectionUtils.isEmpty(plainSelect.getJoins())) {
            plainSelect.getJoins().forEach(e -> {
                if (Objects.nonNull(e.getOnExpression())){
                    e.getOnExpression().accept(expressionVisitorAdapter);
                }
                if (Objects.nonNull(e.getRightItem())){
                    e.getRightItem().accept(expressionVisitorAdapter.getFromItemVisitorAdapter());
                }
            });
        }
        if (Objects.nonNull(plainSelect.getWhere())) {
            plainSelect.getWhere().accept(expressionVisitorAdapter);
        }
        if (Objects.nonNull(plainSelect.getFromItem())) {
            plainSelect.getFromItem().accept(expressionVisitorAdapter.getFromItemVisitorAdapter());
        }
        if (Objects.nonNull(plainSelect.getHaving())) {
            plainSelect.getHaving().accept(expressionVisitorAdapter);
        }
        if (!Objects.isNull(plainSelect.getGroupBy())) {
            if (CollectionUtils.isEmpty(plainSelect.getGroupBy().getGroupByExpressions())){
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
            plainSelect.getSelectItems().forEach(e -> e.accept(new ExpressionVisitorAdapter() {
                @Override
                public void visit(SubSelect subSelect) {
                    expressions.add(subSelect);
                    logger.info("字段子查询：" + subSelect.toString());
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

    public ExpressionVisitorAdapter getExpressionVisitorAdapter() {
        return expressionVisitorAdapter;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    @Override
    public void visit(ValuesStatement aThis) {

    }
}
