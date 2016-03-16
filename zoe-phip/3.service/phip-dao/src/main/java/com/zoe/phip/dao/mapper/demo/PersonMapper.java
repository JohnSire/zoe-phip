/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.dao.mapper.demo;

import com.zoe.phip.dao.MyMapper;
import com.zoe.phip.model.demo.Person;

/**
 * @author
 * @date 2016-03-15
 * @version 1.0
 */
public interface PersonMapper extends MyMapper<Person> {
      Person selectPersonById(String id);
 }