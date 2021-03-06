package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.SortOrder;
import com.zoe.phip.infrastructure.entity.SystemData;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.service.util.SqlHelper;
import com.zoe.phip.register.dao.IMedicalStaffInfoMapper;
import com.zoe.phip.register.model.MedicalStaffInfo;
import com.zoe.phip.register.service.internal.IMedicalStaffRegisterIn;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("MedicalStaffRegisterIn")
@Service(interfaceClass = IMedicalStaffRegisterIn.class, proxy = "sdpf", protocol = {"dubbo"}, dynamic = true)
@ErrorMessage(code = "001", message = "由于内容重复注册，注册失败")
@ErrorMessage(code = "002", message = "由于更新内容不存在，更新失败")
@ErrorMessage(code = "003", message = "由于查询内容不存在，查询失败")
@ErrorMessage(code = "004", message = "由于删除内容不存在，删除失败")
public class MedicalStaffRegisterInImpl extends BaseInServiceImpl<MedicalStaffInfo, IMedicalStaffInfoMapper> implements IMedicalStaffInfoMapper {
    private static final Logger logger = LoggerFactory.getLogger(MedicalStaffRegisterInImpl.class);

    @Override
    public MedicalStaffInfo providerDetailsQuery(String id) throws Exception {
        MedicalStaffInfo staffInfo = getMapper().getProvider(id);
        if (staffInfo == null) {
            throw new BusinessException("003");
        }
        return staffInfo;
    }

    public MedicalStaffInfo providerDetailsQuery(Map<String, Object> map) throws Exception {
        MedicalStaffInfo staffInfo = getMapper().getStaff(map);
        if (staffInfo == null) {
            throw new BusinessException("003");
        }
        return staffInfo;
    }


    @Override
    public PageList<MedicalStaffInfo> providerListQuery(String type, String key, String orgTypeCode, String deptCode, QueryPage page) throws Exception {
        PageList<MedicalStaffInfo> pageList = new PageList<MedicalStaffInfo>();
        //分页
        page.setOrderBy(" M.CREATE_AT  ");
        page.setSortOrder(SortOrder.DESC);
        SqlHelper.startPage(page);
        Map<String, Object> paras = new HashMap<String, Object>();
        List<MedicalStaffInfo> results = null;
        if (type.equals("1")) {
            if (!StringUtil.isNullOrWhiteSpace(key)) {
                paras.put("key", SqlHelper.getLikeStr(key.toUpperCase()));
            }
            if (!StringUtil.isNullOrWhiteSpace(deptCode)) {
                paras.put("deptCode", deptCode);
            }
            results = getAllProviderList(paras);
        } else if (type.equals("2")) {
            if (!StringUtil.isNullOrWhiteSpace(orgTypeCode)) {
                paras.put("orgTypeCode", orgTypeCode);
            }
            results = getOrgProviderList(paras);
        } else {
            if (!StringUtil.isNullOrWhiteSpace(key)) {
                paras.put("key", SqlHelper.getLikeStr(key.toUpperCase()));
            }
            if (!StringUtil.isNullOrWhiteSpace(deptCode)) {
                paras.put("deptCode", deptCode);
            }
            results = getProviderList(paras);
        }

        PageInfo<MedicalStaffInfo> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }


    @Override
    public MedicalStaffInfo addProvider(MedicalStaffInfo medicalStaffInfo) throws Exception {
        Example example = new Example(MedicalStaffInfo.class);
        example.createCriteria().andEqualTo("extensionId", medicalStaffInfo.getExtensionId());
        //数据是否存在判断
        int count = getMapper().selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException("001");
        }
        //保存到数据库
        medicalStaffInfo.setId(StringUtil.getUUID());
        super.add(medicalStaffInfo);
        return medicalStaffInfo;
    }

    @Override
    public MedicalStaffInfo updateProvider(MedicalStaffInfo medicalStaffInfo) throws Exception {
        if (StringUtil.isNullOrWhiteSpace(medicalStaffInfo.getId())) {
            Example example = new Example(MedicalStaffInfo.class);
            example.createCriteria().andEqualTo("extensionId", medicalStaffInfo.getExtensionId());
            //数据是否存在判断
            int count = getMapper().selectCountByExample(example);
            if (count == 0) {
                throw new BusinessException("002");
            }
            //保存到数据库
//        medicalStaffInfo.setId(StringUtil.getUUID());
            getMapper().defaultUpdate(medicalStaffInfo);
        } else {
            getMapper().defaultUpdate(medicalStaffInfo);
        }

        return medicalStaffInfo;
    }

    //region 接口转接
    @Override
    public MedicalStaffInfo getProvider(String id) {
        return getMapper().getProvider(id);
    }

    @Override
    public List<MedicalStaffInfo> getProviderList(Map<String, Object> map) {
        return getMapper().getProviderList(map);
    }

    @Override
    public List<MedicalStaffInfo> getAllProviderList(Map<String, Object> map) {
        return getMapper().getAllProviderList(map);
    }

    @Override
    public List<MedicalStaffInfo> getOrgProviderList(Map<String, Object> map) {
        return getMapper().getOrgProviderList(map);
    }

    @Override
    public MedicalStaffInfo getStaff(Map<String, Object> map) {
        return getMapper().getStaff(map);
    }


    @Override
    public int defaultUpdate(MedicalStaffInfo t) {
        return getMapper().defaultUpdate(t);
    }

    @Override
    public boolean providerDelete(MedicalStaffInfo staffInfo) throws Exception {
        return getMapper().deleteByPrimaryKey(staffInfo.getId()) > 0;
    }
    //endregion
}
