package com.zoe.phip.service.impl.in.sm;

import com.alibaba.fastjson.JSON;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.model.sm.SystemUser;
import com.zoe.phip.service.impl.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhangwenbin on 2016/3/29.
 */
public class UserCompetenceServiceImplTest extends BaseTest {

    @Autowired
    private UserCompetenceServiceImpl impl;

    @Test
    public void saveList() throws Exception {

    }

    @Test
    public void getUserListByCompetenceCategory() throws Exception {
        PageList<SystemUser> result = impl.getUserListByCompetenceCategory("b202b8ba-0f93-4f22-a6b7-6e12379bb26f", "", new QueryPage(1, 30));
        System.out.println(JSON.toJSON(result));
    }

    @Test
    public void cancelUserCompetence() throws Exception {

    }

    @Test
    public void checkExists() throws Exception {
        System.out.println();
        System.out.println(impl.checkExists("b202b8ba-0f93-4f22-a6b7-6e12379bb26f", "1761543318e744f781427ab325e6a45b"));
    }

    @Test
    public void checkExists1() throws Exception {

    }

    @Test
    public void getUserCompetenceIdList() throws Exception {
        System.out.println(impl.getUserCompetenceIdList("b202b8ba-0f93-4f22-a6b7-6e12379bb26f"));
    }

    @Test
    public void getCategoriesByUserId() throws Exception {
        System.out.println(impl.getCategoriesByUserId("1761543318e744f781427ab325e6a45b"));
    }

    @Test
    public void getUserListByCompetenceCategory1() throws Exception {

    }
}