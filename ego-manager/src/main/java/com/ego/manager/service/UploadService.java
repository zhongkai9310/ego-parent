package com.ego.manager.service;

import com.ego.common.result.FileResult;

import java.io.InputStream;

/**
 * @Author: sun
 * @DateTime: 2019/12/26 19:07
 * @since 1.0.0
 */
public interface UploadService {

    FileResult upload(InputStream inputStream, String fileName);

}
