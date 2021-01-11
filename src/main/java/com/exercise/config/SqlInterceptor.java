package com.exercise.config;

import org.apache.ibatis.plugin.Invocation;

/**
 * @author jianglong.li@hand-china.com
 * @date 2021-01-11 13:47
 **/
public interface SqlInterceptor{
    /**
     * @param invocation
     * @param sql
     * @return
     * @author:admin
     * @email:admin
     * @创建日期:2018年1月10日
     * @功能说明： 拦截查询的接口方法，通过原始的sql，判断是否含有占位符。如果没有占位符，则直接返回。否则，根据数据权限查询得到数据权限的sql替换占位符后返回。
     */
    public String doInterceptor(Invocation invocation, String sql);
}