package com.exercise.config;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author jianglong.li@hand-china.com
 * @date 2021-01-11 15:59
 **/
public class AeResultTypeHandler implements ResultSetHandler {
    @Override
    public <E> List<E> handleResultSets(Statement statement) throws SQLException {
        ResultSet resultSet = statement.getResultSet();
        List<E> resultSets = new ArrayList<>();
        PreparedStatement ps=null;
        ResultSetMetaData metaData = ps.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i=0;i<columnCount;i++){
            try {
                Field columnName = metaData.getClass().getDeclaredField("columnName");
                columnName.setAccessible(true);
                Collection o = (Collection)columnName.get(metaData);
                continue;
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            //metaData
        }
        return resultSets;
    }

    @Override
    public <E> Cursor<E> handleCursorResultSets(Statement statement) throws SQLException {
        return null;
    }

    @Override
    public void handleOutputParameters(CallableStatement callableStatement) throws SQLException {

    }
}
