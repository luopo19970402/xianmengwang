package com.qfedu.xianmengwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.qfedu.xianmengwang.config.RedisKeyConfig;
import com.qfedu.xianmengwang.constant.SystemConstant;
import com.qfedu.xianmengwang.dao.ActiveVoterDao;
import com.qfedu.xianmengwang.dto.ActiveVoterDto;
import com.qfedu.xianmengwang.pojo.ActiveVoter;
import com.qfedu.xianmengwang.pojo.User;
import com.qfedu.xianmengwang.service.ActiveVoterService;
import com.qfedu.xianmengwang.util.BeanUtil;
import com.qfedu.xianmengwang.util.JedisCore;
import com.qfedu.xianmengwang.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 9:46
 */
@Service
public class ActiveVoterServiceImpl implements ActiveVoterService {

    @Resource
    private ActiveVoterDao dao;
    @Autowired
    private JedisCore jedisCore;

    /**
     * 投票
     * @param dto
     * @param request
     * @return
     */
    @Override
    public R add(ActiveVoterDto dto, HttpServletRequest request) {
        String token = request.getHeader(SystemConstant.TOKEN_HEADER);
        User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
        //校验是否可以投票
        Integer c = dao.selectFreeTicket(user.getId(), dto.getAid());
        if (c != null && c <= 0) {
            //表示票已用完
            return R.error("您的票数已经用完");
        } else {
            ActiveVoter av = BeanUtil.copyDto(ActiveVoter.class, dto, dto.getClass().getDeclaredFields());
            if (av != null) {
                av.setIp(request.getRemoteAddr());
                if (dao.insert(av) > 0) {
                    return R.ok();
                }
            }
        }
        return R.error("投票失败");
    }

    /**
     * 查询候选人下的投票记录
     * @param aiid
     * @return
     */
    @Override
    public R query(int aiid) {
        return R.ok(dao.selectByAiid(aiid));
    }

    /**
     * 查询用户投票的活动
     * @param token
     * @return
     */
    @Override
    public R query(String token) {
        User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
        return R.ok(dao.selectByUid(user.getId()));
    }

    /**
     * 查询用户的投票记录
     * @param token
     * @return
     */
    @Override
    public R queryItem(String token) {
        User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
        return R.ok(dao.selectByUidItem(user.getId()));
    }

    /**
     * 查询剩余的票数
     * @param token
     * @param aid
     * @return
     */
    @Override
    public R queryTickets(String token, int aid) {
        User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
        Integer c = dao.selectFreeTicket(user.getId(), aid);
        if (c!=null && c<=0){
            return R.error("无票了");
        }else {
            return R.ok();
        }
    }
}
