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
public class ActiveVoter {
    private Integer id;
    private Integer aid;
    private Integer aiid;
    private Integer uid;
    private String ip;
    private Date ctime;
}
