
package com.zoe.phip.infrastructure.constants;

import java.math.BigDecimal;

/**
 * <pre>
 * 系统框架常量
 * </pre>
 * @author hyf
 * @version $Rev: 18 $
 * <pre>
 * 修改记录
 *    修改后版本    |  修改人     |   修改时间        |   修改内容
 * </pre>
 */
public class BasConstant {
	/**
	 * 布尔型的字符串标示，“否”/false统一使用 0 
	 */
	public static final String BOOLEAN_FALSE = "0";
	public static final String BOOLEAN_FALSE_STR= "false";
	/**
	 * 布尔型的字符串标示，“是”/true统一使用 1 
	 */
	public static final String BOOLEAN_TRUE = "1";
	public static final String BOOLEAN_TRUE_STR= "true";
	
	/**
	 * BigDecimal常用常量
	 */
	public static final BigDecimal BIGDECIMAL_ZERO = new BigDecimal("0");
	public static final BigDecimal BIGDECIMAL_WAN = new BigDecimal("10000");
	
	/**
	 * 超级管理员
	 */
	public static final String DEFAULT_USER_CODE = "admin";
	
	/**
	  * Spring配置的消息资源ID
	  */
	public static final String MESSAGE_SOURCE_ID = "messageSource";
	/**
	  * 基础框架配置文件名称
	  */
	public static final String PROPERTIES_BASE_NAME = "bas";
	
	/**
	 * 将要执行的action放在requeset范围内，以备后续调用
	 * @author yetaishan
	 */
	public static final String LOBSTERWEB_ACTION_REQUEST_KEY ="_LOBSTERWEB_ACTION_";
	
	/**
	 * 当前系统部署的数据库类型的键
	 * @author yetaishan
	 */
	public static final String SYS_DB_TYPE_KEY= "system_db_type";
	/**
	 * 业务模块编号在request中的key
	 * @author yetaishan
	 */
	public static final String BIZ_MODULE_ID_KEY = "moduleID";
	
	/**
	 * Session中的用户信息对于的key
	 */
	public static final String USER_INFO = "userInfo";
	
	/**
	 * 存储新增bean列表的Key
	 */
	public static final String INSERT_BEAN_LIST = "insertBeanList"; 
	/**
	 * 存储删除bean列表的Key
	 */
	public static final String DELETE_BEAN_LIST = "deleteBeanList"; 
	/**
	 * 存储修改bean列表的Key
	 */
	public static final String UPDATE_BEAN_LIST = "updateBeanList"; 
	/**
	 * 存储新增bean列表的Key
	 */
	public static final String CHECKED_LIST = "checkedList";
	/**
	 * 存储删除bean列表的Key
	 */
	public static final String UNCHECKED_LIST = "uncheckedList";

	

}
