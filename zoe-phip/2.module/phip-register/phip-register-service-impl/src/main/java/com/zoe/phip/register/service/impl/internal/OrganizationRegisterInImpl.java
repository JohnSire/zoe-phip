package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.register.dao.IOrgDeptInfoMapper;
import com.zoe.phip.register.model.OrgDeptInfo;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.service.internal.IOrganizationRegisterIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.Map;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("OrganizationRegisterIn")
@Service(interfaceClass = IOrganizationRegisterIn.class, proxy = "sdpf",protocol = {"dubbo"},dynamic = true)
@ErrorMessage(code = "001", message = "由于内容重复注册，注册失败")
@ErrorMessage(code = "002", message = "由于更新内容不存在，更新失败")
@ErrorMessage(code = "003", message = "由于合并内容不存在，合并失败")
@ErrorMessage(code = "004", message = "由于查询内容不存在，查询失败")
public class OrganizationRegisterInImpl implements IOrganizationRegisterIn {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationRegisterInImpl.class);

    @Autowired
    private IOrgDeptInfoMapper baseInfoMapper;

    @Override
    public ServiceResultT<OrgDeptInfo> addOrganization(OrgDeptInfo orgDeptInfo) {

        ErrorMessage[] errorMessages= this.getClass().getAnnotationsByType(ErrorMessage.class);
        return SafeExecuteUtil.execute0(() ->
        {
            //数据是否存在判断
            Example example = new Example(OrgDeptInfo.class);
            example.createCriteria().andEqualTo("code", orgDeptInfo.getCode());
            int count = baseInfoMapper.selectCountByExample(example);
            if (count > 0) {
                throw new BusinessException("001");
            }
            //保存到数据库
            orgDeptInfo.setId(StringUtil.getUUID());
            baseInfoMapper.defaultAdd(orgDeptInfo);

            return orgDeptInfo;
        }, errorMessages);
    }

    @Override
    public ServiceResultT<OrgDeptInfo> updateOrganization(OrgDeptInfo orgDeptInfo) {
        ErrorMessage[] errorMessages= this.getClass().getAnnotationsByType(ErrorMessage.class);
        return SafeExecuteUtil.execute0(() ->
        {
            //数据是否存在判断
            Example example = new Example(XmanBaseInfo.class);
            example.createCriteria().andEqualTo("code", orgDeptInfo.getCode());
            int count = baseInfoMapper.selectCountByExample(example);
            if (count == 0) {
                throw new BusinessException("002");
            }
            //保存到数据库
            baseInfoMapper.defaultUpdate(orgDeptInfo);
            return orgDeptInfo;
        }, errorMessages);
    }



    @Override
    public ServiceResultT<OrgDeptInfo> organizationDetailQuery(Map<String,Object> map) {
        ErrorMessage[] errorMessages= this.getClass().getAnnotationsByType(ErrorMessage.class);
        return SafeExecuteUtil.execute0(()->{
            //todo 字典赋值
            OrgDeptInfo baseInfo=baseInfoMapper.getOrgDeptInfo(map);
            if(baseInfo==null){
                throw new BusinessException("004");
            }
            return baseInfo;
        },errorMessages);
    }
}
