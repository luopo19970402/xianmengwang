package com.qfedu.xianmengwang.controller;

import com.qfedu.xianmengwang.constant.SystemConstant;
import com.qfedu.xianmengwang.dto.UserDto;
import com.qfedu.xianmengwang.dto.UserLogDto;
import com.qfedu.xianmengwang.service.UserService;
import com.qfedu.xianmengwang.vo.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-14
 * Time: 19:30
 */
@Api(tags = "用户相关接口")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService service;


    @ApiOperation(value = "校验手机号是否存在",notes = "校验手机号是否存在")
    @GetMapping("api/user/checkPhone/{phone}")
    R checkPhone(@PathVariable String phone){
        return service.checkPhone(phone);
    }

    @ApiOperation(value = "注册",notes = "注册")
    @PostMapping("api/user/register")
    R register(@RequestBody UserDto userDto){
        return service.register(userDto);
    }

    @ApiOperation(value = "修改密码",notes = "修改密码")
    @PostMapping("api/user/changepass/{pass}")
    public R changePass(HttpServletRequest request,@PathVariable String pass){
        return service.changePass(request.getHeader(SystemConstant.TOKEN_HEADER),pass);
    }

    @ApiOperation(value = "找回密码",notes = "找回密码")
    @PostMapping("api/user/findpass")
    public R findPass(@RequestBody UserLogDto userLogDto){
        return service.findPsw(userLogDto);
    }

    @ApiOperation(value = "登录账号",notes = "登录账号")
    @PostMapping("api/user/login")
    public R login(@RequestBody UserLogDto userLogDto){
        return service.login(userLogDto);
    }

    @ApiOperation(value = "检查令牌",notes = "检查令牌")
    @GetMapping("api/user/checktoken")
    public R checkToken(HttpServletRequest request){
        return service.checkToken(request.getHeader(SystemConstant.TOKEN_HEADER));
    }

    @ApiOperation(value = "退出",notes = "退出")
    @GetMapping("api/user/loginout")
    public R loginout(HttpServletRequest request){
        return service.loginOut(request.getHeader(SystemConstant.TOKEN_HEADER));
    }

}
