package com.zoe.phip.service.impl.in.sm;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.model.sm.SystemParameter;
import com.zoe.phip.service.impl.BaseTest;
import com.zoe.phip.service.in.sm.SystemParameterService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by Administrator on 2016/3/22.
 */
public class SystemParameterServiceImplTest extends BaseTest {

    @Autowired
    private SystemParameterService systemParameterService;

    @Test
    public void testAdd() {
        SystemParameter systemParameter = new SystemParameter();
        systemParameter.setId("123");
        systemParameter.setCode("123");
        systemParameter.setDescr("测试数据");
        systemParameter.setCreateAt(new Date());
        systemParameter.setName("测试");
        ServiceResult serviceResult = systemParameterService.add(null, systemParameter);
    }
}
