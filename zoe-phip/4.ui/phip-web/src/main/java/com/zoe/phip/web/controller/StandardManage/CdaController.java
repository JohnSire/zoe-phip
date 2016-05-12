package com.zoe.phip.web.controller.StandardManage;

import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.DataContext;
import com.zoe.phip.web.context.ServiceFactory;
import com.zoe.phip.web.controller.BaseController;
import com.zoe.phip.web.model.sdm.StCdaInfo;
import com.zoe.phip.web.model.sdm.StSetInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chenzhisen on 2016/5/6.
 */
@Controller
@RequestMapping("cda")
public class CdaController extends BaseController {
    //region  View


    @RequestMapping("/view/cdaList")
    @AuthAction(permission = {Permission.View}, name = "查看")
    public String ToCdaList(HttpServletRequest request, Model model) {
        return "/StandardManage/cda/cdaList";
    }

    @RequestMapping("/view/dataSetList")
    @AuthAction(permission = {Permission.View}, name = "查看")
    public String ToDataSetList(HttpServletRequest request, Model model) {
        return "/StandardManage/cda/dataSetList";
    }

    @RequestMapping("/view/cdaDetail")
    public String ToDataSetDetail(HttpServletRequest request, Model model) {

        return "/StandardManage/cda/cdaDetail";
    }

    @RequestMapping("/view/showXSLToStruct")
    public String ToShowXSLToStruct(HttpServletRequest request, Model model) {

        return "/StandardManage/cda/ShowXSLToStruct";
    }

    @RequestMapping("/view/editXsl")
    public String ToEditXsl(HttpServletRequest request, Model model) {

        return "/StandardManage/cda/editXsl";
    }


    @RequestMapping("/view/previewXsl")
    public String ToPreviewXsl(HttpServletRequest request, Model model) {

        return "/StandardManage/cda/previewXsl";
    }

    @RequestMapping("/view/editXml")
    public String ToEditXml(HttpServletRequest request, Model model) {

        return "/StandardManage/cda/editXml";
    }

    @RequestMapping("/view/showXMLToStruct")
    public String ToShowXMLToStruct(HttpServletRequest request, Model model) {

        return "/StandardManage/cda/showXMLToStruct";
    }
    //endregion

    //region   CDA Json

    /**
     * 根据关键字查询CDA列表信息
     *
     * @param keyWord
     * @return
     */
    @RequestMapping(value = "/getCdaList")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResultT<PageList<StCdaInfo>> getCdaList(String keyWord) {
        return ServiceFactory.getStCdaInfoService().getDataPageList(ComSession.getUserInfo(), keyWord, getQueryPage());
    }

    /**
     * 根据id查询Cda信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getCdaInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Query}, name = "查询")
    public ServiceResult getCdaInfo(String id) {
        return ServiceFactory.getStCdaInfoService().getById(ComSession.getUserInfo(), id);
    }

    /**
     * 新增Cda信息
     *
     * @param stCdaInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/addCdaInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Add}, name = "新增")
    public ServiceResult addCdaInfo(StCdaInfo stCdaInfo, HttpServletRequest request) {
        return ServiceFactory.getStCdaInfoService().add(ComSession.getUserInfo(), stCdaInfo);
    }

    /**
     * 更新Cda信息
     *
     * @param stCdaInfo
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateCdaInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "更新")
    public ServiceResult updateCdaInfo(StCdaInfo stCdaInfo, HttpServletRequest request) {
        return ServiceFactory.getStCdaInfoService().update(ComSession.getUserInfo(), stCdaInfo);
    }

    /**
     * 删除Cda信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delCdaInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "更新")
    public ServiceResult delCdaInfo(String id) {
        return ServiceFactory.getStCdaInfoService().deleteById(ComSession.getUserInfo(), id);
    }

    /**
     * 批量删除Cda信息
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delCdaList")
    @ResponseBody
    @AuthAction(permission = {Permission.Update}, name = "更新")
    public ServiceResult delCdaList(String ids) {
        return ServiceFactory.getStCdaInfoService().deleteByIds(ComSession.getUserInfo(), ids);
    }

    /**
     * 根据标准来源和关键字查询CDA信息
     *
     * @param fkSourceId
     * @param keyWord
     * @return
     */
    @RequestMapping(value = "/getBySourceId")
    @ResponseBody
    public ServiceResultT<PageList<StCdaInfo>> getBySourceId(String fkSourceId, String keyWord) {
        return ServiceFactory.getStCdaInfoService().getBySourceId(ComSession.getUserInfo(), fkSourceId, keyWord, getQueryPage());
    }
    //endregion

    //region CDA 与数据集 JSon
//    public ServiceResultT<List<StSetInfo>> getSetList(String keyWord, String fkCdaId) {
//        return ServiceFactory.getStSetInfoService().getByCdaId(ComSession.getUserInfo(), fkCdaId,keyWord);
//    }
    //endregion

