package com.qfedu.xianmengwang.controller;

import com.qfedu.xianmengwang.dto.ActiveItemDto;
import com.qfedu.xianmengwang.service.ActiveItemService;
import com.qfedu.xianmengwang.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 14:39
 */
@Api(tags = "投票活动详情相关")
@RestController
@RequestMapping("/api/activeitem/")
public class ActiveItemController {

    @Autowired
    private ActiveItemService service;

    /**
     * 新增一条投票
     * @param itemDto
     * @return
     */
    @ApiOperation(value = "新增一条投票",notes = "新增一条投票")
    @PostMapping("save")
    public R save(ActiveItemDto itemDto){
        return service.save(itemDto);
    }

    /**
     * 根据票数查询
     * @param id
     * @param flag
     * @return
     */
    @ApiOperation(value = "根据票数查询",notes = "根据票数查询")
    @PostMapping("check")
    public R check(int id,int flag){
        return service.changeFlag(id, flag);
    }

    /**
     * 投票详情
     * @param aid
     * @return
     */
    @ApiOperation(value = "投票详情",notes = "投票详情")
    @PostMapping("detail")
    public R query(int aid){
        return service.queryByAid(aid);
    }
}
