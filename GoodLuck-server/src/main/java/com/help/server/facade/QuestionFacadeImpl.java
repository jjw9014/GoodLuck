package com.help.server.facade;

import com.help.api.*;
import com.help.server.common.*;
import com.help.server.model.Question;
import com.help.server.service.IQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("questionFacade")
public class QuestionFacadeImpl implements QuestionFacade {
    @Autowired
    private IQuestionService questionService;

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
        param.setPubUserId(userId);

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
        param.setPubUserId(userId);
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
        List<Question> list = questionService.list(pageParam);

        return ResultHandler.handleSuccessWithCount(list, count);
    }

    @Override
    public ResultDTO<QuestionParam> info(String number) {
        CommonUtils.assertEmptyField(number, ResultCodeEnum.QUESTION_NUMBER_IS_NULL);
        Question question = questionService.info(number);

        CommonUtils.assertNullField(question, ResultCodeEnum.QUESTION_NUMBER_NOT_EXIST);

        QuestionParam questionParam = new QuestionParam();
        BeanUtils.copyProperties(question, questionParam);
        return ResultHandler.handleSuccess(questionParam);
    }

    @Override
    public ResultDTO<QuestionCountDTO> count() {
        QuestionPageParam newAddPageParam = new QuestionPageParam();
        newAddPageParam.setAuditState(AuditStateEnum.AUDITING.getCode());

        QuestionPageParam unresolvedPageParam = new QuestionPageParam();
        unresolvedPageParam.setState(StateEnum.UNRESOLVED.getCode());
        unresolvedPageParam.setAuditStates(Arrays.asList(AuditStateEnum.AUIDTED.getCode(), AuditStateEnum.VERIFIED.getCode()));

        QuestionPageParam resolvedPageParam = new QuestionPageParam();
        resolvedPageParam.setState(StateEnum.RESOLVED.getCode());

        QuestionCountDTO countDTO = new QuestionCountDTO();
        countDTO.setNewAddCount(questionService.count(newAddPageParam));
        countDTO.setUnresolvedCount(questionService.count(unresolvedPageParam));
        countDTO.setResolvedCount(questionService.count(resolvedPageParam));

        return ResultHandler.handleSuccess(countDTO);
    }
}
