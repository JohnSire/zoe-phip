package com.zoe.phip.web.controller.StandardManage;

import com.zoe.phip.infrastructure.annotation.AuthAction;
import com.zoe.phip.infrastructure.entity.PageList;
import com.zoe.phip.infrastructure.entity.ServiceResult;
import com.zoe.phip.infrastructure.entity.ServiceResultT;
import com.zoe.phip.infrastructure.security.Permission;
import com.zoe.phip.infrastructure.util.StringUtil;
import com.zoe.phip.web.context.ComSession;
import com.zoe.phip.web.context.ServiceFactory;
import com.zoe.phip.web.controller.BaseController;
import com.zoe.phip.web.model.sdm.StCdaInfo;
import com.zoe.phip.web.model.sdm.StRsCdaSetInfo;
import com.zoe.phip.web.model.sdm.StSetInfo;
import org.dom4j.Document;
import org.dom4j.io.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    @AuthAction(permission = {Permission.Delete}, name = "删除")
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
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delCdaList(String ids) {
        return ServiceFactory.getStCdaInfoService().deleteByIds(ComSession.getUserInfo(), ids);
    }


    /**
     * 删除Cda数据集关系
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delRsCdaInfo")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delRsCdaInfo(String id) {
        return ServiceFactory.getStRsCdaSetInfoService().deleteByFkSetId(ComSession.getUserInfo(), id);
    }

    /**
     * 批量删除Cda数据集关系
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delRsCdaList")
    @ResponseBody
    @AuthAction(permission = {Permission.Delete}, name = "删除")
    public ServiceResult delRsCdaList(String ids) {
        return ServiceFactory.getStRsCdaSetInfoService().deleteByFkSetIds(ComSession.getUserInfo(), ids);
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


    /**
     * CDA 与数据集 JSon
     *
     * @param keyWord
     * @param fkCdaId
     * @return
     */
    @RequestMapping(value = "/getSetList")
    @ResponseBody
    public ServiceResultT<PageList<StSetInfo>> getSetList(String keyWord, String fkCdaId) {
        return ServiceFactory.getStSetInfoService().getByCdaId(ComSession.getUserInfo(), fkCdaId, keyWord, getQueryPage());
    }

    @RequestMapping(value = "/updateByCdaId")
    @ResponseBody
    public ServiceResult updateByCdaId(String fkCdaId, String setIds) {
        String[] ids = setIds.split(",");

        List<StRsCdaSetInfo> list = null;
        if (!StringUtil.isNullOrWhiteSpace(setIds)) {
            list = new ArrayList<StRsCdaSetInfo>();
            StRsCdaSetInfo model = new StRsCdaSetInfo();
            for (String fkSetId : ids
                    ) {
                model = new StRsCdaSetInfo();
                model.setId(UUID.randomUUID().toString());
                model.setFkCdaId(fkCdaId);
                model.setFkSetId(fkSetId);
                list.add(model);
            }
        }
        ;

        return ServiceFactory.getStRsCdaSetInfoService().updateByCdaId(ComSession.getUserInfo(), fkCdaId, list);
    }


    //region xsl 与 xml


    @RequestMapping(value = "/saveXml")
    @ResponseBody
    public ServiceResult saveXml(String id, String xml) {
        StCdaInfo model = ServiceFactory.getStCdaInfoService().getById(ComSession.getUserInfo(), id).getResult();
        model.setSampleXml(xml);
        return ServiceFactory.getStCdaInfoService().update(ComSession.getUserInfo(), model);
    }

    @RequestMapping(value = "/saveXsl")
    @ResponseBody
    public ServiceResult saveXsl(String id, String xsl, String type) {
        StCdaInfo model = ServiceFactory.getStCdaInfoService().getById(ComSession.getUserInfo(), id).getResult();
        switch (type) {
            case "ToHtml"://显示
                model.setToHtmlXsl(xsl);
                break;
            case "ToSummary"://摘要
                model.setToSummaryXsl(xsl);
                break;
            case "ToSet"://解析
                model.setToSetXsl(xsl);
                break;
        }
        return ServiceFactory.getStCdaInfoService().update(ComSession.getUserInfo(), model);

    }

    /**
     * 将xml以xsl样式转化为html字符串
     * @param xmlString xml字符串
     * @param xslPath xsl路径
     * @return
     */
    public String getHtmlString(String xmlString,String xslPath){
      //  log.info("开始执行getHtmlString(...)方法");
        String returnDocStr = "";
        try {
            SAXReader reader = new SAXReader();
            ByteArrayInputStream bais = new ByteArrayInputStream(xmlString.getBytes());
            Document doc = reader.read(bais);
            Document transformDoc = this.transformDocument(doc,xslPath);
            returnDocStr = this.write2String(transformDoc);
           // log.info("getHtmlString(...)执行成功!");
        } catch (Exception e) {
           // log.info("getHtmlString(...)方法执行失败,提示信息["+e.getMessage()+"]");
        }
        return returnDocStr;
    }

    /**
     * 通过xsl将xml数据文件转化doc对象
     * @param doc xml文档对象
     * @param xslPath xls文件路径
     * @return
     */
    private Document transformDocument(Document doc,String xslPath){
    //    log.info("开始执行 transformDocument(...)方法");
        TransformerFactory factory = TransformerFactory.newInstance();
        Document transformerDoc = null;
        try {
            Transformer transformer = factory.newTransformer(new StreamSource(xslPath));
            DocumentSource docSource = new DocumentSource(doc);
            DocumentResult docResult = new DocumentResult();
            transformer.transform(docSource, docResult);
            transformerDoc = docResult.getDocument();
          //  log.info("transformDocument(...)执行成功!");
        } catch (Exception e) {
          //  log.info("transformDocument(...)方法执行失败,提示信息["+e.getMessage()+"]");
        }
        return transformerDoc;
    }

    /**
     * 将doc文档对象转化为html字符串
     * @param transformDoc doc文档
     * @return
     */
    private String write2String(Document transformDoc){
       // log.info("开始执行 write2String(...)方法");
        StringWriter strWriter = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("GBK");
        format.setXHTML(true);
        HTMLWriter htmlWriter = new HTMLWriter(strWriter,format);
        format.setExpandEmptyElements(false);
        try {
            htmlWriter.write(transformDoc);
            htmlWriter.flush();
           // log.info("write2String(...)执行成功!");
        } catch (IOException e) {
          //  log.info("write2String(...)方法执行失败,提示信息["+e.getMessage()+"]");
        }
        return strWriter.toString();
    }

    //endregion
}
