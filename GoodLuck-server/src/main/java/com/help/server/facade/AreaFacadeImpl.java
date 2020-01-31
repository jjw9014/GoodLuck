package com.help.server.facade;

import com.help.api.AreaFacade;
import com.help.api.AreaParam;
import com.help.api.ResultDTO;
import com.help.server.common.CommonUtils;
import com.help.server.common.ResultCodeEnum;
import com.help.server.common.ResultHandler;
import com.help.server.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaFacadeImpl implements AreaFacade {
    @Autowired
    private IAreaService areaService;

    @Override
    public ResultDTO<List> getProvinces() {
        AreaParam areaParam = new AreaParam();
        areaParam.setAreaType(2);

        return ResultHandler.handleSuccess(areaService.list(areaParam));
    }

    @Override
    public ResultDTO<List> getCitysByProvince(String code) {
        CommonUtils.assertEmptyField(code, ResultCodeEnum.AREA_PROVINCE_CODE_IS_NULL);

        AreaParam areaParam = new AreaParam();
        areaParam.setAreaType(3);
        areaParam.setAreaParentCode(code);

        return ResultHandler.handleSuccess(areaService.list(areaParam));
    }

    @Override
    public ResultDTO<List> getDistrictsByCity(String code) {
        CommonUtils.assertEmptyField(code, ResultCodeEnum.AREA_CITY_CODE_IS_NULL);

        AreaParam areaParam = new AreaParam();
        areaParam.setAreaType(4);
        areaParam.setAreaParentCode(code);

        return ResultHandler.handleSuccess(areaService.list(areaParam));
    }

    @Override
    public ResultDTO<AreaParam> getAreaByCode(String code) {
        CommonUtils.assertEmptyField(code, ResultCodeEnum.AREA_CODE_IS_NULL);

        AreaParam areaParam = new AreaParam();
        areaParam.setAreaCode(code);

        return ResultHandler.handleSuccess(areaService.get(areaParam));
    }

    @Override
    public ResultDTO<AreaParam> getAreaByName(String name) {
        AreaParam areaParam = new AreaParam();
        areaParam.setAreaName(name);

        return ResultHandler.handleSuccess(areaService.get(areaParam));
    }
}
