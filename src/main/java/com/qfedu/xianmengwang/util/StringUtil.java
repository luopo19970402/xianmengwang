package com.qfedu.xianmengwang.util;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 7:37
 */
public class StringUtil {
    //校验字符串是否为空
    public static boolean checkEmpty(String str){
        if(str!=null && str.length()>0){
            return false;
        }else {
            return true;
        }
    }
    //
    public static boolean checkEmptys(String... args){
        boolean r=false;
        for(String s:args){
            r=checkEmpty(s);
            if (r){
                break;
            }
//            if(s==null && s.length()==0){
//                r=true;
//                break;
//            }
        }
        return r;
    }
}
