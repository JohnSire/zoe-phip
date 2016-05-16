/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

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
import com.zoe.phip.web.dao.sdm.IStSetInfoMapper;
import com.zoe.phip.web.model.sdm.StElementInfo;
import com.zoe.phip.web.model.sdm.StRsSetElementInfo;
import com.zoe.phip.web.model.sdm.StSetInfo;
import com.zoe.phip.web.service.sdm.IStSetInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
@Repository("stSetInfoService")
@Service(interfaceClass = IStSetInfoService.class, protocol = {"dubbo"}, proxy = "sdpf", dynamic = true)
@ErrorMessage(code = "001", message = "数据集标识({0})已经存在,新增失败!")
@ErrorMessage(code = "002", message = "数据集标识({0})已经存在,更新失败!")
public class StSetInfoServiceImpl extends BaseInServiceImpl<StSetInfo, IStSetInfoMapper> implements IStSetInfoMapper {


    @Autowired
    private StRsSetElementInfoServiceImpl rsSetElementInfoService;

    @Autowired
    private StElementInfoServiceImpl elementInfoService;


    public PageList<StSetInfo> getDataPageList(String key, QueryPage queryPage) {
        queryPage.setOrderBy("PSSI.CODE");
        queryPage.setSortOrder(SortOrder.ASC);
        PageList<StSetInfo> pageList = new PageList<>();
        SqlHelper.startPage(queryPage);
        Map<String, Object> map = new TreeMap<>();
        if (!StringUtil.isNullOrWhiteSpace(key)) map.put("key", SqlHelper.getLikeStr(key));
        List<StSetInfo> results = getMapper().getDataPageList(map);
        PageInfo<StSetInfo> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        super.dispose(map);
        return pageList;
    }


    public PageList<StSetInfo> getByCdaId(String fkCdaId, String key, QueryPage queryPage) {
        queryPage.setOrderBy("PSSI.CODE");
        queryPage.setSortOrder(SortOrder.ASC);
        PageList<StSetInfo> pageList = new PageList<>();
        SqlHelper.startPage(queryPage);
        Map<String, Object> map = MapUtil.createMap(m -> {
            m.put("fkCdaId", fkCdaId);
            if (!StringUtil.isNullOrWhiteSpace(key)) m.put("key", SqlHelper.getLikeStr(key));
        });
        List<StSetInfo> infoList = getMapper().getByCdaId(map);
        PageInfo<StSetInfo> pageInfo = new PageInfo<>(infoList);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(infoList);
        super.dispose(map);
        return pageList;
    }

    public PageList<StSetInfo> getByPid(String pid, QueryPage queryPage) {
        PageList<StSetInfo> pageList = new PageList<>();
        SqlHelper.startPage(queryPage);
        List<StSetInfo> infoList = getByPid(pid);
        PageInfo<StSetInfo> pageInfo = new PageInfo<>(infoList);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(infoList);
        return pageList;
    }

    @Override
    public List<StSetInfo> getDataPageList(Map<String, Object> map) {
        return getMapper().getDataPageList(map);
    }

    @Override
    public List<StSetInfo> getByCdaId(Map<String, Object> map) {
        return getMapper().getByCdaId(map);
    }


    public int rsSetElementAdd(StRsSetElementInfo info) throws Exception {
        return rsSetElementInfoService.add(info);
    }

    public int rsSetElementUpdate(StRsSetElementInfo info) throws Exception {
        return rsSetElementInfoService.update(info);
    }

    public int rsSetElementDel(String id) throws Exception {
        return rsSetElementInfoService.deleteById(id);
    }

    public int importSetAndRsElement(List<StSetInfo> setInfoList, List<StRsSetElementInfo> elementInfoList) throws Exception {
        String ids = new String();
        String eleIds = new String();
        for (StSetInfo baseInfo : setInfoList) {
            StSetInfo model = getBySetCode(baseInfo.getCode());
            if (model != null) {
                ids += model.getId() + ",";
                baseInfo.setId(model.getId());
            }
        }
        if (StringUtil.isNullOrWhiteSpace(ids)) {
            ids = ids.substring(0, ids.length() - 1);
            deleteByIds(ids);
        }
        int result = addList(setInfoList);
        importRsSetElementInfo(elementInfoList);
        return result;
    }

    /**
     * 数据集(字段关联)批量导入
     *
     * @param infoList
     * @return
     */

    public int importRsSetElementInfo(List<StRsSetElementInfo> infoList) throws Exception {
        // TODO: 2016/5/9
        String ids = new String();
        for (StRsSetElementInfo info : infoList) {
            if (StringUtil.isNullOrWhiteSpace(info.getSetCode())) continue;
            StSetInfo setInfo = getBySetCode(info.getSetCode());
            if (setInfo == null) continue;
            info.setFkSetId(setInfo.getId());
            if (StringUtil.isNullOrWhiteSpace(info.getFieldCode())) continue;
            StRsSetElementInfo model = rsSetElementInfoService.getBySetCode(info.getSetCode(), info.getFieldCode());
            if (model != null) {
                ids = ids + model.getId() + ",";
                info.setId(model.getId());
            }
            //获取基础数据元的Id
            if (!StringUtil.isNullOrWhiteSpace(info.getBaseElementCode())) {
                StElementInfo e = elementInfoService.getOne(info.getBaseElementCode());
                if (e != null) info.setFkElementId(e.getId());
            }

            /**
             * 字典
             */

            if (!StringUtil.isNullOrWhiteSpace(info.getDictCode())) {
                String dictId = getDictId(info.getDictCode());
                if (!StringUtil.isNullOrWhiteSpace(dictId)) info.setFkDictId(dictId);
            }
        }
        if (!StringUtil.isNullOrWhiteSpace(ids)) {
            ids = ids.substring(0, ids.length() - 1);
            rsSetElementInfoService.deleteByIds(ids);
        }

        return rsSetElementInfoService.addList(infoList);
    }

    @Override
    public int getSingle(Map<String, Object> map) {
        return getMapper().getSingle(map);
    }

    @Override
    public List<StSetInfo> getByPid(String pid) {
        return getMapper().getByPid(pid);
    }

    @Override
    public int add(StSetInfo entity) throws Exception {
        Map<String, Object> map = MapUtil.createMap(m -> {
            m.put("code", entity.getCode());
        });
        if (getSingle(map) > 0) {
            super.dispose(map);
            throw new BusinessException("001", entity.getCode());
        }
        super.dispose(map);
        return super.add(entity);
    }

    @Override
    public int update(StSetInfo entity) throws Exception {
        Map<String, Object> map = MapUtil.createMap(m -> {
            m.put("code", entity.getCode());
            m.put("id", entity.getId());
        });
        if (getSingle(map) > 0) {
            super.dispose(map);
            throw new BusinessException("002", entity.getCode());
        }
        super.dispose(map);
        return super.update(entity);
    }

    @Override
    public StSetInfo getBySetCode(String code) {
        return getMapper().getBySetCode(code);
    }

    @Override
    public String getDictId(String code) {
        return getMapper().getDictId(code);
    }

    @Override
    public StSetInfo getRelationById(String id) {
        return getMapper().getRelationById(id);
    }


}