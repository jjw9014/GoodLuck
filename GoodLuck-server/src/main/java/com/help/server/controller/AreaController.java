package com.help.server.controller;

import com.alibaba.fastjson.JSON;
import com.help.api.AreaFacade;
import com.help.api.SuggestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/area")
public class AreaController {
    private static Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Autowired
    private AreaFacade areaFacade;

    // http://127.0.0.1:9090/area/getProvinceList
    @RequestMapping(value="/getProvinceList")
    @ResponseBody
    public String getProvinceList(SuggestParam param) {

        return JSON.toJSONString(areaFacade.getProvinces());
    }

    // http://127.0.0.1:9090/area/getCityList?provinceCode=110000
    @RequestMapping(value="/getCityList")
    @ResponseBody
    public String getCityList(String provinceCode) {

        return JSON.toJSONString(areaFacade.getCitysByProvince(provinceCode));
    }

    // http://127.0.0.1:9090/area/getDistrictList?cityCode=110100
    @RequestMapping(value="/getDistrictList")
    @ResponseBody
    public String getDistrictList(String cityCode) {

        return JSON.toJSONString(areaFacade.getDistrictsByCity(cityCode));
    }

    // http://127.0.0.1:9090/area/getAreaByCode?areaCode=110100
    @RequestMapping(value="/getAreaByCode")
    @ResponseBody
    public String getAreaByCode(String areaCode) {

        return JSON.toJSONString(areaFacade.getAreaByCode(areaCode));
    }

    // http://127.0.0.1:9090/area/getAreaByName?areaName=北京市
    @RequestMapping(value="/getAreaByName")
    @ResponseBody
    public String getAreaByName(String areaName) {

        return JSON.toJSONString(areaFacade.getAreaByName(areaName));
    }

}
