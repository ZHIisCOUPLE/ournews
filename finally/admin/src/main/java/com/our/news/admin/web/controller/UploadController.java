package com.our.news.admin.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UploadController {
    @Value("${fileImagesUrl}")
    private String IMAGES_PATH;

    /**
     * 文件上传
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile dropFile,MultipartFile editorFile, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        //前端上传的文件
        MultipartFile myFile = dropFile == null ? editorFile : dropFile;

        // 获取文件后缀
        String fileName = myFile.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));



        // 判断路径是否存在，不存在则创建文件夹
        File file = new File(IMAGES_PATH);

        if (!file.exists()) {
            file.mkdir();
        }

        // 将文件写入目标
        file = new File(IMAGES_PATH, UUID.randomUUID() + fileSuffix);
        try {
            myFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Dropzone上传
        if (dropFile!=null){
            result.put("fileName",file.getName());
        }
        else {
            // 返回给 wangEditor 的数据格式
            result.put("errno", 0);
            result.put("data", new String[]{IMAGES_PATH + file.getName()});
        }
        return result;
    }

    /**
     * 远程获取图片链接
     * @param fileName
     * @param response
     */
    @RequestMapping("/picShow")
    public void picShow(String fileName, HttpServletResponse response, HttpServletRequest request){
        if (fileName==null || fileName == ""){
            return;
        }
        InputStream inputStream = null;
        try{
            //.jpg / .png 所以要加1
            String suffiix = fileName.substring(fileName.lastIndexOf(".")+1);

            if ("jpg".equals(suffiix)){
                response.setContentType("image/jpeg");
            }else if ("png".equals(suffiix)){
                response.setContentType("image/png");
            }
            inputStream = new FileInputStream(new File(IMAGES_PATH+fileName));
            OutputStream outputStream = response.getOutputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while (( len = inputStream.read(b)) > 0){
                outputStream.write(b,0,len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
