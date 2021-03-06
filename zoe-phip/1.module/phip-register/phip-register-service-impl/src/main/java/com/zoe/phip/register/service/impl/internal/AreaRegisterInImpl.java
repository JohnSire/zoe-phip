package com.zoe.phip.register.service.impl.internal;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageInfo;
import com.zoe.phip.infrastructure.annotation.ErrorMessage;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.QueryPage;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.exception.BusinessException;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.module.service.impl.in.BaseInServiceImpl;
import com.zoe.phip.module.service.util.SqlHelper;
import com.zoe.phip.register.dao.IAreaBaseInfoMapper;
import com.zoe.phip.register.model.AreaBaseInfo;
import com.zoe.phip.register.service.internal.IAreaRegisterIn;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * Created by zengjiyang on 2016/4/22.
 */
@Repository("AreaRegisterIn")
@Service(interfaceClass = IAreaRegisterIn.class, proxy = "sdpf", protocol = {"dubbo"}, dynamic = true)
@ErrorMessage(code = "001", message = "由于内容重复注册，注册失败")
@ErrorMessage(code = "002", message = "由于内容重复注册，更新失败")
@ErrorMessage(code = "003", message = "由于查询内容不存在，查询失败")
public class AreaRegisterInImpl extends BaseInServiceImpl<AreaBaseInfo, IAreaBaseInfoMapper> implements IAreaBaseInfoMapper {

    @Override
    public int add(AreaBaseInfo entity) throws Exception {
        String id = StringUtil.isNullOrWhiteSpace(entity.getId()) ? UUID.randomUUID().toString() : entity.getId();
        if (ifCodeExist(id, entity.getCode(), 1)) {
            throw new BusinessException("001");
        }
        entity.setId(id);
        return super.add(entity);
    }

    @Override
    public int update(AreaBaseInfo entity) throws Exception {
        if (ifCodeExist(entity.getId(), entity.getCode(), 1)) {
            throw new BusinessException("002");
        }
        return super.update(entity);
    }

    public AreaBaseInfo getAreaBaseInfo(String id) throws Exception {
        AreaBaseInfo baseInfo = getMapper().selectByPrimaryKey(id);
        if (baseInfo == null) {
            throw new BusinessException("003");
        }
        return baseInfo;
    }

    public PageList<AreaBaseInfo> getDataList(String key, QueryPage queryPage) {
        PageList<AreaBaseInfo> pageList = new PageList<>();
        SqlHelper.startPage(queryPage);
        Map map = new HashMap<>();
        if (!StringUtil.isNullOrWhiteSpace(key)) map.put("key", key);
        List<AreaBaseInfo> results = getMapper().getDataList(map);
        PageInfo<AreaBaseInfo> pageInfo = new PageInfo<>(results);
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(results);
        map.clear();
        map = null;
        return pageList;
    }

    public PageList<AreaBaseInfo> getAreaChildrenRegistry(String id, String key, QueryPage queryPage) {
        PageList<AreaBaseInfo> pageList = new PageList<>();
        //分页
        SqlHelper.startPage(queryPage);
        Map<String, Object> map = new TreeMap<>();
        map.put("id", id);
        if (!StringUtil.isNullOrWhiteSpace(key)) map.put("key", key);
        List<AreaBaseInfo> result = getMapper().getChildren(map);
        PageInfo<AreaBaseInfo> pageInfo = new PageInfo<>();
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(result);
        map.clear();
        map = null;
        return pageList;
    }

    public AreaBaseInfo areaHistoryRegistryQuery(String code) {
        Map<String, Object> map = new TreeMap<>();
        map.put("historyId", code);
        AreaBaseInfo baseInfo = getMapper().getAreaBaseInfo(map);
        map.clear();
        map = null;
        return baseInfo;

    }

    public AreaBaseInfo getTopAreaBaseInfo() {
        return getMapper().getTopAreaBaseInfo();
    }

    private boolean ifCodeExist(String id, String code, int type) {
        Example example = new Example(AreaBaseInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("code", code);
        if (type != 0) {
            criteria.andNotEqualTo("id", id);
        }
        int count = getMapper().selectCountByExample(example);
        return count > 0;
    }

    public AreaBaseInfo getAreaByName(String name){
        return getAreaInfoByName(name);
    }

    /**
     * 根据Code获取子节点
     * @param code
     * @param queryPage
     * @return
     */
    public PageList<AreaBaseInfo> getAreaChildrenByCode(String code, QueryPage queryPage){
        PageList<AreaBaseInfo> pageList = new PageList<>();
        queryPage.setOrderBy("T1.NAME");
        //分页
        SqlHelper.startPage(queryPage);
        List<AreaBaseInfo> result = getMapper().getChildrenByCode(code);
        PageInfo<AreaBaseInfo> pageInfo = new PageInfo<>();
        pageList.setTotal((int) pageInfo.getTotal());
        pageList.setRows(result);
        return pageList;
    }



    @Override
    public AreaBaseInfo getAreaBaseInfo(Map<String, Object> map) {
        return getMapper().getAreaBaseInfo(map);
    }

    @Override
    public List<AreaBaseInfo> getChildren(Map<String, Object> map) {
        return getMapper().getChildren(map);
    }

    @Override
    public List<AreaBaseInfo> getDataList(Map<String, Object> map) {
        return getMapper().getDataList(map);
    }

    @Override
    public AreaBaseInfo getAreaInfoByName(String name) {
        return getMapper().getAreaInfoByName(name);
    }

    @Override
    public List<AreaBaseInfo> getChildrenByCode(String code) {
        return getMapper().getChildrenByCode(code);
    }

    @Override
    public int defaultUpdate(AreaBaseInfo t) {
        return getMapper().defaultUpdate(t);
    }
}
