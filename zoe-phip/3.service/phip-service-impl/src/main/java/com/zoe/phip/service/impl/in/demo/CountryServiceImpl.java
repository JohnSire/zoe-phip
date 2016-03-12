package com.zoe.phip.service.impl.in.demo;

import com.zoe.phip.model.demo.Country;
import com.zoe.phip.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.service.in.demo.CountryService;
import org.springframework.stereotype.Service;

/**
 * Created by zengjiyang on 2016/3/12.
 */
@Service("CountryServiceImpl")
public class CountryServiceImpl extends BaseInServiceImpl<Country> implements CountryService{

}
