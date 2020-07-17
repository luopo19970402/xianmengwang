package com.qfedu.xianmengwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.qfedu.xianmengwang.config.RedisKeyConfig;
import com.qfedu.xianmengwang.dao.UserLogDao;
import com.qfedu.xianmengwang.pojo.User;
import com.qfedu.xianmengwang.pojo.UserLog;
import com.qfedu.xianmengwang.service.UserLogService;
import com.qfedu.xianmengwang.service.UserService;
import com.qfedu.xianmengwang.util.JedisCore;
import com.qfedu.xianmengwang.util.StringUtil;
import com.qfedu.xianmengwang.vo.LayuiR;
import com.qfedu.xianmengwang.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-14
 * Time: 20:51
 */
@Service
public class UserLogServiceImpl implements UserLogService {

    @Autowired
    private UserLogDao userLogDao;
    @Autowired
    private JedisCore jedisCore;

    /**
     * 添加一个日志信息
     * @param userLog
     * @return
     */
    @Override
    public int insert(UserLog userLog) {
        return userLogDao.insert(userLog);
    }

    /**
     * 查询日志信息
     * @param token
     * @return
     */
    @Override
    public R queryLog(String token) {
        //校验参数是否合法
        if (!StringUtil.checkEmpty(token)){
            //校验令牌的有效性
            if (!jedisCore.checkKey(RedisKeyConfig.TOKEN_USER+token)){
                //获取用户信息
                User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER+token), User.class);
                //查询
                return R.ok(userLogDao.selectByUid(user.getId()));
            }
        }
        return R.error("令牌无效");
    }

    /**
     * 分页查询日志信息
     * @param token
     * @param page
     * @param limit
     * @return
     */
    @Override
    public LayuiR queryLogs(String token, int page, int limit) {
        LayuiR layuiR = new LayuiR();
        //校验参数是否合法
        if (!StringUtil.checkEmpty(token)){
            //校验令牌的有效性
            if (jedisCore.checkKey(RedisKeyConfig.TOKEN_USER+token)){
                //获取用户信息
                User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
                int s=0;
                if (page>=1){
                    s=(page-1)*limit;
                }
                List<UserLog> list = userLogDao.selectByUidPage(user.getId(), s, limit);
                layuiR.setCode(0);
                layuiR.setCount(userLogDao.selectCount(user.getId()));
                layuiR.setMsg("");
                layuiR.setData(list);
            }
        }
        return layuiR;
    }

}
