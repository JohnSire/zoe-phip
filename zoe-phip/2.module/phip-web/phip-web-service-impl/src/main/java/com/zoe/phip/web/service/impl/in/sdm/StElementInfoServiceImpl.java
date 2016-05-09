/*
 * Powered By zoe
 * Since 2008 - 2016
 */

package com.zoe.phip.web.service.impl.in.sdm;

import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.MapUtil;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.service.util.SqlHelper;
import com.zoe.phip.web.dao.sdm.IStElementInfoMapper;
import com.zoe.phip.web.model.sdm.StElementInfo;
import com.zoe.phip.web.model.sdm.StNormSourceInfo;
import com.zoe.phip.web.model.sm.SystemDictCategory;
import com.zoe.phip.web.model.sm.SystemDictItem;
import com.zoe.phip.web.service.impl.in.sm.SystemDictItemServiceImpl;
import com.zoe.phip.web.service.sdm.IStElementInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.alibaba.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author
 * @version 1.0
 * @date 2016-05-03
 */
@Repository("stElementInfoService")
@Service(interfaceClass = IStElementInfoService.class, proxy = "sdpf", dynamic = true)
@ErrorMessage(code = "001", message = "数据元标识({0})已经存在!")
public class StElementInfoServiceImpl extends BaseInServiceImpl<StElementInfo, IStElementInfoMapper> implements IStElementInfoMapper {

    @Autowired
    private SystemDictItemServiceImpl dictItemServiceImpl;

    @Override
    public int add(StElementInfo entity) throws Exception {
        Map<String, Object> map = MapUtil.createMap(m -> {
            m.put("code", entity.getCode());
        });
        if (getSingle(map) > 0) throw new BusinessException("001", entity.getCode());
        map.clear();
        map = null;
        return super.add(entity);
    }

    @Override
    public int update(StElementInfo entity) throws Exception {
        Map<String, Object> map = MapUtil.createMap(m -> {
            m.put("code", entity.getCode());
            m.put("id", entity.getId());
        });
        if (getSingle(map) > 0) throw new BusinessException("001", entity.getCode());
        map.clear();
        map = null;
        return super.update(entity);
    }


    public PageList<StElementInfo> getDataPageList(String key, QueryPage queryPage) {

        PageList<StElementInfo> pageList = new PageList<>();
        SqlHelper.startPage(queryPage);
        Map<String, Object> map = new TreeMap<>();
        if (!StringUtil.isNullOrWhiteSpace(key)) map.put("key", key);
        List<StElementInfo> results = getMapper().getDataPageList(map);
        PageInfo<StElementInfo> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        return pageList;
    }

    /**
     * 导入数据元
     *
     * @param infoList
     * @return
     */
    public int importElement(List<StElementInfo> infoList) throws Exception {
        String ids = "";
        for (StElementInfo baseInfo : infoList) {
            StElementInfo model = getOne(baseInfo.getCode());
            if (model != null) {
                baseInfo.setId(model.getId());
                ids = ids + model.getId() + ",";
            }
            /*字典设置*/
            if (StringUtil.isNullOrWhiteSpace(baseInfo.getDictCode())) {
                String dictId = getDictId(baseInfo.getDictCode());
                if (!StringUtil.isNullOrWhiteSpace(dictId)) baseInfo.setFkDictId(dictId);
            }
            /*分类设置*/
            if (StringUtil.isNullOrWhiteSpace(baseInfo.getTypeName())) {
                SystemDictItem item = dictItemServiceImpl.getItemByCategoryCodeAndName("ELEMENT_TYPE", baseInfo.getTypeName());
                if (item != null) baseInfo.setFkTypeId(item.getId());
            }
        }
        if (!StringUtil.isNullOrWhiteSpace(ids)) {
            ids = ids.substring(0, ids.length() - 1);
            deleteByIds(ids);
        }
        return addList(infoList);
    }

    public List<StElementInfo> exportElement(String fkSourceId){
        Map<String,Object> map =new TreeMap<>();
        if(!StringUtil.isNullOrWhiteSpace(fkSourceId))
            map.put("fkSourceId",fkSourceId);
        return getMapper().getDataPageList(map);
    }

    @Override
    public List<StElementInfo> getDataPageList(Map<String, Object> map) {
        return getMapper().getDataPageList(map);
    }

    @Override
    public int getSingle(Map<String, Object> map) {
        return getMapper().getSingle(map);
    }

    @Override
    public StElementInfo getOne(String code) {
        return getMapper().getOne(code);
    }

    @Override
    public String getDictId(String code) {
        return getDictId(code);
    }
}