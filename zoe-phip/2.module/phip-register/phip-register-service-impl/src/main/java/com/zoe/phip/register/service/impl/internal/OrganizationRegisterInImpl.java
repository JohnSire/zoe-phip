package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.SafeExecuteUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.service.util.SqlHelper;
import com.zoe.phip.register.dao.IOrgDeptInfoMapper;
import com.zoe.phip.register.model.OrgDeptInfo;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.service.internal.IOrganizationRegisterIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("OrganizationRegisterIn")
@Service(interfaceClass = IOrganizationRegisterIn.class, proxy = "sdpf", protocol = {"dubbo"}, dynamic = true)
@ErrorMessage(code = "001", message = "由于内容重复注册，注册失败")
@ErrorMessage(code = "002", message = "由于更新内容不存在，更新失败")
@ErrorMessage(code = "003", message = "由于合并内容不存在，合并失败")
@ErrorMessage(code = "004", message = "由于查询内容不存在，查询失败")
public class OrganizationRegisterInImpl extends BaseInServiceImpl<OrgDeptInfo, IOrgDeptInfoMapper> implements IOrganizationRegisterIn {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationRegisterInImpl.class);

    @Autowired
    private IOrgDeptInfoMapper baseInfoMapper;

    @Override
    public ServiceResultT<OrgDeptInfo> addOrganization(OrgDeptInfo orgDeptInfo) {

        ErrorMessage[] errorMessages = this.getClass().getAnnotationsByType(ErrorMessage.class);
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
        ErrorMessage[] errorMessages = this.getClass().getAnnotationsByType(ErrorMessage.class);
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
    public ServiceResultT<OrgDeptInfo> organizationDetailQuery(Map<String, Object> map) {
        ErrorMessage[] errorMessages = this.getClass().getAnnotationsByType(ErrorMessage.class);
        return SafeExecuteUtil.execute0(() -> {
            //todo 字典赋值
            OrgDeptInfo baseInfo = baseInfoMapper.getOrgDeptInfo(map);
            if (baseInfo == null) {
                throw new BusinessException("004");
            }
            return baseInfo;
        }, errorMessages);
    }

    @Override
    public boolean organizationDelete(String id) {
        Example e = new Example(OrgDeptInfo.class);
        e.createCriteria().andEqualTo("code", id);
        return getMapper().deleteByExample(e) > 0;
    }

    @Override
    public List<OrgDeptInfo> dictItemListQuery(String deptParentCode) {
        Example example = new Example(OrgDeptInfo.class);
        example.createCriteria().andEqualTo("deptParentCode", deptParentCode);
        return getMapper().selectByExample(example).stream().collect(Collectors.toList());
    }

    @Override
    public PageList<OrgDeptInfo> organizationListQuery(String deptParentCode, String key, QueryPage page) {
        PageList<OrgDeptInfo> pageList = new PageList<OrgDeptInfo>();
        Example example = new Example(OrgDeptInfo.class);
        SqlHelper.startPage(page);
        example.createCriteria().andEqualTo("deptParentCode", deptParentCode);
        if (!StringUtil.isNullOrWhiteSpace(key)) {
            example.or(example.createCriteria().andLike("deptName", "%" + key + "%"));
            example.or(example.createCriteria().andLike("code", "%" + key + "%"));

        }
        List<OrgDeptInfo> results = getMapper().selectByExample(example);
        PageInfo<OrgDeptInfo> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }
}
