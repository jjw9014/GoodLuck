package com.help.server.service;

import com.github.pagehelper.PageHelper;
import com.help.api.ResultDTO;
import com.help.api.TuserGroupDTO;
import com.help.api.TuserPageParam;
import com.help.api.TuserParam;
import com.help.server.cache.StaticDataCache;
import com.help.server.common.ResultHandler;
import com.help.server.dao.TuserMapper;
import com.help.server.model.Tuser;
import com.help.server.model.TuserExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;


/**
 * 用户管理接口服务
 * @Author LY
 * @Date 2020/1/30 11:18
 * @Version 1.0
 **/
@Service
@Slf4j
public class TuserServiceImpl implements TuserService{


    @Resource
    private TuserMapper tuserMapper;

    @Resource
    private IQuestionService iQuestionService;


    @Override
    public TuserParam getUserInfoByOpenId(String openId) {
        Tuser tuser = tuserMapper.selectByPrimaryKey(openId);
        if(tuser != null){
            TuserParam tuserParam = new TuserParam();
            tuserToTuserParam(tuser,tuserParam);
            return tuserParam;
        }else{
            return null;
        }
    }

    @Override
    public boolean addWxUserInfo(Tuser tuser) {
        tuser.setCreateTime(new Date());
        String openId = tuser.getId();
        if(openId == null){
            return false;
        }else{
            Tuser user = tuserMapper.selectByPrimaryKey(openId);
            if(user != null){
                log.info("用户名已存在，不可重复添加");
                return false;
            }else{
                return tuserMapper.insertSelective(tuser) > 0 ? true : false;
            }
        }
    }

    @Override
    public boolean editUserInfo(Tuser tuser) {
        tuser.setLastUpdateTime(new Date());
        int updateNum= tuserMapper.updateByPrimaryKeySelective(tuser);
        if(updateNum > 0){
            return true;
        }else{
            log.info("没有该用户");
            return false;
        }
    }

