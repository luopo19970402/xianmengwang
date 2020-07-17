package com.qfedu.xianmengwang.util;

import java.util.Random;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 20:53
 */
public class FileUtil {
    public static String rename(String fn){
        if (!StringUtil.checkEmpty(fn)){
            if (fn.length()>30){
                fn = fn.substring(fn.length() - 30);
            }
            fn=System.currentTimeMillis()+"_"+new Random().nextInt(1000)+"_"+fn;
            return fn;
        }else {
            return null;

        }
    }
}
