package com.zoe.phip.web.service.impl.in.sdm;

import com.alibaba.fastjson.JSON;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.web.model.sdm.StNormSourceInfo;
import com.zoe.phip.web.service.impl.in.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by zhangwenbin on 5/9/2016.
 */

public class StNormSourceInfoServiceImplTest extends BaseTest {

    @Autowired
    private StNormSourceInfoServiceImpl impl;

    @Test
    public void add() throws Exception{
        StNormSourceInfo info = new StNormSourceInfo();
        info.setId(UUID.randomUUID().toString());
        info.setCode("0107");
        info.setName("决策指标体系");
        info.setDescr("决策指标体系");
        info.setFkDictId("b083430ad1f64a809dfffcf4b143bc39");
        impl.addNormSource(info);
    }

    @Test
    public void getDataPageList() throws Exception {

        String key = null;
        QueryPage queryPage = new QueryPage(1,50);
        PageList<StNormSourceInfo> pageList =impl.getDataPageList(key,queryPage);
        System.out.println();
        System.out.println(JSON.toJSON(pageList));
    }

    @Test
    public void getById() throws Exception {
        StNormSourceInfo normById = impl.getNormById("9c6fc1ef-a047-485a-a309-614fc5e601f0");
    }

}