    //region xsl 与 xml
    @RequestMapping(value = "/uploadXsl")
    @ResponseBody
    public ServiceResultT<String> uploadXsl(String keyWord, String fkCdaId) {
        return new ServiceResultT<String>();
    }

    @RequestMapping(value = "/uploadXml")
    @ResponseBody
    public void uploadXml(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final long MAX_SIZE = 3 * 1024 * 1024;// 设置上传文件最大为 3M
        // 允许上传的文件格式的列表
        final String[] allowedExt = new String[]{"jpg", "jpeg", "gif", "png"};

        response.setContentType("text/html");
        // 设置字符编码为UTF-8, 这样支持汉字显示
        // response.setCharacterEncoding("GBK");

        // 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload
        DiskFileItemFactory dfif = new DiskFileItemFactory();
        dfif.setSizeThreshold(4096);// 设置上传文件时用于临时存放文件的内存大小,这里是4K.多于的部分将临时存在硬盘
        dfif.setRepository(new File(request.getRealPath("/")+ "/file/cda_xsl"));// 设置存放临时文件的目录,web根目录下的ImagesUploadTemp目录

        // 用以上工厂实例化上传组件
        ServletFileUpload sfu = new ServletFileUpload(dfif);
        // 设置最大上传尺寸
        sfu.setSizeMax(MAX_SIZE);

        PrintWriter out = response.getWriter();
        // 从request得到 所有 上传域的列表
        List fileList = null;
        sfu.setHeaderEncoding("UTF-8");
        try {
            fileList = sfu.parseRequest(request);
        } catch (FileUploadException e) {// 处理文件尺寸过大异常
            if (e instanceof FileUploadBase.SizeLimitExceededException) {
                out.print("<script>alert('文件尺寸超过规定大小:" + MAX_SIZE
                        + "字节');history.back();</script>");
                return;
            }
            e.printStackTrace();
        }
        // 没有文件上传
        if (fileList == null || fileList.size() == 0) {
            out.print("<script>alert('请选择上传文件!');history.back();</script>");
            return;
        }
        HashMap<String, String> paramMap = new HashMap<String, String>();
        // 得到所有上传的文件
        Iterator fileItr = fileList.iterator();
        // 循环处理所有文件
        FileItem fileUp = null;
        String path = null;
        while (fileItr.hasNext()) {
            FileItem fileItem = null;
            long size = 0;
            // 得到当前文件
            fileItem = (FileItem) fileItr.next();
            // 忽略简单form字段而不是上传域的文件域(<input type="text" />等)
            if (fileItem == null || fileItem.isFormField()) {
                String formname = fileItem.getFieldName();//获取form中的名字
                String formcontent = fileItem.getString();
                formname = new String(formname.getBytes(), "GBK");
                formcontent = new String(formcontent.getBytes(), "GBK");
                paramMap.put(formname, formcontent);
            } else {
                //得到放文件的item
                fileUp = fileItem;
                // 得到文件的完整路径
                path = fileItem.getName();
                // 得到文件的大小
                size = fileItem.getSize();
                if ("".equals(path) || size == 0) {
                    out.print("<script>alert('请选择上传文件!');history.back();</script>");
                    return;
                }

            }

        }
        // 得到去除路径的文件名
        String t_name = path.substring(path.lastIndexOf("\\") + 1);
        // 得到文件的扩展名(无扩展名时将得到全名)
        String t_ext = t_name.substring(t_name.lastIndexOf(".") + 1);
        // 拒绝接受规定文件格式之外的文件类型
        int allowFlag = 0;
        int allowedExtCount = allowedExt.length;
        for (; allowFlag < allowedExtCount; allowFlag++) {
            if (allowedExt[allowFlag].equals(t_ext))
                break;
        }
        if (allowFlag == allowedExtCount) {
            StringBuffer sb = new StringBuffer();
            for (allowFlag = 0; allowFlag < allowedExtCount; allowFlag++)
                sb.append("*." + allowedExt[allowFlag]);
            out.println("<script>alert('请上传以下类型的文件" + sb.toString()
                    + "');history.back();</script>");
            return;
        }
        long now = System.currentTimeMillis();
        // 根据系统时间生成上传后保存的文件名
        String prefix = String.valueOf(now);
        // 保存的最终文件完整路径,保存在web根目录下的ImagesUploaded目录下
        String u_name = request.getRealPath("/") + "ImagesUploaded/"
                + prefix + "." + t_ext;
        // 相对项目路径
        String file_url = request.getContextPath() + "/"
                + "ImagesUploaded/" + prefix + "." + t_ext;
        try {
            // 保存文件
            fileUp.write(new File(u_name));
            out.println("<script  type='text/javascript'>parent.KE.plugin[\"image\"].insert('" + paramMap.get("id")
                    + "', '" + file_url + "','" + paramMap.get("imgWidth") + "','"
                    + paramMap.get("imgHeight") + "','" + paramMap.get("imgBorder") + "','" + paramMap.get("imgTitle")
                    + "')</script>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion
}
