package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.service.util.SqlHelper;
import com.zoe.phip.register.dao.IOrgDeptInfoMapper;
import com.zoe.phip.register.model.XmanBaseInfo;
import com.zoe.phip.register.service.internal.IOrganizationRegisterIn;
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
public class OrganizationRegisterInImpl extends BaseInServiceImpl<OrgDeptInfo, IOrgDeptInfoMapper> implements IOrgDeptInfoMapper {

    @ErrorMessage(code = "001", message = "由于内容重复注册，注册失败")
    public OrgDeptInfo addOrganization(OrgDeptInfo orgDeptInfo) throws Exception {

        //数据是否存在判断
        Example example = new Example(OrgDeptInfo.class);
        example.createCriteria().andEqualTo("code", orgDeptInfo.getCode());
        int count = getMapper().selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException("001");
        }
        //保存到数据库
        orgDeptInfo.setId(StringUtil.getUUID());
        super.add(orgDeptInfo);

        return orgDeptInfo;
    }


    @ErrorMessage(code = "002", message = "由于更新内容不存在，更新失败")
    public OrgDeptInfo updateOrganization(OrgDeptInfo orgDeptInfo) throws Exception {
        //数据是否存在判断
        Example example = new Example(XmanBaseInfo.class);
        example.createCriteria().andEqualTo("code", orgDeptInfo.getCode());
        int count = getMapper().selectCountByExample(example);
        if (count == 0) {
            throw new BusinessException("002");
        }
        //保存到数据库
        super.update(orgDeptInfo);
        return orgDeptInfo;
    }


    @ErrorMessage(code = "003", message = "由于查询内容不存在，查询失败！")
    public OrgDeptInfo organizationDetailQuery(Map<String, Object> map) throws Exception {
        //todo 字典赋值
        OrgDeptInfo baseInfo = getMapper().getOrgDeptInfo(map);
        if (baseInfo == null) {
            throw new BusinessException("003");
        }
        return baseInfo;
    }


    public boolean organizationDelete(String id) {
        Example e = new Example(OrgDeptInfo.class);
        e.createCriteria().andEqualTo("code", id);
        return getMapper().deleteByExample(e) > 0;
    }

    public List<OrgDeptInfo> dictItemListQuery(String deptParentCode) {
        Example example = new Example(OrgDeptInfo.class);
        example.createCriteria().andEqualTo("deptParentCode", deptParentCode);
        return getMapper().selectByExample(example).stream().collect(Collectors.toList());
    }

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

    @Override
    public OrgDeptInfo getOrgDeptInfo(Map<String, Object> map) {
        return getMapper().getOrgDeptInfo(map);
    }
}
