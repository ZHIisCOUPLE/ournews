package com.our.news.admin.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author ljs
 * @time 2018-11-01
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {
    @Autowired
    private ExcelImpl excelImpl;

    @RequestMapping(value="/down_excel")
    public @ResponseBody
    String dowm(HttpServletResponse response,String name){
        response.setContentType("application/binary;charset=UTF-8");
        try{
            ServletOutputStream out=response.getOutputStream();
            try {
                //设置文件头：最后一个参数是设置下载文件名(这里我们叫：张三.pdf)
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(name+".xls", "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            String[] titles;
            if(name.equals("user")){
                titles = new String[]{ "用户id", "用户姓名", "用户密码","用户电话","用户邮箱","创建时间","更新时间","状态"};
                excelImpl.exportUser(titles, out);
            }else{
                titles = new String[]{ "新闻id", "新闻标题","作者","新闻内容","创建时间","更新时间","类型"};
                excelImpl.exportNews(titles, out);
            }
            return "success";
        } catch(Exception e){
            e.printStackTrace();
            return "导出信息失败";
        }
    }

    @RequestMapping("/user")
    public String upUser(@RequestParam("file") CommonsMultipartFile file){
        try {
            Workbook workbook = new HSSFWorkbook(file.getInputStream());
            excelImpl.upUsers(workbook);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/user/list";
    }

    @RequestMapping("/new")
    public String upNews(@RequestParam("file") CommonsMultipartFile file){
        try {
            Workbook workbook = new HSSFWorkbook(file.getInputStream());
            excelImpl.upNews(workbook);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/content/news/list";
    }
}
