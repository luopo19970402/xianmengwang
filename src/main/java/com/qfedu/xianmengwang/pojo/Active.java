package com.qfedu.xianmengwang.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-16
 * Time: 20:30
 */
@Data
public class Active {
    private Integer id;
    private Integer uid;
    private String name;
    private String info;
    private String picurl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ctime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date stime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date etime;
    private int flag;
    private int dayticket;
    private int lookcount;
}
