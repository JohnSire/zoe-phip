package com.zoe.phip.service.impl.in.sm;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.model.sm.SystemDictItem;
import com.zoe.phip.service.impl.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by 文彬 on 2016/3/22.
 */
public class SystemDictItemServiceImplTest extends BaseTest {


    @Autowired
    private SystemDictItemServiceImpl service;

    @Test
    public void categoryExists() throws Exception {

    }

    @Test
    public void getDictItems() throws Exception {
        PageList<SystemDictItem> sr = service.getDictItems("5D01A80C08CB4ACA88679457A3358A94", "3", new QueryPage(1, 30));
        System.out.println(com.alibaba.fastjson.JSON.toJSON(sr));
    }

    @Test
    public void getDictItems1() throws Exception {
        List<SystemDictItem> sr = service.getDictItems("1", "%GB/T%");
        System.out.println(com.alibaba.fastjson.JSON.toJSON(sr));
    }

    @Test
    public void getDictItemsByCategoryCode() throws Exception {
        QueryPage page = new QueryPage();
        page.setPageNum(1);
        page.setPageSize(5);
        PageList<SystemDictItem> sr = service.getDictItemsByCategoryCode("DICTIONARY_SOURCE111", page);
        System.out.println(com.alibaba.fastjson.JSON.toJSON(sr));
    }

    @Test
    public void getDictItemByCategoryId() throws Exception {
        SystemDictItem sr = service.getDictItemByCategoryId("1", "11");
        System.out.println(com.alibaba.fastjson.JSON.toJSON(sr));
    }

    @Test
    public void getDictItemByCategoryCode() throws Exception {
        List<SystemDictItem> sr = service.getDictItemsByCategoryCode("DICTIONARY_SOURCE");
        System.out.println(com.alibaba.fastjson.JSON.toJSON(sr));
    }

    @Test
    public void getDictItemsByCategoryCode1() throws Exception {
        SystemDictItem sr = service.getDictItemByCategoryCode("DICTIONARY_SOURCE", "12");
        System.out.println(com.alibaba.fastjson.JSON.toJSON(sr));
    }
}