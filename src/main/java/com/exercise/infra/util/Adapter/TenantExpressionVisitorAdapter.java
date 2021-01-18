package com.exercise.infra.util.Adapter;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.FromItemVisitorAdapter;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SubSelect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianglong.li@hand-china.com
 * @date 2020-12-24 13:55
 **/
public class TenantExpressionVisitorAdapter extends ExpressionVisitorAdapter {

    private Logger logger= LoggerFactory.getLogger(TenantExpressionVisitorAdapter.class);
    private List<SubSelect> currentSubSelect;

    private FromItemVisitorAdapter fromItemVisitorAdapter;

    private void init() {
        currentSubSelect = new ArrayList<>(4);
        fromItemVisitorAdapter = new FromItemVisitorAdapter() {
            @Override
            public void visit(SubSelect subSelect) {
                super.visit(subSelect);
                currentSubSelect.add(subSelect);
                logger.info("子查询："+subSelect.toString());
                Statement stmt= null;
                try {
                    stmt = CCJSqlParserUtil.parse( subSelect.getSelectBody().toString());
                } catch (JSQLParserException e) {
                    e.printStackTrace();
                }
                Select select = (Select) stmt;
                select.getSelectBody().accept(new TenantSelectVisitorAdapter());
                subSelect.setSelectBody(select.getSelectBody());
            }

        };
    }

    public TenantExpressionVisitorAdapter() {
        init();
    }

    @Override
    public void visit(SubSelect subSelect) {
        super.visit(subSelect);
        currentSubSelect.add(subSelect);
        logger.info("子查询："+subSelect.toString());
        Statement stmt= null;
        try {
            stmt = CCJSqlParserUtil.parse( subSelect.getSelectBody().toString());
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        Select select = (Select) stmt;
        select.getSelectBody().accept(new TenantSelectVisitorAdapter());
        subSelect.setSelectBody(select.getSelectBody());
    }

    public FromItemVisitorAdapter getFromItemVisitorAdapter() {
        return fromItemVisitorAdapter;
    }



}
