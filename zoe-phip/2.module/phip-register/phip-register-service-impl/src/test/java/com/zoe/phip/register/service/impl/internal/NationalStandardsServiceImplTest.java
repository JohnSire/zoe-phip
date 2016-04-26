package com.zoe.phip.register.service.impl.internal;

import com.zoe.phip.register.BaseTest;
import com.zoe.phip.register.model.NationalStandards;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by zengjiyang on 2016/4/26.
 */
public class NationalStandardsServiceImplTest extends BaseTest {


    @Autowired
    private NationalStandardsServiceImpl nationalStandardsService;

    @Test
    public void testAdd() throws Exception {

        NationalStandards nationalStandards=new NationalStandards();
        nationalStandards.setName("123");
        nationalStandards.setCode("456");
        nationalStandardsService.add(nationalStandards);
    }
}