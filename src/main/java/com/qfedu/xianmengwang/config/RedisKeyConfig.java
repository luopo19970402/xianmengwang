package com.qfedu.xianmengwang.config;


public class RedisKeyConfig {
    //令牌
    public static final String PHONE_TOKEN="xianmengwang:phone:";//后面追加手机号 值存储令牌
    public static final String TOKEN_USER="xianmengwang:token:";//后面追加令牌 值存储用户信息

    public static final String PHONE_FOR="xianmengwang:for:phone:";//后面跟手机号 冻结的账号
    public static final String PHONE_ERROR="xianmengwang:error:phone:";//后面跟手机号和时间戳

    //有效期
    public static final int TOKEN_TIME=30*60; //令牌有效期 单位秒
    public static final int TOKENFOR_TIME=30*60; //冻结账号 单位秒
    public static final int PHONERROR_TIME=10*60; //错误的有效期 单位秒
}
