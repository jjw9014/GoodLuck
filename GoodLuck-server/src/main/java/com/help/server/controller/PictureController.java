package com.help.server.controller;

import com.alibaba.fastjson.JSON;
import com.help.api.*;
import com.help.server.common.FileUtil;
import com.help.server.common.GetParamsUtils;
import com.help.server.common.ResultHandler;
import com.help.server.service.SenInfoCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

@Controller
@RequestMapping("/picture")
public class PictureController {
    private static Logger logger = LoggerFactory.getLogger(PictureController.class);

    @Autowired
    private PictureFacade pictureFacade;

    @Autowired
    private SenInfoCheckService senInfoCheckService;

    // http://127.0.0.1:9090/picture/upload
    @PostMapping(value="/upload")
    @ResponseBody
    public String upload(@RequestParam("pic") MultipartFile file) {
        //图片校验
        boolean picCheckResult = senInfoCheckService.checkPic(file);
        if(!picCheckResult){
            return JSON.toJSONString(ResultHandler.createErrorResult("图片内容非法"));
        }
        PictureParam pictureParam = new PictureParam();
        pictureParam.setPicName(file.getOriginalFilename());
        pictureParam.setPicSuffix(file.getContentType());
        pictureParam.setPicSize((int)file.getSize());

        String md5 = null;
        try {
            md5 = FileUtil.temp(file.getBytes());
            logger.info("缓存图片 {}", md5);
        } catch (IOException e) {
            logger.error("缓存图片失败", e);
        }

        if (md5 != null) {
            pictureParam.setPicMd5(md5);
            pictureParam.setPicUrl("picture/preview/" + md5);
        }

        // todo 暂时不上传ftp

        return JSON.toJSONString(pictureFacade.upload(pictureParam));
    }

    // http://127.0.0.1:9090/picture/detail?md5=1c8d7d1e8aefa22449a06a914efd441e
    @RequestMapping(value="/detail")
    @ResponseBody
    public String get(String md5) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        md5 = (String) GetParamsUtils.getValueIfNull(md5, paramMap.get("md5"), " ");

        return JSON.toJSONString(pictureFacade.info(md5));
    }

    // http://127.0.0.1:9090/picture/download?md5=1c8d7d1e8aefa22449a06a914efd441e
    @RequestMapping(value="/download")
    @ResponseBody
    public void download(String md5, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        md5 = (String) GetParamsUtils.getValueIfNull(md5, paramMap.get("md5"), " ");

        ResultDTO<PictureParam> resultDTO = pictureFacade.info(md5);
        if (!resultDTO.isSuccess() || resultDTO.getData() == null) {
            logger.error("图片不存在，{}", md5);
            return;
        }

        PictureParam param = resultDTO.getData();

        try {
            String explorerType = request.getHeader("User-Agent");
            response.addHeader("Content-Disposition", String.format("attachment;filename=%s", new String((param.getPicName()).getBytes("GB2312"), "iso8859-1")));
            if (explorerType != null && !"".equals(explorerType) && explorerType.indexOf("MSIE") > 0) {
                response.setHeader("Pragma", "public");
                response.setHeader("Cache-Control", "public");
            }
            File file = new File(FileUtil.BASE_URL + md5);
            if (file.exists()) {
                FileCopyUtils.copy(
                        FileCopyUtils.copyToByteArray(file),
                        response.getOutputStream());
            }
        } catch (IOException e) {
            try {
                response.setContentType("text/html; charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(String.format("%s：%s", "下载失败", e.getMessage()));
                response.getWriter().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    // http://127.0.0.1:9090/picture/preview/1c8d7d1e8aefa22449a06a914efd441e
    @RequestMapping(value="/preview/{md5}")
    @ResponseBody
    public void preview(@PathVariable String md5, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        md5 = (String) GetParamsUtils.getValueIfNull(md5, paramMap.get("md5"), " ");

        ResultDTO<PictureParam> resultDTO = pictureFacade.info(md5);
        if (!resultDTO.isSuccess() || resultDTO.getData() == null) {
            logger.error("图片不存在，{}", md5);
            return;
        }

        // photoUrl为接收到的路径
        File file = new File(FileUtil.BASE_URL + md5);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(fis, 1024);
                ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
                byte[] cache = new byte[1024];
                int length = 0;
                while ((length = bis.read(cache)) != -1) {
                    bos.write(cache, 0, length);
                }
                /**
                return bos.toByteArray();
                BASE64Encoder encoder = new BASE64Encoder();
                return encoder.encode(bos.toByteArray());
                **/
                response.getOutputStream().write(bos.toByteArray());
            } catch (Exception e) {
                logger.error("show picture failed", e);
            }
        }
    }

}
