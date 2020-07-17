package com.qfedu.xianmengwang.dao;

import com.qfedu.xianmengwang.pojo.User;
import com.qfedu.xianmengwang.pojo.UserLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-14
 * Time: 20:42
 */
public interface UserLogDao {

    @Insert("insert into t_userlog(uid,type,content,ctime,ip) values (#{uid},#{type},#{content},now(),1) ")
    int insert(UserLog log);
    @Select("select id,uid,type,content,ctime,ip,from t_userlog where uid=#{uid} order by ctime desc")
    List<UserLog> selectByUid(int uid);
    @Select("select id,uid,type,content,ctime,ip from t_userlog where uid=#{uid} order by ctime desc limit #{start},#{size}")
    List<UserLog> selectByUidPage(int uid,int start,int size);

    @Select("select count(*) from t_userlog where uid=#{uid}")
    long selectCount(int uid);
}
