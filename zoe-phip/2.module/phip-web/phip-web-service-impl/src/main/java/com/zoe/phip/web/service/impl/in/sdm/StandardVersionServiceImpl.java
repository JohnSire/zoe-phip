/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.web.dao.sdm.IStandardVersionMapper;
import com.zoe.phip.web.model.sdm.StandardVersion;
import com.zoe.phip.web.service.sdm.IStandardVersionService;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
@Repository("standardVersionService")
@Service(interfaceClass = IStandardVersionService.class, proxy = "sdpf", dynamic = true)
@ErrorMessage(code = "001", message = "标准版本标识({0})已经存在!")
public class StandardVersionServiceImpl extends BaseInServiceImpl<StandardVersion, IStandardVersionMapper> implements IStandardVersionMapper {

    @Override
    public int add(StandardVersion standardVersion) throws Exception {
        Example example = new Example(StandardVersion.class);
        example.createCriteria().andEqualTo("code", standardVersion.getCode());
        //数据是否存在判断
        int count = getMapper().selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException("001");
        }
        standardVersion.setId(StringUtil.getUUID());
        return super.add(standardVersion);
    }


    @Override
    public int update(StandardVersion standardVersion) throws Exception {
        return 0;
    }

    @Override
    public int getSingleVersion(Map<String, Object> map) {
        return getMapper().getSingleVersion(map);
    }


}