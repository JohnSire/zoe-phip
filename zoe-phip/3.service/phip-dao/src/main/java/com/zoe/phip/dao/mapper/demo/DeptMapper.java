package com.zoe.phip.dao.mapper.demo;

import com.zoe.phip.dao.MyMapper;
import com.zoe.phip.model.demo.Dept;

/**
 * Created by zengjiyang on 2016/1/25.
 */
public interface DeptMapper extends MyMapper<Dept> {

    void updateDeptName(String name);

    int countDept();
}
