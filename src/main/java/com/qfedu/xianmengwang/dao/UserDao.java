package com.qfedu.xianmengwang.dao;

import com.qfedu.xianmengwang.pojo.User;
import org.apache.ibatis.annotations.*;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-14
 * Time: 16:35
 */
public interface UserDao {

    /**
     * 注册
     * @param t_user
     * @return
     */
    @Insert("insert into t_user(phone,nickname,password,flag) values(#{phone},#{nickname},#{password},1)")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int register(User t_user);

    /**
     * 校验手机号
     * @return
     */
    @Select("select id,phone,nickname,password,flag from t_user where phone=#{phone}" )
    User selectPhone(String phone);

    /**
     * 修改密码
     * @param uid
     * @param psw
     * @return
     */
    @Update("update t_user set password=#{psw} where id=#{id}")
    int changePsw(@Param("id") int uid,@Param("psw") String psw);
    /**
     * 找回密码
     * @param phone
     * @param psw
     * @return
     */
    @Update(
            "update t_user set password=#{password} where phone=#{phone}")
    int findPsw(@Param("phone") String phone,@Param("password") String psw);
}
