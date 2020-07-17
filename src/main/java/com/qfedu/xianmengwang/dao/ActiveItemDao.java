package com.qfedu.xianmengwang.dao;

import com.qfedu.xianmengwang.dto.ActiveDto;
import com.qfedu.xianmengwang.dto.ActiveItemDto;
import com.qfedu.xianmengwang.pojo.ActiveItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-16
 * Time: 20:55
 */
public interface ActiveItemDao {

    /**
     * 添加
     * @param dto
     * @return
     */
    @Insert("insert into t_activeitem(aid,name,info,picurl,tickets,flag,ctime) values(#{aid},#{name},#{info},#{picurl},0,1,now())")
    int insert(ActiveItemDto dto);

    /**
     * 更新
     * @param id
     * @param flag
     * @return
     */
    @Update("update t_activeitem set flag=#{flag} where id=#{id}")
    int update(@Param("id")int id,@Param("flag")int flag);

    /**
     * 通过aid查找
     * @param aid
     * @return
     */
    @Select("select * from t_activeitem where aid=#{aid} order by tickets desc")
    List<ActiveItem> selectByAid(int aid);
}
