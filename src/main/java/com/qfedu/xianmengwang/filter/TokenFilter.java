package com.qfedu.xianmengwang.filter;

import com.alibaba.fastjson.JSON;
import com.qfedu.xianmengwang.config.RedisKeyConfig;
import com.qfedu.xianmengwang.constant.SystemConstant;
import com.qfedu.xianmengwang.util.JedisCore;
import com.qfedu.xianmengwang.util.StringUtil;
import com.qfedu.xianmengwang.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 7:35
 */
@Component
@WebFilter("/*")
public class TokenFilter implements Filter {
    @Autowired
    private JedisCore jedisCore;
    private List<String> whiteurls= Arrays.asList(
            "api/active/save",
            "api/active/myactive",
            "/api/voter/save",
            "/api/voter/voteractive",
            "/api/voter/myticklog",
            "/api/voter/freetick",
            "api/user/changepass",
            "api/user/loginout",
            "api/user/checktoken",
            "api/userlog/all",
            "api/userlog/allpage"
    );
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        //获取请求路径
        String qurl=request.getRequestURI();
        boolean ischeck=false;
        //校验访问路径 是否需要进行令牌传递
        for(String s:whiteurls){
            if(qurl.contains(s)){
                //需要校验令牌
                ischeck=true;
                break;
            }
        }
        //令牌校验
        if(ischeck){
            HttpServletResponse response=(HttpServletResponse)servletResponse;
            response.setContentType("application/json;charset=UTF-8");
            //获取令牌
            String token=request.getHeader(SystemConstant.TOKEN_HEADER);
            if(!StringUtil.checkEmpty(token)){
                //跟服务器记录比对
                if(jedisCore.checkKey(RedisKeyConfig.TOKEN_USER+token)){
                    filterChain.doFilter(request,servletResponse);
                }else {
                    response.getWriter().print(JSON.toJSONString(R.error("亲，令牌已经失效")));
                }
            }else {
                response.getWriter().print(JSON.toJSONString(R.error("亲，该接口需要令牌，才可以访问")));
            }
        }else {
            filterChain.doFilter(request,servletResponse);
        }
    }
}