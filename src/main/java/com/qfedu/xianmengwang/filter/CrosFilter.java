package com.qfedu.xianmengwang.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 7:31
 */
public class CrosFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        response.setHeader("Access-Control-Allow-Origin","*"); //允许跨域
        response.setHeader("Access-Control-Allow-Credentials","true"); //允许跨域
        response.setHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,OPTIONS");//支持跨域的请求方式
        response.setHeader("Access-Control-Allow-Headers","content-type,votertoken");// 允许跨域的请求消息头
        response.setHeader("Access-Control-Max-Age","3600");
        filterChain.doFilter(servletRequest,response);
    }
}
