package com.zoe.phip.service.impl.in.sm;

import com.zoe.phip.model.sm.SystemParameter;
import com.zoe.phip.service.impl.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class SystemParameterServiceImplTest extends BaseTest {

    @Autowired
    private SystemParameterServiceImpl systemParameterService;

    @Test
    public void testAdd() throws Exception {
        SystemParameter systemParameter = new SystemParameter();
        systemParameter.setId("123");
        systemParameter.setCode("123");
        systemParameter.setDescr("测试数据");
        systemParameter.setCreateAt(new Date());
        systemParameter.setName("测试");
        int serviceResult = systemParameterService.add(systemParameter);
    }

    @Test
    public void updateListTest() throws Exception{
        List<SystemParameter> list= systemParameterService.getList(null);

        systemParameterService.updateList(list);
    }
}
