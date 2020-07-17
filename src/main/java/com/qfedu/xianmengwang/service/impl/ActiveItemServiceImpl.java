package com.qfedu.xianmengwang.service.impl;

import com.qfedu.xianmengwang.dao.ActiveItemDao;
import com.qfedu.xianmengwang.dto.ActiveItemDto;
import com.qfedu.xianmengwang.pojo.ActiveItem;
import com.qfedu.xianmengwang.service.ActiveItemService;
import com.qfedu.xianmengwang.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 8:43
 */
@Service
public class ActiveItemServiceImpl implements ActiveItemService {
    @Autowired
    private ActiveItemDao dao;

    /**
     *添加一个活动
     * @param dto
     * @return
     */
    @Override
    public R save(ActiveItemDto dto) {
        if (dao.insert(dto)>0){
            return R.ok();
        }else {
            return R.error("异常");
        }
    }


    /**
     * 通过aid查询活动
     * @param aid
     * @return
     */
    @Override
    public R queryByAid(int aid) {
        List<ActiveItem> list = dao.selectByAid(aid);
        if (list!=null && list.size()>0){
            return R.ok();
        }else {
            return R.error("未添加候选人");
        }
    }

    /**
     * 添加标识
     * @param id
     * @param flag
     * @return
     */
    @Override
    public R changeFlag(int id, int flag) {
        if (dao.update(id, flag)>0){
            return R.ok();
        }else {
            return R.error("异常");
        }
    }
}
