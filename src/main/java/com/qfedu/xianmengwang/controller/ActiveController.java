package com.qfedu.xianmengwang.controller;

import com.qfedu.xianmengwang.constant.SystemConstant;
import com.qfedu.xianmengwang.dto.ActiveDto;
import com.qfedu.xianmengwang.service.ActiveService;
import com.qfedu.xianmengwang.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 10:39
 */
@Api(tags = "投票活动相关")
public class ActiveController {
    @Autowired
    private ActiveService service;

    /**
     * 新建活动
     * @param dto
     * @param request
     * @return
     */
    @ApiOperation(value = "新建活动",notes = "新建活动")
    @GetMapping("api/active/save")
    public R save(ActiveDto dto, HttpServletRequest request){
        return service.save(dto,request.getHeader(SystemConstant.TOKEN_HEADER));
    }

    /**
     * 审核活动
     * @param aid
     * @param flag
     * @return
     */
    @ApiOperation(value = "审核活动",notes = "审核活动")
    @GetMapping("api/active/flag")
    public R check(int aid,int flag){
        return service.changeFlag(aid, flag);
    }

    /**
     * 查询活动-我的活动
     * @param request
     * @return
     */
    @ApiOperation(value = "查询活动-我的活动",notes = "查询活动-我的活动")
    @GetMapping("api/active/myActive")
    public R myActive(HttpServletRequest request){
        return service.queryByUid(request.getHeader(SystemConstant.TOKEN_HEADER));
    }

    /**
     * 查询活动-热门活动
     * @return
     */
    @ApiOperation(value = "查询活动-热门活动",notes = "查询活动-热门活动")
    @GetMapping("api/active/hot")
    public R hot(){
        return service.queryHot();
    }

    /**
     * 查询活动-最新活动
     * @return
     */
    @ApiOperation(value = "查询活动-最新活动",notes = "查询活动-最新活动")
    @GetMapping("api/active/news")
    public R news(){
        return service.queryNew();
    }

    /**
     * 查询活动-进行中活动
     * @return
     */
    @ApiOperation(value = "查询活动-进行中的活动",notes = "查询活动-进行中的活动")
    @GetMapping("api/active/activing")
    public R activing(){
        return service.queryActiving();
    }
}
