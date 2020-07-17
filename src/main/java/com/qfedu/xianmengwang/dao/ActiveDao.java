package com.qfedu.xianmengwang.dao;

import com.qfedu.xianmengwang.pojo.Active;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-16
 * Time: 20:35
 */
public interface ActiveDao {
    /**
     * 添加
     * @param active
     * @return
     */
    @Insert("insert into t_active(uid,name,info,ctime,stime,etime,flag,picurl,dayticket,lookcount) values(#{uid},#{name},#{info},now(),#{stime},#{etime},1,#{picurl},#{dayticket},0)")
    int insert(Active active);

    /**
     * 更新
     * @param aid
     * @param flag
     * @return
     */
    @Update("update t_active set flag=#{flag} where aid=#{aid})")
    int update(@Param("aid")int aid,@Param("flag")int flag);

    /**
     * 通过uid查找
     * @param uid
     * @return
     */
    @Select("select id,uid,name,info,ctime,stime,etime,flag,picurl,dayticket,lookcount from t_active where uid=#{uid} order by ctime desc")
    List<Active> queryByUid(int uid);

    /**
     * 查找热门活动
     * @return
     */
    @Select("select id,uid,name,info,ctime,stime,etime,flag,picurl,dayticket,lookcount from t_active order by lookcount desc limit 10")
    List<Active> queryActiveHot();

    /**
     * 查找现在活动
     * @return
     */
    @Select("select id,uid,name,info,ctime,stime,etime,flag,picurl,dayticket,lookcount from t_active where stime > now() order by stime asc limit 10")
    List<Active> queryActiveNew();

    /**
     *  查询激活状态的活动
     * @return
     */
    @Select("select id,uid,name,info,ctime,stime,etime,flag,picurl,dayticket,lookcount from t_active where stime <= now() and etime >now() order by stime asc limit 10")
    List<Active> queryActiving();

    /**
     * 添加一次点击量
     * @param aid
     * @return
     */
    @Update("update t_active set lookcount=lookcount+1 where aid=#{aid}")
    int addLook(int aid);
}
