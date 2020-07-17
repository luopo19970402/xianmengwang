package com.qfedu.xianmengwang.service.impl;

import com.qfedu.xianmengwang.constant.SystemConstant;
import com.qfedu.xianmengwang.service.OssService;
import com.qfedu.xianmengwang.util.FileUtil;
import com.qfedu.xianmengwang.util.OssSingleCore;
import com.qfedu.xianmengwang.util.StringUtil;
import com.qfedu.xianmengwang.vo.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with taosicong.
 * User: Administrator
 * Date: 2020-07-17
 * Time: 20:52
 */
public class OssServiceImpl implements OssService {
    @Override
    public R upload(MultipartFile file) {
        if (!file.isEmpty()){
            //重命名.限制长度
            String fn= FileUtil.rename(file.getOriginalFilename());
            try{
                String url= OssSingleCore.getInstance().upload(SystemConstant.OSS_BUCKET,fn,
                        file.getBytes());
                if(!StringUtil.checkEmpty(url)){
                    return R.ok(url);
                }
            }catch (Exception e){
                return R.error(e.getMessage());
            }
        }
        return R.error("oss上传异常");
    }

}
