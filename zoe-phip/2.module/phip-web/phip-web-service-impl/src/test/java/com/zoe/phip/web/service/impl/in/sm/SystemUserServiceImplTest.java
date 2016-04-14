package com.zoe.phip.web.service.impl.in.sm;

import com.zoe.phip.web.model.sm.SystemUser;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zengjiyang on 2016/4/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class SystemUserServiceImplTest {


    @Autowired
    private SystemUserServiceImpl systemUserService;


/*    @org.junit.Test
    public void testUpdatePassword() throws Exception {
        systemUserService.updateState("123",1);

    }*/
    @org.junit.Test
    public void testAdd()throws Exception {
        SystemUser s = new SystemUser();
       // s.setName("1");
       s.setPassword("1");
        s.setLoginName("admin");
        s.setName("1");
        s.setState(1);

     /*   Field[] fields = SystemUser.class.getDeclaredFields();

        for (Field f : fields) {
            String filedName = f.getName();
            System.out.println("属性名称:【" + filedName + "】");

            //1、获取属性上的指定类型的注释
            Annotation annotation = f.getAnnotation(XmlElement.class);

            //有该类型的注释存在
            if (annotation != null) {
                //强制转化为相应的注释
                XmlElement xmlElement = (XmlElement) annotation;
                //3、获取属性上的指定类型的注释的指定方法
                //具体是不是默认值可以去查看源代码
                if (xmlElement.name().equals("##default")) {
                    System.out.println("属性【" + filedName + "】注释使用的name是默认值: " + xmlElement.name());
                } else {
                    System.out.println("属性【" + filedName + "】注释使用的name是自定义的值: " + xmlElement.name());
                }
            }

            //2、获取属性上的所有注释
            Annotation[] allAnnotations = f.getAnnotations();

            for (Annotation an : allAnnotations) {

                Class annotationType = an.annotationType();

                System.out.println("属性【" + filedName + "】的注释类型有: " + annotationType);
            }
            System.out.println("----------华丽的分割线--------------");


            for (Annotation an : allAnnotations) {

                Class annotationType = an.annotationType();

                System.out.println("属性【" + filedName + "】的注释类型有: " + annotationType);
            }
            System.out.println("----------华丽的分割线--------------");
      }
*/


        systemUserService.add(s);

        }

}