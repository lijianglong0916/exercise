package com.exercise.infra.util.Adapter;

import net.sf.jsqlparser.statement.select.FromItemVisitorAdapter;
import net.sf.jsqlparser.statement.select.SubSelect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianglong.li@hand-china.com
 * @date 2020-12-24 13:55
 **/
public class ExpressionVisitorAdapter extends net.sf.jsqlparser.expression.ExpressionVisitorAdapter {

    private Logger logger= LoggerFactory.getLogger(ExpressionVisitorAdapter.class);
    private List<SubSelect> currentSubSelect;

    private List<SubSelect> isExpired;

    private FromItemVisitorAdapter fromItemVisitorAdapter;

    private void init() {
        currentSubSelect = new ArrayList<>(4);
        isExpired = new ArrayList<>(4);
        fromItemVisitorAdapter = new FromItemVisitorAdapter() {
            @Override
            public void visit(SubSelect subSelect) {
                super.visit(subSelect);
                currentSubSelect.add(subSelect);
                logger.info("子查询："+subSelect.toString());

            }
        };
    }

    public ExpressionVisitorAdapter() {
        init();
    }

    @Override
    public void visit(SubSelect subSelect) {
        super.visit(subSelect);
        currentSubSelect.add(subSelect);
        logger.info("子查询："+subSelect.toString());
    }

    public List<SubSelect> getCurrentSubSelect() {
        return currentSubSelect;
    }

    public FromItemVisitorAdapter getFromItemVisitorAdapter() {
        return fromItemVisitorAdapter;
    }

    public List<SubSelect> getIsExpired() {
        isExpired.clear();
        isExpired.addAll(currentSubSelect);
        return isExpired;
    }

    public void isClear(List<SubSelect> selects, boolean isClear) {
        if (isClear) {
            if (selects != null) {
                selects.clear();
            }
        }
    }
}
