package com.help.server.facade;

import com.help.api.ResultDTO;
import com.help.api.SuggestFacade;
import com.help.api.SuggestParam;
import com.help.server.common.CommonUtils;
import com.help.server.common.ResultCodeEnum;
import com.help.server.common.ResultHandler;
import com.help.server.model.Suggest;
import com.help.server.service.ISuggestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SuggestFacadeImpl implements SuggestFacade {
    @Autowired
    private ISuggestService suggestService;

    @Override
    public ResultDTO submit(SuggestParam suggestParam) {
        CommonUtils.assertEmptyField(suggestParam.getUserId(), ResultCodeEnum.SUGGUEST_USER_ID_IS_NULL);
        CommonUtils.assertEmptyField(suggestParam.getRemark(), ResultCodeEnum.SUGGUEST_REMARK_IS_NULL);

        suggestParam.setUserId(suggestParam.getUserId().trim());
        suggestParam.setRemark(suggestParam.getRemark().trim());
        if (!StringUtils.isEmpty(suggestParam.getMobile())) {
            suggestParam.setMobile(suggestParam.getMobile().trim());
        }

        Suggest suggest = new Suggest();
        BeanUtils.copyProperties(suggestParam, suggest);

        return ResultHandler.handleSuccess(suggestService.submit(suggest));
    }

    @Override
    public ResultDTO<List> list(int pageSize, int pageNo) {

        pageSize = pageSize<10 ? 10 : pageSize;
        pageNo = pageNo<0 ? 1 : pageNo;

        int count = suggestService.count();
        List<Suggest> list = suggestService.list(pageSize, pageNo);

        return ResultHandler.handleSuccessWithCount(list, count);
    }
}
