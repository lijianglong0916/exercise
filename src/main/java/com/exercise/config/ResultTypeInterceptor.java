package com.exercise.config;

import com.exercise.config.annotation.InterceptAnnotation;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author jianglong.li@hand-china.com
 * @date 2021-01-11 18:13
 **/

@Intercepts({
        // @Signature(type = Executor.class, method = /* org.apache.ibatis.executor.Executor中定义的方法,参数也要对应 */"update", args = { MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultSetHandler.class})})
public class ResultTypeInterceptor implements Interceptor {
    @Override
    public Object plugin(Object target) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 方法一
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());

        //先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement

        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        //id为执行的mapper方法的全路径名，如com.uv.dao.UserMapper.insertUser

        String id = mappedStatement.getId();

        //sql语句类型 select、delete、insert、update

        String sqlCommandType = mappedStatement.getSqlCommandType().toString();

        BoundSql boundSql = statementHandler.getBoundSql();

        //获取到原始sql语句

        String sql = boundSql.getSql();

        String mSql = sql;

        //TODO 修改位置

        //注解逻辑判断  添加注解了才拦截

        Class<?> classType = Class.forName(mappedStatement.getId().substring(0, mappedStatement.getId().lastIndexOf(".")));

        String mName = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".") + 1, mappedStatement.getId().length());

        for (Method method : classType.getDeclaredMethods()) {

            if (method.isAnnotationPresent(InterceptAnnotation.class) && mName.equals(method.getName())) {

                InterceptAnnotation interceptorAnnotation = method.getAnnotation(InterceptAnnotation.class);

                if (interceptorAnnotation.flag()) {

                    mSql = sql + " limit 2";

                }

            }

        }

        //通过反射修改sql语句

        Field field = boundSql.getClass().getDeclaredField("sql");

        field.setAccessible(true);

        field.set(boundSql, mSql);

        return invocation.proceed();

    }
}
