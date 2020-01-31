package com.help.api;

import java.util.List;

public interface AreaFacade {
    public ResultDTO<List> getProvinces();

    public ResultDTO<List> getCitysByProvince(String code);

    public ResultDTO<List> getDistrictsByCity(String code);

    public ResultDTO<AreaParam> getAreaByCode(String code);

    public ResultDTO<AreaParam> getAreaByName(String name);

}
