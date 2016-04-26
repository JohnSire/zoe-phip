/*
 * Powered By zoe
 * Since 2008 - 2016
 */

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
import com.zoe.phip.register.dao.IOrgBaseInfoMapper;
import com.zoe.phip.register.model.OrgBaseInfo;
import com.zoe.phip.register.model.OrgDeptInfo;
import com.zoe.phip.register.service.internal.IOrgBaseInfoService;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-04-25
 */
@Repository("OrgBaseInfoService")
@Service(interfaceClass = IOrgBaseInfoService.class, protocol = {"dubbo"}, proxy = "sdpf", dynamic = true)
public class OrgBaseInfoServiceImpl extends BaseInServiceImpl<OrgBaseInfo, IOrgBaseInfoMapper> implements IOrgBaseInfoMapper {


    @Override
    public OrgBaseInfo addOrganization(OrgBaseInfo orgBaseInfo) throws Exception {
        return null;
    }

    @Override
    public OrgBaseInfo updateOrganization(OrgBaseInfo orgBaseInfo) throws Exception {
        return null;
    }

    @Override
    public OrgBaseInfo organizationDetailQuery(Map<String, Object> map) throws Exception {
        return null;
    }

    @Override
    public boolean organizationDelete(OrgBaseInfo orgBaseInfo) throws Exception {
        return false;
    }

    @Override
    public PageList<OrgBaseInfo> organizationListQuery(QueryPage page, String message) throws Exception {
        return null;
    }

    @Override
    @ErrorMessage(code = "001", message = "由于机构代码重复注册，注册失败")
    public int add(OrgBaseInfo entity)throws Exception {
        List<OrgBaseInfo> list = getOrgBaseInfoByCode(entity.getCode());
        if (list != null && list.size() > 0) {
            throw new BusinessException("001", entity.getCode());
        }
        return super.add(entity);
    }

    @Override
    @ErrorMessage(code = "002",message = "已存在机构代码为({0})的机构!")
    public boolean UpdateOrgBaseInfo(OrgBaseInfo orgBaseInfo) throws Exception {

       Example example = new Example(OrgBaseInfo.class);
        example.createCriteria().andEqualTo("code", orgBaseInfo.getCode())
                .andNotEqualTo("id", orgBaseInfo.getId());
        int count = getMapper().selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException("003", orgBaseInfo.getCode());
        } else
            return getMapper().updateByPrimaryKeySelective(orgBaseInfo)>0;
    }

    @Override
    public List<OrgBaseInfo> orgBaseInfoDetailQuery(String code,String deptName) throws Exception {
        Example example = new Example(OrgBaseInfo.class);
        example.createCriteria().andEqualTo("code", code).andEqualTo("deptName",deptName);
        List<OrgBaseInfo> list = getMapper().selectByExample(example);
        return list;
    }

    @Override
    public boolean OrgabaseInfoDelete(String id) throws Exception {
        Example e = new Example(OrgDeptInfo.class);
        e.createCriteria().andEqualTo("code", id);
        return getMapper().deleteByExample(e) > 0;
    }

    @Override
    public PageList<OrgBaseInfo> orgBaseInfoPageListQuery(String code, String key, QueryPage page) throws Exception {
        PageList<OrgBaseInfo> pageList = new PageList<OrgBaseInfo>();
        Example example = new Example(OrgDeptInfo.class);
        SqlHelper.startPage(page);
        example.createCriteria().andEqualTo("parentId", code);
        if (!StringUtil.isNullOrWhiteSpace(key)) {
            example.or(example.createCriteria().andLike("orgName", "%" + key + "%"));
            example.or(example.createCriteria().andLike("code", "%" + key + "%"));

        }
        List<OrgBaseInfo> results = getMapper().selectByExample(example);
        PageInfo<OrgBaseInfo> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }


    private List<OrgBaseInfo> getOrgBaseInfoByCode(String code) {
        Example example = new Example(OrgBaseInfo.class);
        example.createCriteria().andEqualTo("code", code);
        List<OrgBaseInfo> list = getMapper().selectByExample(example);
        return list;
    }






}