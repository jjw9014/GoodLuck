package com.help.server.facade;

import com.help.api.*;
import com.help.server.common.*;
import com.help.server.model.Question;
import com.help.server.service.IQuestionService;
import com.help.server.service.PictureService;
import com.help.server.service.TuserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("questionFacade")
public class QuestionFacadeImpl implements QuestionFacade {
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private AreaFacade areaFacade;
    @Autowired
    private PictureService pictureService;

    @Autowired
    private TuserService tuserService;

    @Override
    public ResultDTO pub(QuestionParam param) {
        CommonUtils.assertEmptyField(param.getTag(), ResultCodeEnum.QUESTION_TAG_IS_NULL);
        CommonUtils.assertEmptyField(param.getPubUserId(), ResultCodeEnum.QUESTION_PUB_USER_IS_NULL);
        CommonUtils.assertEmptyField(param.getRemark(), ResultCodeEnum.QUESTION_REMARK_IS_NULL);

        return ResultHandler.handleSuccess(questionService.submit(param));
    }

    @Override
    public ResultDTO focus(String number, String userId) {
        // todo 防刷逻辑待完善
        CommonUtils.assertEmptyField(number, ResultCodeEnum.QUESTION_NUMBER_IS_NULL);
        CommonUtils.assertEmptyField(userId, ResultCodeEnum.QUESTION_USER_IS_NULL);

        QuestionParam param = new QuestionParam();
        param.setNumber(number);

        Question question = questionService.info(number);
        CommonUtils.assertNullField(question, ResultCodeEnum.QUESTION_NUMBER_NOT_EXIST);

        param.setStars(question.getStars()+1);
        return ResultHandler.handleSuccess(questionService.submit(param));
    }

    @Override
    public ResultDTO updateInfo(QuestionUpdateParam updateParam) {
        CommonUtils.assertEmptyField(updateParam.getNumber(), ResultCodeEnum.QUESTION_NUMBER_IS_NULL);


        QuestionParam param = new QuestionParam();
        BeanUtils.copyProperties(updateParam, param);

        return ResultHandler.handleSuccess(questionService.submit(param));
    }

    @Override
    public ResultDTO resolve(String number, String userId) {
        CommonUtils.assertEmptyField(number, ResultCodeEnum.QUESTION_NUMBER_IS_NULL);
        CommonUtils.assertEmptyField(userId, ResultCodeEnum.QUESTION_USER_IS_NULL);

        QuestionParam param = new QuestionParam();
        param.setNumber(number);
        param.setState(StateEnum.RESOLVED.getCode());

        return ResultHandler.handleSuccess(questionService.submit(param));
    }

    @Override
    public ResultDTO audit(String number, String userId, String auditState) {
        CommonUtils.assertEmptyField(number, ResultCodeEnum.QUESTION_NUMBER_IS_NULL);
        CommonUtils.assertEmptyField(userId, ResultCodeEnum.QUESTION_USER_IS_NULL);
        CommonUtils.assertEmptyField(auditState, ResultCodeEnum.QUESTION_AUDIT_STATE_IS_NULL);

        QuestionParam param = new QuestionParam();
        param.setNumber(number);
        param.setAuditState(auditState);

        return ResultHandler.handleSuccess(questionService.submit(param));
    }

    @Override
    public ResultDTO<List> list(QuestionPageParam pageParam) {

        int count = questionService.count(pageParam);

        List<QuestionParam> respList = new ArrayList<>();
        if (count > 0) {
            List<Question> list = questionService.list(pageParam);
            Set<String> pubUserSet = new HashSet<>();

            for (Question question : list) {
                pubUserSet.add(question.getPubUserId());
            }
            Map<String, TuserParam> tuserMap = tuserService.list(pubUserSet);
            for (Question question : list) {
                respList.add(question2Resp(question,tuserMap));
            }

        }

        return ResultHandler.handleSuccessWithCount(respList, count);
    }

