package com.zoe.phip.service.impl.in;

import com.zoe.phip.model.base.ServiceResult;
import com.zoe.phip.model.demo.Country;
import com.zoe.phip.model.demo.Dept;
import com.zoe.phip.service.in.BaseInService;
import com.zoe.phip.service.in.demo.DeptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by zengjiyang on 2016/3/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring-mybatis.xml"})
public class BaseInServiceImplTest {


    @Autowired
    private DeptService deptBaseInService;

    @Autowired
    private BaseInService<Country> countryBaseInService;

    @Test
    public void testAdd() throws Exception {

        ServiceResult result=deptBaseInService.deleteById("01d75140b4844e159407be2ebf62973a");
    }

    @Test
    public void testCountry() throws Exception {

        ServiceResult result=countryBaseInService.deleteById("1");
    }
}