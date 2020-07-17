package com.qfedu.xianmengwang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-14
 * Time: 16:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLog {
    private Long id;
    private Integer type;
    private Integer uid;
    private String ip;
    private String content;
    private Date ctime;

    public UserLog(Integer type, Integer uid, String ip, String content) {
        this.type = type;
        this.uid = uid;
        this.ip = ip;
        this.content = content;
    }

}
