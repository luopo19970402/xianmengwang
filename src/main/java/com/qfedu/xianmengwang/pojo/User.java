package com.qfedu.xianmengwang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-14
 * Time: 16:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private Integer flag;
    private String phone;
    private String password;
    private String nickname;
}
