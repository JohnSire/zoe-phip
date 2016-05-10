/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.web.dao.sdm.IStandardVerRsDictMapper;
import com.zoe.phip.web.model.sdm.StandardVerRsDict;
import com.zoe.phip.web.model.sdm.StandardVerRsField;
import com.zoe.phip.web.service.sdm.IStandardVerRsDictService;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2016-05-04
 */
@Repository("standardVerRsDictService")
@Service(interfaceClass = IStandardVerRsDictService.class, proxy = "sdpf", dynamic = true)
public class StandardVerRsDictServiceImpl extends BaseInServiceImpl<StandardVerRsDict, IStandardVerRsDictMapper> implements IStandardVerRsDictMapper {

    public int versionDictUpdate(String fkVersionId, List<StandardVerRsDict> infoList) throws Exception{
        Example example = new Example(StandardVerRsDict.class);
        example.createCriteria().andEqualTo("fkVersionId", fkVersionId);
        int i = super.addList(infoList);
        return i;
    }
}