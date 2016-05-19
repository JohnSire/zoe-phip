package com.zoe.phip.register.service.impl;

import com.zoe.phip.register.BaseTest;
import com.zoe.phip.register.service.impl.external.DictRegisterImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by fangqin on 2016/5/9.
 */
public class DictRegisterImplTest extends BaseTest {


    @Autowired
    private DictRegisterImpl dictRegister;

    @Test
    public void testAddDictCatalogRequest() throws Exception {
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<data>\n" +
                "    <Code value=\"test00101\"/>\n" +
                "    <Name value=\"test00101\"/>\n" +
                "    <ParentId value=\"\"/>\n" +
                "    <Type value=\"0\"/>\n" +
                "</data>";

        String result = dictRegister.addDictCatalogRequest(input);
        System.out.println(result);
    }

    @Test
    public void testUpdateDictCatalogRequest() throws Exception {
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<data>\n" +
                "    <Id value=\"ab3836a8580148a8924757a7b4e32708\"/>\n" +
                "    <Code value=\"test\"/>\n" +
                "    <Name value=\"test\"/>\n" +
                "    <ParentId value=\"0\"/>\n" +
                "    <Type value=\"0\"/>\n" +
                "</data>";

        String result = dictRegister.updateDictCatalogRequest(input);
        System.out.println(result);
    }

    @Test
    public void testDictCatalogDetailQuery() throws Exception {
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<data>\n" +
                "    <Id value=\"ab3836a8580148a8924757a7b4e32708\"/>\n" +
                "</data>";

        String result = dictRegister.dictCatalogDetailQuery(input);
        System.out.println(result);
    }

    @Test
    public void testDictCatalogDetailDelete() throws Exception {
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<data>\n" +
                "    <Id value=\"4ca5537e98984af481d4cae70aa5ab39\"/>\n" +
                "</data>";

        String result = dictRegister.dictCatalogDetailDelete(input);
        System.out.println(result);
    }

    @Test
    public void testAddDictItemRequest() throws Exception {
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<data>\n" +
                "    <Code value=\"01\"/>\n" +
                "    <Name value=\"test\"/>\n" +
                "    <FkCatalogId value=\"d8996efcb98f49549153234f7f242b74\"/>\n" +
                "</data>";

        String result = dictRegister.addDictItemRequest(input);
        System.out.println(result);
    }

    @Test
    public void testUpdateDictItemRequest() throws Exception {
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<data>\n" +
                "    <Id value=\"ee9c58edfcfa4d19b0005354759b5850\"/>\n" +
                "    <Code value=\"01\"/>\n" +
                "    <Name value=\"test1\"/>\n" +
                "    <FkCatalogId value=\"d8996efcb98f49549153234f7f242b74\"/>\n" +
                "</data>";

        String result = dictRegister.updateDictItemRequest(input);
        System.out.println(result);
    }

    @Test
    public void testDictItemDetailQuery() throws Exception {
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<data>\n" +
                "    <Id value=\"ee9c58edfcfa4d19b0005354759b5850\"/>\n" +
                "</data>";

        String result = dictRegister.dictItemDetailQuery(input);
        System.out.println(result);
    }

    @Test
    public void testDictItemDetailDelete() throws Exception {
        String input = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<data>\n" +
                "    <Id value=\"ee9c58edfcfa4d19b0005354759b5850\"/>\n" +
                "</data>";

        String result = dictRegister.dictItemDetailDelete(input);
        System.out.println(result);
    }
}