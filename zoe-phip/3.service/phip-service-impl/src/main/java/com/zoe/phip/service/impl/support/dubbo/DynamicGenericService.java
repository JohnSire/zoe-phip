package com.zoe.phip.service.impl.support.dubbo;

import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;
import org.springframework.stereotype.Repository;

/**
 * Created by qiuyungen on 2016/3/26.
 */
@Repository("DynamicGenericService")
public class DynamicGenericService implements GenericService {
    @Override
    public Object $invoke(String s, String[] strings, Object[] objects) throws GenericException {
        return null;
    }
}
