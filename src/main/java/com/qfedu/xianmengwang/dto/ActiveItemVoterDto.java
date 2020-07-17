package com.qfedu.xianmengwang.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-16
 * Time: 22:32
 */
@Data
public class ActiveItemVoterDto {
    private String ip;
    private String nickname;
    private String phone;
    private Date ctime;
}
