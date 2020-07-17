package com.qfedu.xianmengwang.service;

import com.qfedu.xianmengwang.dto.ActiveDto;
import com.qfedu.xianmengwang.vo.R;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 7:43
 */
public interface ActiveService {
    R save(ActiveDto dto, String token);
    R changeFlag(int aid,int flag);
    R queryByUid(String token);
    R queryHot();
    R queryNew();
    R queryActiving();
    R changeLook(int aid);
}
