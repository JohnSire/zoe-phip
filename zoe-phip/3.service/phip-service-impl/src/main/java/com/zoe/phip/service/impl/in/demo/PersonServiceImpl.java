/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.impl.in.demo;

import com.zoe.phip.dao.mapper.demo.PersonMapper;
import com.zoe.phip.model.demo.Person;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.in.demo.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date 2016-03-15
 * @version 1.0
 */
@Service("personService")
public class PersonServiceImpl extends BaseInServiceImpl<Person> implements PersonService {


    @Autowired
    @SuppressWarnings("all")
    private PersonMapper personMapper;

    @Override
    public Person selectPersonById(String id) {
        return personMapper.selectPersonById(id);
    }
}