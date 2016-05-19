package com.zoe.phip.register.service.impl.internal;

import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.register.BaseTest;
import com.zoe.phip.register.model.DictCatalog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by zhangwenbin on 2016/4/28.
 */
public class DictRegisterInImplTest extends BaseTest {

    @Autowired
    private DictRegisterInImpl dictInImpl;

    @Test
    public void addDictCatalogRequest() throws Exception {

    }

    @Test
    public void updateDictCatalogRequest() throws Exception {

    }

    @Test
    public void dictCatalogDetailQuery() throws Exception {

    }

    @Test
    public void dictCatalogDetailDelete() throws Exception {

    }

    @Test
    public void dictCatalogListQuery() throws Exception {

    }

    @Test
    public void getDictCatalogList() throws Exception {

    }

    @Test
    public void selectChildCountById() throws Exception {

    }

    @Test
    public void dictCatalogDetailQueryById() throws Exception {

    }

    @Test
    public void addDictItemRequest() throws Exception {

    }

    @Test
    public void updateDictItemRequest() throws Exception {

    }

    @Test
    public void dictItemDetailQuery() throws Exception {

    }

    @Test
    public void dictItemDetailDelete() throws Exception {

    }

    @Test
    public void dictItemListQuery() throws Exception {

    }

    @Test
    public void dictItemDetailQueryById() throws Exception {

    }

    @Test
    public void testUpdateDictWithFkCatalog() {
        int result = dictInImpl.updateDictWithFkCatalog("","");
        System.out.println(result);
    }

    @Test
    public void testDictCatalogTreeQuery(){
        PageList<DictCatalog> result = dictInImpl.dictCatalogTreeQuery("test01","");
        System.out.println(result);
    }

    @Test
    public void testDictListWithoutFkCatalog(){
        PageList<DictCatalog> result = dictInImpl.dictListWithoutFkCatalog(new QueryPage(),"");
        System.out.println(result);
    }
}