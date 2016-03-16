/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.in.demo;

import com.zoe.phip.model.demo.Person;
import com.zoe.phip.service.in.BaseInService;

/**
 * 对外发布的服务接口
 * @author
 * @date 2016-03-15
 * @version 1.0
 */
public interface PersonService extends BaseInService<Person> {
   Person selectPersonById(String id);
}