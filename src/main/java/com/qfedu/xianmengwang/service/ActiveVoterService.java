package com.qfedu.xianmengwang.service;

import com.qfedu.xianmengwang.dto.ActiveVoterDto;
import com.qfedu.xianmengwang.vo.R;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 7:48
 */
public interface ActiveVoterService {
    R add(ActiveVoterDto dto, HttpServletRequest request);
    R query(int aiid);
    R query(String token);
    R queryItem(String token);
    R queryTickets(String token,int aid);

}
