package com.zoe.phip.service.impl.in.sm;

import com.alibaba.dubbo.common.json.JSON;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.model.sm.SystemDictCategory;
import com.zoe.phip.service.impl.BaseTest;
import com.zoe.phip.service.in.sm.SystemDictCategoryService;
import com.zoe.phip.service.in.sm.SystemUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by 文彬 on 2016/3/22.
 */
public class SystemDictCategoryServiceImplTest extends BaseTest {

    @Autowired
    private SystemDictCategoryService service;

    @Test
    public void getDictCategories() throws Exception {
        QueryPage page = new QueryPage();
        page.setPageNum(1);
        page.setPageSize(2);
        ServiceResult sr = service.getDictCategories(null, "", page);
    }

    @Test
    public void getDictCategory() throws Exception {
        ServiceResult sr = service.getDictCategory(null, "DICTIONARY_SOURCE");
        System.out.print(JSON.json(sr));
    }
}