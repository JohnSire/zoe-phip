/*
 * Powered By zoe
 * Since 2008 - 2016
 */


package com.zoe.phip.dao.sm;

import com.zoe.phip.dao.MyMapper;
import com.zoe.phip.model.sm.SystemDictItem;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @date 2016-03-22
 */
public interface SystemDictItemMapper extends MyMapper<SystemDictItem> {
    List<SystemDictItem> getDataItemList(Map map);
}

