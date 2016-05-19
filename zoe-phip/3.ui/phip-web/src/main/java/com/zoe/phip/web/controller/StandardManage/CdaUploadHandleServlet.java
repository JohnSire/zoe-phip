package com.zoe.phip.web.controller.StandardManage;

import com.alibaba.dubbo.common.json.JSONArray;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.zoe.phip.infrastructure.entity.ServiceResultT;

import com.zoe.phip.web.model.UploadRes;
import com.zoe.phip.web.model.sdm.StElementInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by chenzhisen on 2016/5/12.
 */

public class CdaUploadHandleServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServiceResultT<UploadRes> serviceResultT = new ServiceResultT<UploadRes>();
        UploadRes uploadRes=new UploadRes();


        String fileContent = "";
        String message="";
        try {
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                //按照传统方式获取数据
                return;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                //如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {

                } else {//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    System.out.println(filename);
                    if (filename == null || filename.trim().equals("")) {
                        message = "请选择文件";
                        uploadRes.setMessage(message);

                        continue;
                    }

                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    fileContent = convertStreamToString(in);
                    in.close();
                    item.delete();

                    uploadRes.setFileContent(fileContent);
                    serviceResultT.setIsSuccess(true);
                  ;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        serviceResultT.setResult(uploadRes);
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSON(serviceResultT).toString());


    }
    public static String  convertStreamToString(InputStream is) {



        StringBuilder sb = new StringBuilder();

        String line = null;

        try {
            BufferedReader reader = new BufferedReader(  new InputStreamReader(is,"utf-8"));
            while ((line = reader.readLine()) != null) {

                sb.append(line + " ");

            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                is.close();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }
        return sb.toString();

    }

}
