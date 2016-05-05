/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.web.dao.sdm.IStandardVersionMapper;
import com.zoe.phip.web.model.sdm.*;
import com.zoe.phip.web.service.sdm.IStandardVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
@Repository("standardVersionService")
@Service(interfaceClass = IStandardVersionService.class, proxy = "sdpf", dynamic = true)
@ErrorMessage(code = "001", message = "标准版本标识({0})已经存在!")
public class StandardVersionServiceImpl extends BaseInServiceImpl<StandardVersion, IStandardVersionMapper> implements IStandardVersionMapper {

    @Autowired
    StandardVerRsDictServiceImpl dictServiceImpl;

    @Autowired
    StandardVerRsSetServiceImpl setServiceImpl;

    @Autowired
    StandardVerRsCdaServiceImpl cdaServiceImpl;

    @Autowired
    StandardVerRsFieldServiceImpl fieldServiceImpl;

    @Override
    public int add(StandardVersion standardVersion) throws Exception {
        Example example = new Example(StandardVersion.class);
        example.createCriteria().andEqualTo("code", standardVersion.getCode());
        //数据是否存在判断
        int count = getMapper().selectCountByExample(example);
        if (count > 0) {
            throw new BusinessException("001");
        }
        standardVersion.setId(StringUtil.getUUID());
        return super.add(standardVersion);
    }


    @Override
    public int update(StandardVersion standardVersion) throws Exception {
        Map<String, Object> map = MapUtil.createMap(m -> {
            m.put("code", standardVersion.getCode());
            m.put("id", standardVersion.getId());
        });
        if (getSingleVersion(map) > 0) throw new BusinessException("001", standardVersion.getCode());
        return super.update(standardVersion);
    }

    @Override
    public int getSingleVersion(Map<String, Object> map) {
        return getMapper().getSingleVersion(map);
    }

    @Override
    public List<StandardVersion> getDataPageList(Map<String, Object> map) {
        return getMapper().getDataPageList(map);
    }


    @Override
    public int versionStandardStruct(String fkVersionId, List<StandardVerRsCda> cdaList, List<StandardVerRsSet> setList, List<StandardVerRsField> fieldList) throws Exception {
        Example cda = new Example(StandardVerRsCda.class);
        cda.createCriteria().andEqualTo("fkVersionId", fkVersionId);
        int count = getMapper().selectCountByExample(cda);

        int i = setServiceImpl.versionStandardStruct(fkVersionId, setList);
        int j = cdaServiceImpl.versionStandardStruct(fkVersionId, cdaList);
        int p = fieldServiceImpl.versionStandardStruct(fkVersionId, fieldList);
        return p;
    }

    @Override
    public int versionDictUpdate(String fkVersionId, List<StandardVerRsDict> infoList) throws Exception {
        return dictServiceImpl.versionDictUpdate(fkVersionId, infoList);
    }


}