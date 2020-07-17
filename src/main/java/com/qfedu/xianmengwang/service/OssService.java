package com.qfedu.xianmengwang.service;

import com.qfedu.xianmengwang.vo.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 20:51
 */
public interface OssService {
    R upload(MultipartFile file);
}
