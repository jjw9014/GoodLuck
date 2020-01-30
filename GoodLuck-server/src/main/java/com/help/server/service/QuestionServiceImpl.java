package com.help.server.service;

import com.github.pagehelper.PageHelper;
import com.help.api.QuestionPageParam;
import com.help.api.QuestionParam;
import com.help.server.common.AuditStateEnum;
import com.help.server.common.DateUtils;
import com.help.server.common.StateEnum;
import com.help.server.dao.QuestionMapper;
import com.help.server.model.Question;
import com.help.server.model.QuestionExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Override
    @Transactional
    public int submit(QuestionParam param) {
        Question question = new Question();
        BeanUtils.copyProperties(param, question);

        if (StringUtils.isEmpty(question.getNumber())) {
            question.setNumber(generateNumber(param.getPubUserId()));
            question.setState(StateEnum.UNRESOLVED.getCode());
            question.setAuditState(AuditStateEnum.AUDITING.getCode());
            question.setPubTime(Calendar.getInstance().getTime());
            question.setLastUpdateTime(Calendar.getInstance().getTime());
            question.setStars(0);
            return questionMapper.insert(question);
        } else {
            question.setLastUpdateTime(Calendar.getInstance().getTime());
            return questionMapper.updateByPrimaryKeySelective(question);
        }
    }

    private String generateNumber(String userId) {
        String dateStr = DateUtils.formatDateToString(Calendar.getInstance().getTime());// 10位
        String userStr = userId==null? "000000" : userId.replace("_", "").
                replace("-", "").substring(0, 6);// 6位
        String millisStr = String.valueOf(Calendar.getInstance().getTimeInMillis() % 1000000); // 6位
        return String.format("%s-%s-%s", dateStr, userStr, millisStr);
    }

    @Override
    public List<Question> list(QuestionPageParam pageParam) {
        pageParam.setPageSize(pageParam.getPageSize()<10 ? 10 : pageParam.getPageSize());
        pageParam.setPageNo(pageParam.getPageNo()<0 ? 1 : pageParam.getPageNo());

        //注意此方法后面紧跟着mybatis查询方法
        PageHelper.startPage(pageParam.getPageNo(), pageParam.getPageSize());
        QuestionExample example = getQuestionExample(pageParam);

        return questionMapper.selectByExample(example);
    }

    private QuestionExample getQuestionExample(QuestionPageParam pageParam) {
        QuestionExample example = new QuestionExample();
        QuestionExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(pageParam.getTag())) {
            criteria.andTagLike("%" + pageParam.getTag() + "%");
        }
        if (pageParam.getPubTime() != null) {
            criteria.andPubTimeEqualTo(pageParam.getPubTime());
        }
        if (pageParam.getStarsMin() > 0) {
            criteria.andStarsGreaterThanOrEqualTo(pageParam.getStarsMin());
        }
        if (pageParam.getStarsMax() > 0) {
            criteria.andStarsLessThanOrEqualTo(pageParam.getStarsMax());
        }
        if (!StringUtils.isEmpty(pageParam.getState())) {
            criteria.andStateEqualTo(pageParam.getState());
        }
        if (!StringUtils.isEmpty(pageParam.getAuditState())) {
            criteria.andAuditStateEqualTo(pageParam.getAuditState());
        }
        if (!CollectionUtils.isEmpty(pageParam.getAuditStates())) {
            criteria.andAuditStateIn(pageParam.getAuditStates());
        }
        if (!StringUtils.isEmpty(pageParam.getNumber())) {
            criteria.andNumberEqualTo(pageParam.getNumber());
        }
        if (!StringUtils.isEmpty(pageParam.getPubUserId())) {
            criteria.andPubUserIdEqualTo(pageParam.getPubUserId());
        }
        if (!StringUtils.isEmpty(pageParam.getProvince())) {
            criteria.andProvinceEqualTo(pageParam.getProvince());
        }
        if (!StringUtils.isEmpty(pageParam.getCity())) {
            criteria.andCityEqualTo(pageParam.getCity());
        }
        if (!StringUtils.isEmpty(pageParam.getDistrict())) {
            criteria.andDistrictEqualTo(pageParam.getDistrict());
        }
        if (!StringUtils.isEmpty(pageParam.getStreet())) {
            criteria.andStreetEqualTo(pageParam.getStreet());
        }
        if (!StringUtils.isEmpty(pageParam.getMobile())) {
            criteria.andMobileEqualTo(pageParam.getMobile());
        }

        if (!StringUtils.isEmpty(pageParam.getOrderBy())) {
            example.setOrderByClause(pageParam.getOrderBy());
        }
        return example;
    }

    @Override
    public int count(QuestionPageParam pageParam) {
        QuestionExample example = getQuestionExample(pageParam);
        example.setOrderByClause(null);

        return questionMapper.countByExample(example);
    }

    @Override
    public Question info(String number) {
        return questionMapper.selectByPrimaryKey(number);
    }
}