    @Override
    public Map<String,TuserParam> list(Set<String> set) {
        TuserExample example = new TuserExample();
        example.createCriteria().andIdIn(new ArrayList<>(set));
        List<Tuser> list = tuserMapper.selectByExample(example);
        List<TuserParam> tuserParams = new ArrayList<TuserParam>();
        tuserListToTuserParamList(list,tuserParams);
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }
        Map<String,TuserParam> map = new HashMap<>();
        for (TuserParam user : tuserParams) {
            map.put(user.getId(),user);
        }
        return map;
    }

    @Override
    public Map<String, TuserParam> getUserInfoToMap(String openId) {
        Tuser user = tuserMapper.selectByPrimaryKey(openId);
        TuserParam tuserParam = new TuserParam();
        tuserToTuserParam(user,tuserParam);
        Map<String,TuserParam> map = new HashMap<>();
        if(tuserParam != null){
            map.put(user.getId(),tuserParam);
        }
        return map;
    }

    @Override
    public void register(Tuser tuser) {

        String openId = tuser.getId();
        if(openId != null){
            Tuser user = tuserMapper.selectByPrimaryKey(openId);
            if(user != null){
                log.info("用户名已存在，进行修改操作");
                this.editUserInfo(tuser);
            }else{
                tuser.setCreateTime(new Date());
                int insertNum = tuserMapper.insertSelective(tuser);
                if(insertNum > 0){
                    log.info("用户添加成功");
                }
            }
        }else{
            tuser = null;
        }
    }

    @Override
    public ResultDTO getListByConditon(TuserPageParam pageParam) {

        TuserExample example = getTuserExample(pageParam);
        int count = tuserMapper.countByExample(example);
        List<TuserParam> tuserParamList = new ArrayList<TuserParam>();
        if(count > 0){
            tuserParamList = this.getUserList(pageParam);
        }
        return ResultHandler.handleSuccessWithCount(tuserParamList,count);
    }

    @Override
    public List<TuserParam> getUserList(TuserPageParam pageParam) {
        TuserExample example = getTuserExample(pageParam);
        List<TuserParam> tuserParamList = new ArrayList<TuserParam>();
        if(pageParam.getPageNo() != null && pageParam.getPageSize() != null){
            pageParam.setPageSize(pageParam.getPageSize() != null ? pageParam.getPageSize() : 10);
            pageParam.setPageNo(pageParam.getPageNo() != null ? pageParam.getPageNo() : 1);
            PageHelper.startPage(pageParam.getPageNo(), pageParam.getPageSize());
        }
        List<Tuser> tuserList = tuserMapper.selectByExample(example);
        tuserListToTuserParamList(tuserList,tuserParamList);
        return tuserParamList;
    }

    /**
     * 用户信息转换
     * @param tuserList
     * @param tuserParamList
     */
    private  void tuserListToTuserParamList(List<Tuser> tuserList,List<TuserParam> tuserParamList){
        if(!tuserList.isEmpty()){
            TuserParam tuserParam = null;
            for (int i = 0; i < tuserList.size(); i++) {
                Tuser tuser = tuserList.get(i);
                tuserParam = new TuserParam();
                tuserToTuserParam(tuser,tuserParam);
                tuserParamList.add(tuserParam);
            }
        }


    }

    /**
     * tuser 转 tuserParam
     * @param tuser
     * @param tuserParam
     */
    private void tuserToTuserParam(Tuser tuser,TuserParam tuserParam){
        if (!StringUtils.isEmpty(tuser.getId())) {
            tuserParam.setId(tuser.getId());
            tuserParam.setPubNum(iQuestionService.countByPubPeople(tuser.getId()));
        }else{
            tuserParam.setName("");
            tuserParam.setPubNum(0);
        }
        if (!StringUtils.isEmpty(tuser.getName())) {
            tuserParam.setName(tuser.getName());
        }else{
            tuserParam.setName("");
        }
        if (!StringUtils.isEmpty(tuser.getNickName())) {
            tuserParam.setNickName( tuser.getNickName());
        }else{
            tuserParam.setNickName( "");
        }
        if (tuser.getSex() != null) {
            String sexValue = null;
            switch (tuser.getSex()){
                case 0:
                    sexValue = "未知";
                    break;
                case 1:
                    sexValue = "男";
                    break;
                case 2:
                    sexValue = "女";
                    break;
                default:
                    sexValue = "未知";
                    break;
            }
            tuserParam.setSex(tuser.getSex());
            tuserParam.setSexValue(sexValue);
        }else{
            tuserParam.setSex(0);
            tuserParam.setSexValue("未知");
        }
        if (!StringUtils.isEmpty(tuser.getBirthday())) {
            tuserParam.setBirthday(tuser.getBirthday());
        }else{
            tuserParam.setBirthday("");
        }

        if (!StringUtils.isEmpty(tuser.getProvince())) {
            tuserParam.setProvince(tuser.getProvince());
        }else{
            tuserParam.setProvince("");
        }

        if (!StringUtils.isEmpty(tuser.getCity())) {
            tuserParam.setCity(tuser.getCity());
        }else{
            tuserParam.setCity("");
        }

        if (!StringUtils.isEmpty(tuser.getCountry())) {
            tuserParam.setCountry(tuser.getCountry());
        }else{
            tuserParam.setCountry("");
        }

        if (!StringUtils.isEmpty(tuser.getWxNumber())) {
            tuserParam.setWxNumber(tuser.getWxNumber());
        }else{
            tuserParam.setWxNumber("");
        }

        if (!StringUtils.isEmpty(tuser.getMobile())) {
            tuserParam.setMobile(tuser.getMobile());
        }else{
            tuserParam.setMobile("");
        }

        if (!StringUtils.isEmpty(tuser.getSchool())) {
            tuserParam.setSchool(tuser.getSchool());
        }else{
            tuserParam.setSchool("");
        }

        if (!StringUtils.isEmpty(tuser.getProfession())) {
            tuserParam.setProfession(tuser.getProfession());
        }else{
            tuserParam.setProfession("");
        }

        if (!StringUtils.isEmpty(tuser.getUnionId())) {
            tuserParam.setUnionId(tuser.getUnionId());
        }else{
            tuserParam.setUnionId("");
        }
        Integer indentityType = tuser.getIdentityType();
        if (indentityType != null) {
            tuserParam.setIdentityType(indentityType);
            tuserParam.setIdentity(StaticDataCache.IDENTITY_TYPE_MAP.containsKey(indentityType + "") ?
                    StaticDataCache.IDENTITY_TYPE_MAP.get(indentityType + "") : "未知");
        }else{
            tuserParam.setIdentityType(255);
            tuserParam.setIdentity( "未知");
        }

        if(tuser.getHeadImgUrl() != null){
            tuserParam.setHeadImgUrl(tuser.getHeadImgUrl());
        }

        if(tuser.getCreateTime() != null){
            tuserParam.setCreateTime(tuser.getCreateTime());
        }
        if(tuser.getLastUpdateTime() != null){
            tuserParam.setLastUpdateTime(tuser.getLastUpdateTime());
        }

        if(tuser.getLastLoginTime() != null){
            tuserParam.setLastLoginTime(tuser.getLastLoginTime());
        }
    }
    /**
     * 拼接查询参数
     * @param pageParam 查询参数
     * @return
     */
    private TuserExample getTuserExample(TuserPageParam pageParam) {
        TuserExample example = new TuserExample();
        TuserExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(pageParam.getId())) {
            criteria.andIdIn(Arrays.asList(pageParam.getId().split(",")));
        }
        if (!StringUtils.isEmpty(pageParam.getName())) {
            criteria.andNameLike("%" + pageParam.getName() + "%");
        }
        if (!StringUtils.isEmpty(pageParam.getNickName())) {
            criteria.andNickNameLike("%" + pageParam.getNickName() + "%");
        }
        if (pageParam.getSex() != null) {
            criteria.andSexEqualTo(pageParam.getSex());
        }
        if (!StringUtils.isEmpty(pageParam.getBirthday())) {
            criteria.andBirthdayEqualTo(pageParam.getBirthday());
        }

        if (!StringUtils.isEmpty(pageParam.getProvince())) {
            criteria.andProvinceEqualTo(pageParam.getProvince());
        }

        if (!StringUtils.isEmpty(pageParam.getCity())) {
            criteria.andCityEqualTo(pageParam.getCity());
        }

        if (!StringUtils.isEmpty(pageParam.getCountry())) {
            criteria.andCountryEqualTo(pageParam.getCountry());
        }

        if (!StringUtils.isEmpty(pageParam.getWxNumber())) {
            criteria.andWxNumberLike(pageParam.getWxNumber());
        }

        if (!StringUtils.isEmpty(pageParam.getMobile())) {
            criteria.andMobileLike(pageParam.getMobile());
        }

        if (!StringUtils.isEmpty(pageParam.getSchool())) {
            criteria.andSchoolLike(pageParam.getSchool());
        }

        if (!StringUtils.isEmpty(pageParam.getProfession())) {
            criteria.andProfessionLike(pageParam.getProfession());
        }

        if (!StringUtils.isEmpty(pageParam.getUnionId())) {
            criteria.andUnionIdLike(pageParam.getUnionId());
        }

        if (pageParam.getIdentityType() != null) {
            criteria.andIdentityTypeEqualTo(pageParam.getIdentityType());
        }

        if (!StringUtils.isEmpty(pageParam.getOrderBy())) {
            example.setOrderByClause(pageParam.getOrderBy());
        }

        return example;

    }

    @Override
    public boolean indentityUser(String userId, int identityType) {
        Tuser tuser = new Tuser();
        tuser.setIdentityType(identityType);
        tuser.setId(userId);
        tuser.setLastUpdateTime(new Date());
        int num = tuserMapper.updateByPrimaryKeySelective(tuser);
        if(num > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<TuserGroupDTO> selectIdentityCount(TuserPageParam pageParam) {

        TuserExample example = getTuserExample(pageParam);
        example.setOrderByClause(" identity_type asc ");
        List<TuserGroupDTO> list = tuserMapper.selectIdentityCount(example);
        List<TuserGroupDTO> result = new LinkedList<TuserGroupDTO>();
        Map<String,TuserGroupDTO> identityMap = new HashMap<>();
        int totalCount = 0;
        for (int i = 0; i < list.size(); i++) {
            TuserGroupDTO tuserGroupDTO = list.get(i);
            totalCount += tuserGroupDTO.getCount();
            Integer indentityType = tuserGroupDTO.getIdentityType();
            if(indentityType ==  null){
                indentityType = 255;
                tuserGroupDTO.setIdentityType(indentityType);
            }
            tuserGroupDTO.setIdentity(StaticDataCache.IDENTITY_TYPE_MAP.containsKey(indentityType + "") ?
                    StaticDataCache.IDENTITY_TYPE_MAP.get(indentityType+ "") : "未知");
            identityMap.put(indentityType + "",tuserGroupDTO);
        }
        TuserGroupDTO tuserGroupDTO = new TuserGroupDTO();
        tuserGroupDTO.setIdentityType(0);
        tuserGroupDTO.setIdentity("全部");
        tuserGroupDTO.setCount(totalCount);
        result.add(tuserGroupDTO);
        for (String key :StaticDataCache.IDENTITY_TYPE_MAP.keySet()) {
            if(identityMap.containsKey(key)){
                result.add(identityMap.get(key));
            }else{
                tuserGroupDTO = new TuserGroupDTO();
                tuserGroupDTO.setIdentityType(Integer.valueOf(key));
                tuserGroupDTO.setIdentity(StaticDataCache.IDENTITY_TYPE_MAP.get(key));
                tuserGroupDTO.setCount(0);
                result.add(tuserGroupDTO);
            }
        }
        return result;
    }


}
