package com.qfedu.xianmengwang.service;

import com.qfedu.xianmengwang.dao.UserDao;
import com.qfedu.xianmengwang.dto.UserDto;
import com.qfedu.xianmengwang.dto.UserLogDto;
import com.qfedu.xianmengwang.pojo.User;
import com.qfedu.xianmengwang.pojo.UserLog;
import com.qfedu.xianmengwang.vo.R;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-14
 * Time: 19:12
 */
public interface UserService {

    /**
     * 检查手机号
     * @param phone
     * @return
     */
   R checkPhone(String phone);

    /**
     * 注册
     * @param userDto
     * @return
     */
    R register(UserDto userDto);

    /**
     * 校验手机号
     * @return
     */
    R selectPhone(String phone);

    /**
     * 修改密码
     * @param token
     * @param psw
     * @return
     */
    R changePass(String token,String pass);

    /**
     * 找回密码
     * @param logDto
     * @return
     */
    R findPsw(UserLogDto logDto);

    /**
     * 登录账号
     * @param userLogDto
     * @return
     */
    R login(UserLogDto userLogDto);

    /**
     * 检查令牌
     * @param token
     * @return
     */
    R checkToken(String token);

    /**
     * 退出登录
     * @param token
     * @return
     */
    R loginOut(String token);
}
