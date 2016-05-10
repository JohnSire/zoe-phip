/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.dao.sdm;

import com.zoe.phip.module.service.mapper.IMyMapper;
import com.zoe.phip.web.model.sdm.*;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
public interface IStandardVersionMapper extends IMyMapper<StandardVersion> {

    int getSingleVersion(Map<String, Object> map);

    List<StandardVersion> getDataPageList(Map<String, Object> map);


    int versionStandardStruct(String fkVersionId, List<StandardVerRsCda> cdaList, List<StandardVerRsSet> setList, List<StandardVerRsField> fieldList) throws Exception;

    int versionDictUpdate(String fkVersionId, List<StandardVerRsDict> infoList) throws Exception;

}