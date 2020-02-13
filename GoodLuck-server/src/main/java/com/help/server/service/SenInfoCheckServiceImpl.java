package com.help.server.service;

import com.help.server.common.SenInfoCheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 敏感内容校验实现类
 */
@Service
public class SenInfoCheckServiceImpl implements SenInfoCheckService{

    @Autowired
    private WxO2oService wxO2oService;

    @Override
    public boolean checkPic(MultipartFile file) {
        return SenInfoCheckUtil.imgFilter(wxO2oService.getAccessToken(),file);
    }

    @Override
    public boolean checkContent(String content) {
        return SenInfoCheckUtil.cotentFilter(wxO2oService.getAccessToken(),content);
    }
}