    private QuestionParam question2Resp(Question question,Map<String, TuserParam> tuserMap) {
        QuestionParam param = new QuestionParam();
        BeanUtils.copyProperties(question, param);

        param.setPubTimeStr(param.getPubTime()==null?null:DateUtils.formatDate(param.getPubTime()));
        param.setAuditTimeStr(param.getAuditTime()==null?null:DateUtils.formatDate(param.getAuditTime()));
        param.setLastUpdateTimeStr(param.getLastUpdateTime()==null?null:DateUtils.formatDate(param.getLastUpdateTime()));
        param.setProvinceName(StringUtils.isEmpty(param.getProvince())?"":getAreaByCode(param.getProvince()));
        param.setCityName(StringUtils.isEmpty(param.getCity())?"":getAreaByCode(param.getCity()));
        param.setDistrictName(StringUtils.isEmpty(param.getDistrict())?"":getAreaByCode(param.getDistrict()));

        // 设置下载地址
        param.setPicList(getPictureByMd5s(param.getPicMd5()));

        String pubUserId = param.getPubUserId();
        if(pubUserId != null && tuserMap.containsKey(pubUserId)){
            TuserParam user = tuserMap.get(pubUserId);
            param.setNickName(user.getNickName());
            param.setHeadImgUrl(user.getHeadImgUrl());
            param.setIdentity(user.getIdentity());
        }else{
            param.setNickName("");
            param.setHeadImgUrl("");
            param.setIdentity("未知");
        }

        return param;
    }

    private List<PictureParam> getPictureByMd5s(String picMd5) {
        if (StringUtils.isBlank(picMd5)) {
            return new ArrayList<>();
        }

        return pictureService.list(Arrays.asList(picMd5.split(",")));
    }

    private String getAreaByCode(String code) {
        ResultDTO<AreaParam> resultDTO = areaFacade.getAreaByCode(code);
        if (resultDTO != null && resultDTO.isSuccess() && resultDTO.getData() != null) {
            return resultDTO.getData().getAreaName();
        }

        return null;
    }

    @Override
    public ResultDTO<QuestionParam> info(String number) {
        CommonUtils.assertEmptyField(number, ResultCodeEnum.QUESTION_NUMBER_IS_NULL);
        Question question = questionService.info(number);

        CommonUtils.assertNullField(question, ResultCodeEnum.QUESTION_NUMBER_NOT_EXIST);


        QuestionParam questionParam = question2Resp(question,tuserService.getUserInfoToMap(question.getPubUserId()));
        return ResultHandler.handleSuccess(questionParam);
    }

    @Override
    public ResultDTO<QuestionCountDTO> count() {
        QuestionPageParam newAddPageParam = new QuestionPageParam();
        newAddPageParam.setState(StateEnum.UNRESOLVED.getCode());
        newAddPageParam.setAuditStates(Arrays.asList(AuditStateEnum.AUDITING.getCode(), AuditStateEnum.AUDITED_FAILURE.getCode()));

        QuestionPageParam unresolvedPageParam = new QuestionPageParam();
        unresolvedPageParam.setState(StateEnum.UNRESOLVED.getCode());
        unresolvedPageParam.setAuditStates(Arrays.asList(AuditStateEnum.AUIDTED.getCode(), AuditStateEnum.VERIFIED.getCode(), AuditStateEnum.VERIFIED_FAILURE.getCode()));

        QuestionPageParam resolvedPageParam = new QuestionPageParam();
        resolvedPageParam.setState(StateEnum.RESOLVED.getCode());

        QuestionCountDTO countDTO = new QuestionCountDTO();
        countDTO.setNewAddCount(questionService.count(newAddPageParam));
        countDTO.setUnresolvedCount(questionService.count(unresolvedPageParam));
        countDTO.setResolvedCount(questionService.count(resolvedPageParam));

        return ResultHandler.handleSuccess(countDTO);
    }

    @Override
    public ResultDTO depreate(String number, String userId) {
        CommonUtils.assertEmptyField(number, ResultCodeEnum.QUESTION_NUMBER_IS_NULL);
        CommonUtils.assertEmptyField(userId, ResultCodeEnum.QUESTION_USER_IS_NULL);

        QuestionParam param = new QuestionParam();
        param.setNumber(number);
        param.setState(StateEnum.DELETED.getCode());

        return ResultHandler.handleSuccess(questionService.submit(param));
    }
}
