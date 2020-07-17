package com.qfedu.xianmengwang.service;

import com.qfedu.xianmengwang.pojo.UserLog;
import com.qfedu.xianmengwang.vo.LayuiR;
import com.qfedu.xianmengwang.vo.R;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-14
 * Time: 20:50
 */
public interface UserLogService {

    int insert(UserLog userLog);
    R queryLog(String token);
    LayuiR queryLogs(String token,int page, int limit);
}
