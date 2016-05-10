package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.SortOrder;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.service.util.SqlHelper;
import com.zoe.phip.register.dao.IDictCatalogMapper;
import com.zoe.phip.register.dao.IDictItemMapper;
import com.zoe.phip.register.dao.INationalStandardsMapper;
import com.zoe.phip.register.dao.IOrgDeptInfoMapper;
import com.zoe.phip.register.model.*;
import com.zoe.phip.register.service.internal.IOrganizationRegisterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("OrganizationRegisterIn")
@Service(interfaceClass = IOrganizationRegisterIn.class, proxy = "sdpf", protocol = {"dubbo"}, dynamic = true)
@ErrorMessage(code = "001", message = "由于内容重复注册，注册失败")
@ErrorMessage(code = "002", message = "由于更新内容不存在，更新失败")
@ErrorMessage(code = "003", message = "由于查询内容不存在，查询失败！")
public class OrganizationRegisterInImpl extends BaseInServiceImpl<OrgDeptInfo, IOrgDeptInfoMapper> implements IOrgDeptInfoMapper {

    @Autowired
    private IDictItemMapper dictItemMapper;

    @Autowired
    @Qualifier(value = "IDictCatalogMapper")
    private IDictCatalogMapper dictCatalogMapper;

    @Autowired
    @Qualifier(value = "INationalStandardsMapper")
    private INationalStandardsMapper nationalStandardsMapper;


    public OrgDeptInfo addOrganization(OrgDeptInfo orgDeptInfo) throws Exception {

        //数据是否存在判断
        Example example = new Example(OrgDeptInfo.class);
        example.createCriteria().andEqualTo("deptCode", orgDeptInfo.getDeptCode());
        int count = getMapper().selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException("001");
        }
        //保存到数据库
        orgDeptInfo.setId(StringUtil.getUUID());
        super.add(orgDeptInfo);

