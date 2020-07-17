package com.qfedu.xianmengwang.controller;

import com.qfedu.xianmengwang.constant.SystemConstant;
import com.qfedu.xianmengwang.service.UserLogService;
import com.qfedu.xianmengwang.vo.LayuiR;
import com.qfedu.xianmengwang.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 10:18
 */
@Api(tags = "日志相关")
@RestController
public class UserLogController {
    @Autowired
    private UserLogService service;

    /**
     * 查询所有日志
     * @param request
     * @return
     */
    @ApiOperation(value = "查询",notes = "查询")
    @GetMapping("api/userlog/all")
    public R all(HttpServletRequest request){
        return service.queryLog(request.getHeader(SystemConstant.TOKEN_HEADER));
    }

    /**
     * 分页查询
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value = "分页查询",notes = "分页查询")
    @GetMapping("api/userlog/allpage")
    public LayuiR allPage(HttpServletRequest request,int page,int limit){
        return service.queryLogs(request.getHeader(SystemConstant.TOKEN_HEADER),page,limit);
    }
}
