package com.qfedu.xianmengwang.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-16
 * Time: 20:31
 */
@Data
public class ActiveItem {
    private Integer id;
    private String name;
    private String info;
    private String picurl;
    private int flag;
    private int aid;
    private int tickets;
    private Date ctime;
}
