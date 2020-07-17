package com.qfedu.xianmengwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.qfedu.xianmengwang.config.RedisKeyConfig;
import com.qfedu.xianmengwang.dao.UserDao;
import com.qfedu.xianmengwang.dao.UserLogDao;
import com.qfedu.xianmengwang.dto.UserDto;
import com.qfedu.xianmengwang.dto.UserLogDto;
import com.qfedu.xianmengwang.pojo.User;
import com.qfedu.xianmengwang.pojo.UserLog;
import com.qfedu.xianmengwang.service.UserService;
import com.qfedu.xianmengwang.util.EncryptUtil;
import com.qfedu.xianmengwang.util.JedisCore;
import com.qfedu.xianmengwang.util.TokenUtil;
import com.qfedu.xianmengwang.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-14
 * Time: 19:19
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${xianmengwang.aes.passkey}")
    public String key;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserLogDao userLogDao;
    @Autowired
    private JedisCore jedisCore;


    @Override
    public R checkPhone(String phone) {
        User user = userDao.selectPhone(phone);
        if (user!=null){
            return R.error("手机号已经存在");
        }else {
            return R.ok();
        }

    }

    /**
     * 注册账号
     * @param userDto
     * @return
     */
    @Transactional
    @Override
    public R register(UserDto userDto) {
//        if (checkPhone(userDto.getPhone()).getCode()==200){
//
//            userDto.setPsw(EncryptUtil.aesenc(key,userDto.getPsw()));
//
//            User user = new User();
//            user.setNickname(userDto.getNickname());
//            user.setPassword(userDto.getPsw());
//            user.setPhone(userDto.getPhone());
//            userDao.register(user);
//            //记录日志
//            UserLog userLog = new UserLog(1,user.getId(),"","新增用户");
//            userLogDao.insert(userLog);
//            return R.ok();
//        } else {
//            return R.error("注册失败了哥哥");
//        }
        //校验手机号
        if(checkPhone(userDto.getPhone()).getCode()==200){
            //密文
            userDto.setPsw(EncryptUtil.aesenc(key,userDto.getPsw()));
            //新增
            User user=new User();
            user.setNickname(userDto.getNickname());
            user.setPassword(userDto.getPsw());
            user.setPhone(userDto.getPhone());
            userDao.register(user);
            //记录日志
            UserLog log=new UserLog(1,user.getId(),"","新增用户");
            userLogDao.insert(log);
            return R.ok();
        }else {
            return R.error("亲，手机号已经被注册啦");
        }
    }



    @Override
    public R selectPhone(String phone) {
        return null;
    }

    /**
     * 修改密码
     * @param token
     * @param
     * @return
     */
    @Override
    public R changePass(String token, String pass) {
        if(jedisCore.checkKey(RedisKeyConfig.TOKEN_USER+token)){
            User user=JSON.parseObject(jedisCore.get(RedisKeyConfig.PHONE_TOKEN+token),User.class);
            if(userDao.changePsw(user.getId(),EncryptUtil.aesenc(key,pass))>0){
                //删除令牌
                jedisCore.del(RedisKeyConfig.TOKEN_USER+token);
                jedisCore.del(RedisKeyConfig.PHONE_TOKEN+user.getPhone());
                return R.ok("密码修改成功，请重新登录");
            }
        }
        return R.error("密码修改失败");
    }

    @Override
    public R findPsw(UserLogDto logDto) {
        if (userDao.findPsw(logDto.getPhone(),EncryptUtil.aesenc(key,logDto.getPsw()))>0){
            return R.ok();
        } else {
            return R.error("手机号不存在");
        }
    }

    @Override
    public R login(UserLogDto userLogDto) {
        if (jedisCore.checkKey(RedisKeyConfig.PHONE_FOR+userLogDto.getPhone())){
            return R.error("账号被冻结"+jedisCore.ttl(RedisKeyConfig.PHONE_FOR+userLogDto.getPhone())+"秒后重新登录");
        }  else if (jedisCore.checkKey(RedisKeyConfig.PHONE_TOKEN+userLogDto.getPhone())){
            return R.error("账号已经登录过了");
        } else {
                User user = userDao.selectPhone(userLogDto.getPhone());
                boolean iserror=true;
                if (user!=null){
                    if (user.getFlag()==1){
                        //比较密码
                        if (user.getPassword().equals(EncryptUtil.aesenc(key,userLogDto.getPsw()))){
                            //密码正确后根据id添加一个token
                            String token = TokenUtil.createToken(user.getId());
                            //存到redis中
                            jedisCore.set(RedisKeyConfig.PHONE_TOKEN+userLogDto.getPhone(),token,RedisKeyConfig.TOKEN_TIME);
                            jedisCore.set(RedisKeyConfig.TOKEN_USER+token,JSON.toJSONString(user),RedisKeyConfig.TOKEN_TIME);
                            iserror=false;
                            return R.ok(token);
                        }
                    }
                }
                if (iserror){
                    // 本次登录失败
                    //校验错误的次数
                    if (jedisCore.keys(RedisKeyConfig.PHONE_ERROR+userLogDto.getPhone())==2) {
                        //如果查到错误次数为2 再次登录错误 账号冻结30分钟
                        jedisCore.set(RedisKeyConfig.PHONE_FOR + userLogDto.getPhone(), System.currentTimeMillis() + "", RedisKeyConfig.TOKEN_TIME);
                    }
                        //记录本次错误
                        jedisCore.set(RedisKeyConfig.PHONE_ERROR+userLogDto.getPhone(),System.currentTimeMillis()+"",RedisKeyConfig.PHONERROR_TIME);
                }
            return R.error("账号密码错误");
        }

    }

    @Override
    public R checkToken(String token) {
        if (token!=null && token.length()>0){
            if (jedisCore.checkKey(RedisKeyConfig.TOKEN_USER+token)){
                return R.ok();
            }
        }
        return R.error("令牌无效");
    }

    @Override
    public R loginOut(String token) {
        if (token!=null && token.length()>0){
            jedisCore.del(RedisKeyConfig.TOKEN_USER+token);
            User user = JSON.parseObject(jedisCore.get(RedisKeyConfig.PHONE_TOKEN + token), User.class);
            jedisCore.del(RedisKeyConfig.PHONE_TOKEN+user.getPhone());
            return R.ok();
        }
        return R.error("请传递令牌");
    }
}
