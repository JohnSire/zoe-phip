package com.zoe.phip.service.impl.in.sm;

import com.alibaba.fastjson.JSON;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.model.sm.SystemUser;
import com.zoe.phip.service.impl.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by zhangwenbin on 2016/3/29.
 */
public class MenuCompetenceServiceImplTest extends BaseTest {

    @Autowired
    private MenuCompetenceServiceImpl impl;


    @Test
    public void saveList() throws Exception {

    }

    @Test
    public void getMenuListByCompetenceCategory() throws Exception {
        PageList<MenuData> result = impl.getMenuListByCompetenceCategory("b202b8ba-0f93-4f22-a6b7-6e12379bb26f", "", new QueryPage(1, 30));
        System.out.print(JSON.toJSON(result));
    }

    @Test
    public void getMenuCompetenceIdList() throws Exception {

    }

    @Test
    public void cancelMenuCompetence() throws Exception {

    }

    @Test
    public void checkExists() throws Exception {

    }

    @Test
    public void checkExists1() throws Exception {

    }

    @Test
    public void getCategoriesByMenuId() throws Exception {

    }

    @Test
    public void checkExists2() throws Exception {

    }

    @Test
    public void getMenuListByCompetenceCategory1() throws Exception {

    }
}