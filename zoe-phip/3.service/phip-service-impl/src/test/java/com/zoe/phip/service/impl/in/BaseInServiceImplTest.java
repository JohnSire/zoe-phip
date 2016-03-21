package com.zoe.phip.service.impl.in;

import com.zoe.phip.infrastructure.logger.Logger;
import com.zoe.phip.model.base.PageList;
import com.zoe.phip.model.base.QueryPage;
import com.zoe.phip.model.base.ServiceResult;
import com.zoe.phip.model.base.ServiceResultT;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by zengjiyang on 2016/3/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring-mybatis.xml"})
public class BaseInServiceImplTest {


//    @Autowired
//    private Logger logger;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BaseInServiceImplTest.class);


    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testAdd() throws Exception {
        logger.info("info123");
        logger.debug("debug456");
        logger.error("error");
        logger.warn("warn");
    }

    @Test
    public void testCountry() throws Exception {


    }

    @Test
    public void orderTest(){

    }

    @Test
    public void insertListTest(){

    }

    @Test
    public void deleteByIdsTest(){



    }
}