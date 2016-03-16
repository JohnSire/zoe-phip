package com.zoe.phip.code.generator;

import com.zoe.aop.generator.GeneratorFacade;
import org.junit.Test;

import java.io.File;

/**
 * Created by zengjiyang on 2016/2/1.
 */
public class GeneratorTest {
    @Test
    public void test() throws Exception{
        GeneratorFacade facade = new GeneratorFacade();
        facade.getGenerator().addTemplateRootDir(new File("template"));
        facade.deleteOutRootDir();
        facade.generateByAllTable();
    }

    @Test
    public void stringUtil(){
        String aa="java.lang.string";
        int index= aa.lastIndexOf(".");
        String b= aa.substring(index+1);
        System.out.println(b);
    }
}
