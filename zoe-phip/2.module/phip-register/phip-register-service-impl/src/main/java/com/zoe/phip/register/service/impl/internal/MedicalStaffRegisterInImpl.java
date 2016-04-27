package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
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

    public MedicalStaffInfo providerDetailsQuery(Map<String, Object> map) throws Exception {
        //todo 字典赋�
        MedicalStaffInfo staffInfo = getMapper().getStaff(map);
        if (staffInfo == null) {
            throw new BusinessException("003");
        }
        return staffInfo;
    }

    @Override

    public boolean providerDelete(MedicalStaffInfo staffInfo) throws Exception {
        return getMapper().deleteByPrimaryKey(staffInfo.getId()) > 0;
    }

    @Override
    public PageList<MedicalStaffInfo> providerListQuery(QueryPage page, String message) throws Exception {
        PageList<MedicalStaffInfo> pageList = new PageList<MedicalStaffInfo>();
        Example example = new Example(MedicalStaffInfo.class);
        SqlHelper.startPage(page);
        if (!StringUtil.isNullOrWhiteSpace(message)) {
            example.createCriteria().andLike("idNo", "%" + message + "%");
            example.or(example.createCriteria().andLike("name", "%" + message + "%"));
        }
        List<MedicalStaffInfo> results = getMapper().selectByExample(example);
        PageInfo<MedicalStaffInfo> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }


    @Override
    public MedicalStaffInfo getStaff(Map<String, Object> map) {
        return getMapper().getStaff(map);
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
        ErrorMessage[] errorMessages = this.getClass().getAnnotationsByType(ErrorMessage.class);
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
        return medicalStaffInfo;
    }
}
