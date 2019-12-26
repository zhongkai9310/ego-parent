package com.ego.manager.service.impl;

import com.ego.common.result.FileResult;
import com.ego.manager.service.UploadService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @Author: sun
 * @DateTime: 2019/12/26 19:07
 * @since 1.0.0
 */
@Service
public class UploadServiceImpl implements UploadService {
    @Override
    public FileResult upload(InputStream inputStream, String fileName) {
        FileResult fileResult = new FileResult();
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "cN_x3ahP4lMjaR9OSee4A22Z0DzWSMGMgd1gRK3V";
        String secretKey = "fSZ6uoXyjCJMVnL3JLcPKWqaPVso5MGyl4z4y9y6";
        String bucket = "ego-sun";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                if (response.statusCode == 200) {
                    fileResult.setSuccess("success");
                    fileResult.setMessage("success");
                    fileResult.setFileUrl("http://q2y4kvloh.bkt.clouddn.com/" + fileName);
                    return fileResult;
                } else {
                    fileResult.setError("error");
                    fileResult.setMessage("error");
                    return fileResult;
                }
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return fileResult;
    }
}
