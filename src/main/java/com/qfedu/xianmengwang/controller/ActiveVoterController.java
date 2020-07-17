package com.qfedu.xianmengwang.controller;

import com.qfedu.xianmengwang.constant.SystemConstant;
import com.qfedu.xianmengwang.dto.ActiveVoterDto;
import com.qfedu.xianmengwang.service.ActiveVoterService;
import com.qfedu.xianmengwang.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 14:47
 */
@Api(tags = "投票相关")
@RestController
@RequestMapping("/api/voter/")
public class ActiveVoterController {

    @Autowired
    private ActiveVoterService service;

    /**
     * 投票数量+1
     * @param activeVoterDto
     * @param request
     * @return
     */
    @ApiOperation(value = "点击增加一个投票",notes = "点击增加一个投票")
    @PostMapping("save")
    public R save(@RequestBody ActiveVoterDto activeVoterDto, HttpServletRequest request){
        return service.add(activeVoterDto,request);
    }

    /**
     * 查询候选人下的投票记录-无投票权
     * @param id
     * @return
     */
    @ApiOperation(value = "查询候选人下的投票记录-无投票权",notes = "查询候选人下的投票记录-无投票权")
    @GetMapping("itemtick")
    public R itemtick(int id){
        return service.query(id);
    }

    /**
     * 查询候选人下的投票记录-有投票权
     * @param request
     * @return
     */
    @ApiOperation(value = "查询候选人下的投票记录-有投票权",notes = "查询候选人下的投票记录-有投票权")
    @GetMapping("voteractive")
    public R mytick(HttpServletRequest request){
        return service.query(request.getHeader(SystemConstant.TOKEN_HEADER));
    }

    /**
     * 我的投票记录
     * @param request
     * @return
     */
    @ApiOperation(value = "我的投票记录",notes = "我的投票记录")
    @GetMapping("myticklog")
    public R mytickitem(HttpServletRequest request){
        return service.queryItem(request.getHeader(SystemConstant.TOKEN_HEADER));
    }

    /**
     * 剩余票数
     * @param request
     * @param aid
     * @return
     */
    @ApiOperation(value = "剩余票数",notes = "剩余票数")
    @GetMapping("freetick")
    public R freetick(HttpServletRequest request,int aid){
        return service.queryTickets(request.getHeader(SystemConstant.TOKEN_HEADER),aid);
    }
}
