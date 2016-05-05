/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
;
import com.zoe.phip.web.dao.sdm.IStandardVerRsSetMapper;
import com.zoe.phip.web.model.sdm.StandardVerRsSet;
import com.zoe.phip.web.service.sdm.IStandardVerRsSetService;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2016-05-04
 */
@Repository("standardVerRsSetService")
@Service(interfaceClass = IStandardVerRsSetService.class, proxy = "sdpf", dynamic = true)
public class StandardVerRsSetServiceImpl extends BaseInServiceImpl<StandardVerRsSet, IStandardVerRsSetMapper> implements IStandardVerRsSetMapper {

    public int versionStandardStruct(String fkVersionId, List<StandardVerRsSet> setList) throws Exception {
        Example example = new Example(StandardVerRsSet.class);
        example.createCriteria().andEqualTo("fkVersionId", fkVersionId);
        int i = super.addList(setList);
        return i;
    }

}