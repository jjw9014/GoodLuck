package com.help.server.controller;

import com.help.api.ResultDTO;
import com.help.server.common.ResultHandler;
import com.help.server.common.SenInfoCheckUtil;
import com.help.server.service.WxO2oService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/senInfoCheck")
public class SenInfoCheckController {

    @Autowired
    private WxO2oService wxO2oService;

    /**
     * 校验图片
     * @param file 图片文件
     * @return
     */
    @RequestMapping(value="/checkPic")
    public ResultDTO checkPic(@RequestParam("pic") MultipartFile file) {
        return ResultHandler.handleSuccess(SenInfoCheckUtil.imgFilter(wxO2oService.getAccessToken(),file));
    }

    /**
     * 校验文字
     * @param content 文字内容
     * @return
     */
    @RequestMapping(value="/checkContent")
    public ResultDTO checkPic(String content) {
        return ResultHandler.handleSuccess(SenInfoCheckUtil.cotentFilter(wxO2oService.getAccessToken(),content));
    }
}
