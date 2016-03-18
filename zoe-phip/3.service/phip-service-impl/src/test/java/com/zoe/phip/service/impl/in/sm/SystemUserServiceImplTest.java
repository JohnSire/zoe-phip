package com.zoe.phip.service.impl.in.sm;

import com.zoe.phip.model.base.PageList;
import com.zoe.phip.model.base.QueryPage;
import com.zoe.phip.model.base.ServiceResult;
import com.zoe.phip.model.base.ServiceResultT;
import com.zoe.phip.model.sm.SystemUser;
import com.zoe.phip.service.impl.BaseTest;
import com.zoe.phip.service.in.sm.SystemUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zengjiyang on 2016/3/18.
 */
public class SystemUserServiceImplTest extends BaseTest {


    @Autowired
    private SystemUserService systemUserService;

    @Test
    public void addTest(){
        SystemUser user=creteUser("管理员","admin","admin",1);
        ServiceResult result= systemUserService.add(user);
    }

    @Test
    public void addListTest(){
        List<SystemUser> list=new ArrayList<>();
        SystemUser user=creteUser("zjy","zjy","zjy",1);
        list.add(user);
        ServiceResult result= systemUserService.addList(list);
    }


    @Test
    public void pageQueryTest(){
        ServiceResultT<PageList<SystemUser>> resultT= systemUserService.getList(new QueryPage(),SystemUser.class);
        List<SystemUser> list= resultT.getResult().getRows();

    }

    private SystemUser creteUser(String name,String loginName,String passWord,int state){
        SystemUser user=new SystemUser();
        user.setName(name);
        user.setLoginName(loginName);
        user.setState(state);
        user.setPassword(passWord);
        user.setCreateAt(new Date());
        user.setCreateBy(loginName);
        return user;
    }

    @Test
    public void testLogin() throws Exception {
        ServiceResult result= systemUserService.login("zjy","zjy");
    }
}