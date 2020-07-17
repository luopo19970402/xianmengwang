package com.qfedu.xianmengwang.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-16
 * Time: 20:33
 */
@Data
public class ActiveDto {
    //自定义注解
//    @BeanCopyField("name")
    private String n;
    private String info;
    private String picurl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date etime;
    private int dayticket;
}
