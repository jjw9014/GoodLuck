package com.help.server.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 敏感内容校验
 */
public interface SenInfoCheckService {

    /**
     * 校验图片
     * @param file 文件
     * @return
     */
    boolean checkPic(MultipartFile file);

    /**
     * 校验文字内容
     * @param content 文字
     * @return
     */
    boolean checkContent(String content);
}
