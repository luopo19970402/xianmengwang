package com.qfedu.xianmengwang.service;

import com.qfedu.xianmengwang.dto.ActiveItemDto;
import com.qfedu.xianmengwang.vo.R;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 7:47
 */
public interface ActiveItemService {
    //新增
    R save(ActiveItemDto dto);
    //查询 活动下的详情信息 排序 根据票数
    R queryByAid(int aid);
    //审核
    R changeFlag(int id,int flag);

}
