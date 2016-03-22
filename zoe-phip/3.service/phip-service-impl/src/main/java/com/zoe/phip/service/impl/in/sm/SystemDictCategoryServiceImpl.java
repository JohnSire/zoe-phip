/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.impl.in.sm;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.entity.BusinessException;
import com.zoe.phip.model.sm.SystemDictCategory;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.in.sm.SystemDictCategoryService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
@Service("systemDictCategoryService")
public class SystemDictCategoryServiceImpl extends BaseInServiceImpl<SystemDictCategory> implements SystemDictCategoryService {

    @Override
    public ServiceResult add(SystemDictCategory entity) {
        return SafeExecuteUtil.execute(
                () -> {
                    Example example = new Example(SystemDictCategory.class);
                    example.createCriteria().andEqualTo("code", entity.getCode());
                    List<SystemDictCategory> list = mapper.selectByExample(example);
                    if (list != null && list.size() > 0) {
                        // TODO: 2016/3/22 错误消息
                        throw new BusinessException("该字典类别({0})已经存在", entity.getCode());
                    } else
                        return super.add(entity);
                });

    }

    @Override
    public ServiceResult update(SystemDictCategory entity) {
        return SafeExecuteUtil.execute(
                () -> {
                    Example example = new Example(SystemDictCategory.class);
                    example.createCriteria().andEqualTo("code", entity.getCode())
                            .andNotEqualTo("id", entity.getId());
                    List<SystemDictCategory> list = mapper.selectByExample(example);
                    if (list != null && list.size() > 0) {
                        // TODO: 2016/3/22 错误消息
                        return null;
                    } else
                        return super.update(entity);
                });
    }
}