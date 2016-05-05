package com.zoe.phip.code.generator;

import com.zoe.aop.generator.GeneratorFacade;
import org.junit.Test;

import java.io.File;
import java.util.Date;

/**
 * Created by zengjiyang on 2016/2/1.
 */
public class GeneratorTest {
    @Test
    public void test() throws Exception {
        GeneratorFacade facade = new GeneratorFacade();
        facade.getGenerator().addTemplateRootDir(new File("template"));
        facade.deleteOutRootDir();

        facade.generateByTable("PHIP_EHR_DATA_INFO");
    }
}
