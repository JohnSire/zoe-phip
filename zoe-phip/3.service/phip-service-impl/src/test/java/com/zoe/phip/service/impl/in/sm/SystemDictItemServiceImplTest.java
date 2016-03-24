package com.zoe.phip.service.impl.in.sm;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.JSONConverter;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.service.impl.BaseTest;
import com.zoe.phip.service.in.sm.SystemDictItemService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by 文彬 on 2016/3/22.
 */
public class SystemDictItemServiceImplTest extends BaseTest {


    @Autowired
    private SystemDictItemService service;

    @Test
    public void categoryExists() throws Exception {

    }

    @Test
    public void getDictItems() throws Exception {
        ServiceResult sr = service.getDictItems(null,"1", "%5%", new QueryPage(1, 5));
        System.out.println(com.alibaba.fastjson.JSON.toJSON(sr));
    }

    @Test
    public void getDictItems1() throws Exception {
        ServiceResult sr = service.getDictItems(null,"1", "%GB/T%");
        System.out.println(com.alibaba.fastjson.JSON.toJSON(sr));
    }

    @Test
    public void getDictItemsByCategoryCode() throws Exception {
        QueryPage page = new QueryPage();
        page.setPageNum(1);
        page.setPageSize(5);
        ServiceResult sr = service.getDictItemsByCategoryCode(null,"DICTIONARY_SOURCE111", page);
        System.out.println(com.alibaba.fastjson.JSON.toJSON(sr));
    }

    @Test
    public void getDictItemByCategoryId() throws Exception {
        ServiceResult sr = service.getDictItemByCategoryId(null,"1", "11");
        System.out.println(com.alibaba.fastjson.JSON.toJSON(sr));
    }

    @Test
    public void getDictItemByCategoryCode() throws Exception {
        ServiceResult sr = service.getDictItemsByCategoryCode(null,"DICTIONARY_SOURCE");
        System.out.println(com.alibaba.fastjson.JSON.toJSON(sr));
    }

    @Test
    public void getDictItemsByCategoryCode1() throws Exception {
        ServiceResult sr = service.getDictItemByCategoryCode(null,"DICTIONARY_SOURCE", "12");
        System.out.println(com.alibaba.fastjson.JSON.toJSON(sr));
    }
}