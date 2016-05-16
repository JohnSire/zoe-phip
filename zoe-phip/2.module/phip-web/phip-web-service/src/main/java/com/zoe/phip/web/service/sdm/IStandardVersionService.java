/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.web.service.sdm;

import com.zoe.phip.infrastructure.entity.*;
import com.zoe.phip.module.service.service.in.IBaseInService;
import com.zoe.phip.web.model.sdm.*;

import java.util.List;

/**
 * 对外发布的服务接口
 *
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
public interface IStandardVersionService extends IBaseInService<StandardVersion> {

    /**
     * 根据关键字查询标准版本信息
     *
     * @param systemData
     * @param key
     * @param queryPage
     * @return
     */
    ServiceResultT<PageList<StandardVersion>> getDataPageList(SystemData systemData, String key, QueryPage queryPage);

    /**
     * 维护版本标准关系
     *
     * @param systemData
     * @param fkVersionId 版本标识ID
     * @param cdaList     版本CDA关系实体
     * @param setList     版本数据集关系实体
     * @param fieldList   版本数据集字段关系实体
     * @return
     */
    ServiceResult versionStandardStruct(SystemData systemData, String fkVersionId, List<StandardVerRsCda> cdaList, List<StandardVerRsSet> setList, List<StandardVerRsDict> fieldList);


    ServiceResult updateVersion(SystemData systemData, List<StandardVerRsCda> cdaList, List<StandardVerRsSet> setList, List<StandardVerRsDict> fieldList);


    /**
     * 标准版本字典维护
     *
     * @param systemData
     * @param infoList   版本字典关系
     * @return
     */
    ServiceResult versionDictUpdate(SystemData systemData, String fkVersionId, List<StandardVerRsField> infoList);

    /**
     * 删除版本信息
     * @param systemData
     * @param id
     * @return
     */
    ServiceResult deleteVersion(SystemData systemData, String id);

    /**
     * 标准版本新增关联
     * @param systemData
     * @param fkVersionId
     * @param cdaList
     * @param setList
     * @param fieldList
     * @return
     */
    ServiceResult versionStandardRsAdd(SystemData systemData, String fkVersionId, List<StandardVerRsCda> cdaList, List<StandardVerRsSet> setList, List<StandardVerRsDict> fieldList);
}