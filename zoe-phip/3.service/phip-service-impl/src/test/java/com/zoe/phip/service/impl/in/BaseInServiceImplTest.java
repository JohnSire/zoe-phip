package com.zoe.phip.service.impl.in;

import com.zoe.phip.model.base.PageList;
import com.zoe.phip.model.base.QueryPage;
import com.zoe.phip.model.base.ServiceResult;
import com.zoe.phip.model.base.ServiceResultT;
import com.zoe.phip.model.demo.Dept;
import com.zoe.phip.service.in.demo.CountryService;
import com.zoe.phip.service.in.demo.DeptService;
import com.zoe.phip.service.in.demo.OrdersService;
import com.zoe.phip.service.in.demo.PersonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * Created by zengjiyang on 2016/3/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring-mybatis.xml"})
public class BaseInServiceImplTest {


    @Autowired
    private DeptService deptBaseInService;

    @Autowired
    private CountryService countryBaseInService;

    @Autowired
    private PersonService personService;

    @Autowired
    private OrdersService ordersService;

    @Test
    public void testAdd() throws Exception {
        ServiceResultT<List<Dept>> resultT=deptBaseInService.getList();
        QueryPage page=new QueryPage(1,5);
        ServiceResultT<PageList<Dept>> pageResult= deptBaseInService.getList(page);

        ServiceResult result=deptBaseInService.getById("1");
        Assert.assertTrue(result!=null);
    }

    @Test
    public void testCountry() throws Exception {

        ServiceResult result=countryBaseInService.getById("2");
    }

    @Test
    public void orderTest(){
        try {
            ServiceResult result=ordersService.getList();
        }catch (Exception e){
        }
    }
}