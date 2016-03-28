package com.zoe.phip.service.impl.in.sm;

import com.alibaba.dubbo.common.json.JSON;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.model.sm.SystemDictCategory;
import com.zoe.phip.service.impl.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 文彬 on 2016/3/22.
 */
public class SystemDictCategoryServiceImplTest extends BaseTest {

    @Autowired
    private SystemDictCategoryServiceImpl service;

    @Test
    public void getDictCategories() throws Exception {
        QueryPage page = new QueryPage();
        page.setPageNum(1);
        page.setPageSize(2);
        PageList<SystemDictCategory> sr = service.getDictCategories("", page);
    }

    @Test
    public void getDictCategory() throws Exception {
        SystemDictCategory sr = service.getDictCategory("DICTIONARY_SOURCE");
        System.out.print(JSON.json(sr));
    }
}