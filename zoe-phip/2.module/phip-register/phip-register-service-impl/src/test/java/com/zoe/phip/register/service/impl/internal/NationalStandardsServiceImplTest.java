package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.common.json.JSON;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.register.BaseTest;
import com.zoe.phip.register.model.NationalStandards;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by zhangwenbin on 2016/4/26.
 */
public class NationalStandardsServiceImplTest extends BaseTest {
    @Autowired
    private NationalStandardsServiceImpl impl;

    @Test
    public void add() throws Exception{
        NationalStandards model = new NationalStandards();
        model.setId(UUID.randomUUID().toString());
        model.setCode("10000");
        model.setName("专业技术职务代码");
        model.setCodeSystem("2.16.156.10011.2.3.3.10");
        model.setDictCode("GB/T 8561");
        impl.add(model);
    }

    @Test
    public void update() throws Exception{
        NationalStandards model = new NationalStandards();
        model.setId("e898a9cc-40bc-406a-ae1e-0e859ea3b56f");
        model.setCode("10000");
        model.setName("专业技术职务代码");
        model.setCodeSystem("2.16.156.10011.2.3.3.10");
        model.setDictCode("GB/T 8561");
        model.setDescr("专业技术职务代码");
        impl.update(model);
    }

    @Test
    public void getDataListByPage() throws Exception {
        PageList<NationalStandards> list = impl.getDataListByPage("e",new QueryPage());
        System.out.println(JSON.json(list));
    }

}