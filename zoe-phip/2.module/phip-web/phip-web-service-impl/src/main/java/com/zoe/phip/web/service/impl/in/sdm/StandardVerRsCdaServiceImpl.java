/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.web.dao.sdm.IStandardVerRsCdaMapper;
import com.zoe.phip.web.model.sdm.StandardVerRsCda;
import com.zoe.phip.web.service.sdm.IStandardVerRsCdaService;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2016-05-05
 */
@Repository("standardVerRsCdaService")
@Service(interfaceClass = IStandardVerRsCdaService.class, proxy = "sdpf", dynamic = true)
public class StandardVerRsCdaServiceImpl extends BaseInServiceImpl<StandardVerRsCda, IStandardVerRsCdaMapper> implements IStandardVerRsCdaMapper {

    public int versionStandardStruct(String fkVersionId,List<StandardVerRsCda> fieldList) throws Exception {
        Example cda = new Example(StandardVerRsCda.class);
        cda.createCriteria().andEqualTo("fkVersionId", fkVersionId);
        int i = super.addList(fieldList);
        return i;
    }
}