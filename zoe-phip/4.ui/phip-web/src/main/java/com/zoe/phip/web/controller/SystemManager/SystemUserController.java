package com.zoe.phip.web.controller.SystemManager;

import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.annotation.AuthController;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.security.MenuCode;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import com.zoe.phip.web.controller.BaseController;
import com.zoe.phip.web.model.sm.MenuData;
import com.zoe.phip.web.model.sm.SystemUser;
import com.zoe.phip.web.model.sm.UserCompetence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinzhixing on 2016/3/21.
 */
@Controller
@RequestMapping("/user")
@AuthController(code= MenuCode.SystemUser)
@Validated
public class SystemUserController extends BaseController {
    //region 用户管理视图开始
    //用户管理列表
    @RequestMapping("/list")
    @AuthAction(permission = {Permission.View},name = "查看")
    public String ToList(HttpServletRequest request, Model model) {
        return "SystemManage/SysUser/list";
    }
    //用户详详情
    @RequestMapping("/detail")
    @AuthAction(permission = {Permission.View},name = "查看")
    public String ToDetail(HttpServletRequest request, Model model) {
        return "SystemManage/SysUser/detail";
    }

    //修改密码
    @RequestMapping("/update/pwd")
    @AuthAction(permission = {Permission.View},name = "查看")
    public String ToPwd(HttpServletRequest request, Model model) {
        return "SystemManage/SysUser/updatePwd";
    }
    //endregion  用户管理视图结束


    //region 方法
    /**
     * 获取用户列表
     * @return
     */
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ResponseBody
    @AuthAction(permission = {Permission.Query},name = "查询")
    public ServiceResultT<PageList<SystemUser>> getUserList(HttpServletRequest request) {
        return ServiceFactory.getUserService()
                .getUserList(ComSession.getUserInfo(), null, request.getParameter("keyWord"), getQueryPage());

    }

    /**
     * 获取单个用户信息
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    @ResponseBody
    @AuthAction(permission = {Permission.Query},name = "查询")
    public ServiceResultT<SystemUser> getUserInfo(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        ServiceResultT<SystemUser> user = ServiceFactory.getUserService().getById(ComSession.getUserInfo(),id );
        return user;
    }


    /**
     * 新增用户
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST)
    @ResponseBody
    @AuthAction(permission = {Permission.Add},name = "新增")
    public ServiceResult addUserInfo( SystemUser userInfo, BindingResult br) {
        if(br.hasErrors())   System.out.println(br);

        return ServiceFactory.getUserService().add(ComSession.getUserInfo(), userInfo, br);
    }
    /**
     * 更新用户
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    @ResponseBody
    @AuthAction(permission = {Permission.Update},name = "修改")
    public ServiceResult updateUserInfo(SystemUser userInfo) {
        return ServiceFactory.getUserService().update(ComSession.getUserInfo(), userInfo);
    }

    /**
     * 更新用户状态
     * @param
     * @return
     */
    @RequestMapping(value = "/updateState", method = RequestMethod.POST)
    @ResponseBody
    @AuthAction(permission = {Permission.Update},name = "修改")
    public ServiceResult updateState(String id,int state) {
        return ServiceFactory.getUserService().updateState(ComSession.getUserInfo(),id,state);
    }

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delUserInfo", method = RequestMethod.GET)
    @ResponseBody
    @AuthAction(permission = {Permission.Delete},name = "删除")
    public ServiceResult deleteUserInfo(String id) {
        return ServiceFactory.getUserService().deleteById(ComSession.getUserInfo(), id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delUserList", method = RequestMethod.POST)
    @ResponseBody
    @AuthAction(permission = {Permission.Delete},name = "删除")
    public ServiceResult deleteUserList(String ids) {
        return ServiceFactory.getUserService().deleteByIds(ComSession.getUserInfo(), ids);
    }

    /**
     * 获取用户关联菜单
     * @return
     */
    @RequestMapping("/menu")
    @ResponseBody
    @AuthAction(permission = {Permission.Query},name = "查询")
    public ServiceResultT<List<MenuData>> getUserMenu() {
        return ServiceFactory.getMenuDataService().getCompetenceMenuByUser(ComSession.getUserInfo(), ComSession.getUserInfo().getUserId());
    }
    //endregion


    /**
     * 添加用户权限
     * @param catalogId
     * @param ids
     * @return
     */
    @RequestMapping(value = "/addUserAcc")
    @ResponseBody
    @AuthAction(permission = {Permission.Add},name = "新增")
    public ServiceResult addUserAcc(@RequestParam("catalogId") String catalogId, @RequestParam("ids") String ids) {
        List<UserCompetence> models = new ArrayList<UserCompetence>();
        String [] arrayids = ids.split(",");
        for(String  id:arrayids){
            if(StringUtil.isNullOrWhiteSpace(id))continue;
            UserCompetence menu = new UserCompetence();
            menu.setFkCompetenceCategoryId(catalogId);
            menu.setFkUserId(id);
            models.add(menu);
        }
        return ServiceFactory.getUserCompetenceService().saveList(ComSession.getUserInfo(),catalogId,models);
    }

    /**
     * 删除用户权限
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delUserAcc")
    @ResponseBody
    @AuthAction(permission = {Permission.Add},name = "新增")
    public ServiceResult delUserAcc( @RequestParam("ids") String ids) {
        return ServiceFactory.getUserCompetenceService().deleteByIds(ComSession.getUserInfo(),ids);
    }

}
