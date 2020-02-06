package com.help.api;

import java.util.List;

public interface PictureFacade {
    public ResultDTO<String> upload(PictureParam param);

    public ResultDTO<List<PictureParam>> list(List<String> md5List);

    public ResultDTO<PictureParam> info(String md5);

}