        return orgDeptInfo;
    }

    public OrgDeptInfo updateOrganization(OrgDeptInfo orgDeptInfo) throws Exception {
        //数据是否存在判断
        Example example = new Example(OrgDeptInfo.class);
        example.createCriteria().andEqualTo("deptCode", orgDeptInfo.getDeptCode());
        int count = getMapper().selectCountByExample(example);
        if (count == 0) {
            throw new BusinessException("002");
        }
        //保存到数据库
        getMapper().defaultUpdate(orgDeptInfo);
        return orgDeptInfo;
    }

    public OrgDeptInfo updateOrg(OrgDeptInfo orgDeptInfo) throws Exception {
        //数据是否存在判断
        Example example = new Example(OrgDeptInfo.class);
        example.createCriteria().andEqualTo("id", orgDeptInfo.getId());
        int count = getMapper().selectCountByExample(example);
        if (count == 0) {
            throw new BusinessException("002");
        }
        //保存到数据库
        super.update(orgDeptInfo);
        return orgDeptInfo;
    }


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
        e.createCriteria().andEqualTo("id", id);
        return getMapper().deleteByExample(e) > 0;
    }

    public DictCatalog dictItemListQuery() {
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("sdiCode", "org_code");//系统字典项的code
        paras.put("sdcCode", "STD_ORG_TYPE");//系统字典里的code
        DictCatalog dictCatalog = dictCatalogMapper.getDictCategoryOrg(paras);
        if (null != dictCatalog && null != dictCatalog.getId()) {
            Map<String, Object> parasTwo = new HashMap<String, Object>();
            parasTwo.put("pid", dictCatalog.getId());
            dictCatalog.setDictItemList(dictItemMapper.getDictItemOrgList(paras));
        }

        return dictCatalog;
    }

    public NationalStandards dictItemListQueryByCodeSystem(String codeSystem) {
        NationalStandards nationalStandards = nationalStandardsMapper.getNationalStandardDescr(
                MapUtil.createMap(m -> {
                    m.put("codeSystem", codeSystem);
                })
        );
        if (null != nationalStandards) {
            nationalStandards.setDictItemList(dictItemMapper.getDictItemNewOrgTree(MapUtil.createMap(m -> {
                m.put("codeSystem", codeSystem);
            })));
        }
        return nationalStandards;
    }

    public NationalStandards dictItemListQueryByDictCode(String dictCode) {
        NationalStandards nationalStandards = nationalStandardsMapper.getNationalStandardDescr(
                MapUtil.createMap(m -> {
                    m.put("dictCode", dictCode);
                })
        );
        if (null != nationalStandards) {
            nationalStandards.setDictItemList(dictItemMapper.getDictItemNewOrgTree(MapUtil.createMap(m -> {
                m.put("dictCode", dictCode);
            })));
        }
        return nationalStandards;
    }

    public PageList<OrgDeptInfo> organizationListQuery(String type, String deptTypeCode, String key, QueryPage page) {
        PageList<OrgDeptInfo> pageList = new PageList<OrgDeptInfo>();
        //分页
        SqlHelper.startPage(page);
        Map<String, Object> paras = new HashMap<String, Object>();
        if (!StringUtil.isNullOrWhiteSpace(key)) {
            paras.put("key", SqlHelper.getLikeStr(key.toUpperCase()));
        }
        if (type.equals("1")) {
            paras.put("deptTypeCode", deptTypeCode);
        } else {
            //读取所有机构排除科室数据
            paras.put("divisionRoot", "2.16.156.10011.1.5");
        }
        //        SqlHelper.setOrder(paras,queryPage);
        List<OrgDeptInfo> results = ((IOrgDeptInfoMapper) getMapper()).getOrgDeptInfoList(paras);
        PageInfo<OrgDeptInfo> pageInfo = new PageInfo<OrgDeptInfo>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    public PageList<OrgDeptInfo> DepartmentListQuery(String type, String deptTypeCode, String deptParentCode, String key, QueryPage page) {
        PageList<OrgDeptInfo> pageList = new PageList<OrgDeptInfo>();
        //分页
        SqlHelper.startPage(page);
        Map<String, Object> paras = new HashMap<String, Object>();
        if (!StringUtil.isNullOrWhiteSpace(key)) {
            paras.put("key", SqlHelper.getLikeStr(key.toUpperCase()));
        }
        if (type.equals("1")) {
            paras.put("deptTypeCode", deptTypeCode);
        } else {
            //读取所有科室排除机构数据
            paras.put("divisionRoot", "2.16.156.10011.1.26");
        }
        paras.put("deptParentCode", deptParentCode);
        //        SqlHelper.setOrder(paras,queryPage);
        List<OrgDeptInfo> results = ((IOrgDeptInfoMapper) getMapper()).getOrgDeptInfoList(paras);
        PageInfo<OrgDeptInfo> pageInfo = new PageInfo<OrgDeptInfo>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }


    public PageList<OrgDeptInfo> orgListQuery() {
        PageList<OrgDeptInfo> pageList = new PageList<OrgDeptInfo>();
        Map<String, Object> paras = new HashMap<String, Object>();
        List<OrgDeptInfo> results = ((IOrgDeptInfoMapper) getMapper()).getOrgDeptInfoList(paras);
        PageInfo<OrgDeptInfo> pageInfo = new PageInfo<OrgDeptInfo>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }


    @Override
    public List<OrgDeptInfo> getOrgDeptInfoList(Map<String, Object> args) {
        return getMapper().getOrgDeptInfoList(args);

    }


    @Override
    public OrgDeptInfo getOrgDeptInfo(Map<String, Object> map) {
        return getMapper().getOrgDeptInfo(map);
    }

    @Override
    public int defaultUpdate(OrgDeptInfo t) {
        return getMapper().defaultUpdate(t);
    }


    public PageList<DictItem> getDictItemPage(String codeSystem, String key, QueryPage page) {
        PageList<DictItem> pageList = new PageList<DictItem>();
        page.setOrderBy("pdi.CREATE_AT");
        page.setSortOrder(SortOrder.DESC);
        //分页
        SqlHelper.startPage(page);
        //        SqlHelper.setOrder(paras,queryPage);
        List<DictItem> results =
                dictItemMapper.getDictItemNewOrgTree(MapUtil.createMap(m -> {
                    m.put("codeSystem", codeSystem);
                    if (!StringUtil.isNullOrWhiteSpace(key)) {
                        m.put("key", SqlHelper.getLikeStr(key.toUpperCase()));
                    }
                }));
        PageInfo<DictItem> pageInfo = new PageInfo<DictItem>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }


    @Override
    public OrgDeptInfo getById(String id) throws Exception {
        return getOrgDeptByPrimaryKey(id);
    }
    @Override
    public OrgDeptInfo getOrgDeptByPrimaryKey(String id) {
        return getMapper().getOrgDeptByPrimaryKey(id);
    }

    @Override
    public OrgDeptInfo getDepartmentByPrimaryKey(String id) {
       return getMapper().getDepartmentByPrimaryKey(id);
    }


    public OrgDeptInfo getByDepartmentId(String id){
        return getDepartmentByPrimaryKey(id);
    }

}
