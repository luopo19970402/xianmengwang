package com.qfedu.xianmengwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.qfedu.xianmengwang.config.RedisKeyConfig;
import com.qfedu.xianmengwang.dao.ActiveDao;
import com.qfedu.xianmengwang.dto.ActiveDto;
import com.qfedu.xianmengwang.pojo.Active;
import com.qfedu.xianmengwang.pojo.User;
import com.qfedu.xianmengwang.service.ActiveService;
import com.qfedu.xianmengwang.util.BeanUtil;
import com.qfedu.xianmengwang.util.JedisCore;
import com.qfedu.xianmengwang.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 7:53
 */
@Service
public class AcvtiveServiceImpl implements ActiveService {

    @Resource
    private ActiveDao dao;
    @Autowired
    private JedisCore jedisCore;

    /**
     * 保存
     * @param dto
     * @param token
     * @return
     */
    @Override
    public R save(ActiveDto dto, String token) {
        User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
        Active active = BeanUtil.copyDto(Active.class, dto, dto.getClass().getDeclaredFields());
        if (active!=null){
            active.setUid(user.getId());
            if (dao.insert(active) > 0) {
                return R.ok();
            }
        }
        return R.error("操作未生效");
    }

    /**
     *  审核账号是否
     * @param aid
     * @param flag
     * @return
     */
    @Override
    public R changeFlag(int aid, int flag) {
        if (dao.update(aid, flag)>0){
            return R.ok();
        }else {
            return R.error("审核失败");
        }
    }

    /**
     *  通过Uid查询活动
     * @param token
     * @return
     */
    @Override
    public R queryByUid(String token) {
        User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.TOKEN_USER + token), User.class);
        List<Active> list = dao.queryByUid(user.getId());
        if (list!=null && list.size()>0){
            return R.ok(list);
        }else {
            return R.error("未查到活动");
        }
    }

    /**
     * 查询热门活动
     * @return
     */
    @Override
    public R queryHot() {
        return R.ok(dao.queryActiveHot());
    }

    /**
     * 查询新活动
     * @return
     */
    @Override
    public R queryNew() {
        return R.ok(dao.queryActiveNew());
    }

    /**
     * 查询激活的活动
     * @return
     */
    @Override
    public R queryActiving() {
        return R.ok(dao.queryActiving());
    }

    /**
     * 添加一次浏览记录
     * @param aid
     * @return
     */
    @Override
    public R changeLook(int aid) {
        if (dao.addLook(aid)>0){
            return R.ok();
        }else {
            return R.error("新增失败");
        }
    }
}
