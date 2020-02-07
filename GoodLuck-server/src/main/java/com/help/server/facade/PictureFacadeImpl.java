package com.help.server.facade;

import com.help.api.PictureFacade;
import com.help.api.PictureParam;
import com.help.api.ResultDTO;
import com.help.server.common.CommonUtils;
import com.help.server.common.ResultCodeEnum;
import com.help.server.common.ResultHandler;
import com.help.server.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class PictureFacadeImpl implements PictureFacade {
    @Autowired
    private PictureService pictureService;

    @Override
    public ResultDTO<PictureParam> upload(PictureParam param) {
        CommonUtils.assertEmptyField(param.getPicMd5(), ResultCodeEnum.PICTURE_MD5_IS_NULL);
        CommonUtils.assertEmptyField(param.getPicUrl(), ResultCodeEnum.PICTURE_URL_IS_NULL);
        CommonUtils.assertEmptyField(param.getPicName(), ResultCodeEnum.PICTURE_NAME_IS_NULL);

        pictureService.submit(param);

        return ResultHandler.handleSuccess(param);
    }

    @Override
    public ResultDTO<List<PictureParam>> list(List<String> md5List) {
        CommonUtils.assertEmptyField(md5List, ResultCodeEnum.PICTURE_MD5_IS_NULL);

        return ResultHandler.handleSuccess(pictureService.list(md5List));
    }

    @Override
    public ResultDTO<PictureParam> info(String md5) {
        CommonUtils.assertEmptyField(md5, ResultCodeEnum.PICTURE_MD5_IS_NULL);

        List<PictureParam> list = pictureService.list(Arrays.asList(md5));

        if (CollectionUtils.isEmpty(list)) {
            return ResultHandler.handleError(ResultCodeEnum.PICTURE_NOT_EXIST);
        }

        return ResultHandler.handleSuccess(list.get(0));
    }
}
