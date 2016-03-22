/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.service.impl.in.sm;

import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.model.sm.MenuData;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.in.sm.MenuDataService;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date 2016-03-21
 * @version 1.0
 */
@Service("menuDataService")
public class MenuDataServiceImpl extends BaseInServiceImpl<MenuData> implements MenuDataService {

    @Override
    public ServiceResult add(MenuData entity) {

        return super.add(entity);
    }
}