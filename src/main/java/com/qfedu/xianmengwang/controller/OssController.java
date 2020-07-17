package com.qfedu.xianmengwang.controller;

import com.qfedu.xianmengwang.service.OssService;
import com.qfedu.xianmengwang.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 21:14
 */

@RestController
public class OssController {
    @Autowired
    private OssService service;

    @PostMapping("api/oss/upload")
    public R upload(MultipartFile file) {
        return service.upload(file);
    }
}
