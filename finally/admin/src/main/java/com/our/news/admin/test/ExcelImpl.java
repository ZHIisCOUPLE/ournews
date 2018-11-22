package com.our.news.admin.test;

import com.our.news.admin.service.NewInfoService;
import com.our.news.admin.service.UserService;
import com.ournews.domain.entity.NewsInfo;
import com.ournews.domain.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ljs
 * @time 2018-11-01
 */
@Component
public class ExcelImpl {

    @Autowired
    private NewInfoService newInfoService;
    @Autowired
    private UserService userService;
    private
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public void upNews(Workbook workbook){
        try {
            User user = new User();
            user.setId(1L);
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            List<NewsInfo> list = new ArrayList<>();
            for(int i = 1 ; i < rows ; i++){
                Row row = sheet.getRow(i);
                int cols = row.getPhysicalNumberOfCells();
                if(getBool(row,cols)){
                    continue;
                }
                NewsInfo newsInfo = new NewsInfo();
                newsInfo.setName(getCell(row,1).getStringCellValue());
                newsInfo.setUser(user);
                newsInfo.setPicture("50599cd8-a56d-428a-be6f-b29e7306449c.jpeg");
                String de = getCell(row,3).getStringCellValue();
                newsInfo.setDetail(de);
                newsInfo.setCreated(df.parse(getCell(row,4).getStringCellValue()));
                newsInfo.setUpdated(df.parse(getCell(row,5).getStringCellValue()));
                newsInfo.setType(getCell(row,6).getStringCellValue());
                list.add(newsInfo);
            }
            for (NewsInfo newsInfo : list) {
                newInfoService.save(newsInfo);
            }
        } catch (Exception e) {
        }
    }

    public void upUsers(Workbook workbook){
        try {
            Sheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            List<User> list = new ArrayList<>();
            for(int i = 1 ; i < rows ; i++){
                Row row = sheet.getRow(i);
                int cols = row.getPhysicalNumberOfCells();
                if(getBool(row,cols)){
                    continue;
                }
                User user = new User();
                user.setUsername(getCell(row,1).getStringCellValue());
                user.setPassword("123");
                user.setPhone(getCell(row,3).getStringCellValue());
                user.setEmail(getCell(row,4).getStringCellValue());
                user.setCreated(df.parse(getCell(row,5).getStringCellValue()));
                user.setUpdated(df.parse(getCell(row,6).getStringCellValue()));
                String delete = getCell(row,7).getStringCellValue();
                user.setIsdelete(Integer.parseInt(delete.equals("删除") ? "0" : delete.equals("冻结") ? "2" : "1"));
                list.add(user);
            }
            for (User user : list) {
                userService.save(user);
            }
        } catch (Exception e) {
        }
    }

    public void exportNews(String[] titles, ServletOutputStream out) throws Exception{
        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");
            //设置对应的索引列的列宽 从0开始算
            hssfSheet.setColumnWidth(1, 18000);
            hssfSheet.setColumnWidth(2, 3500);
            hssfSheet.setColumnWidth(3, 10000);
            hssfSheet.setColumnWidth(4, 3500);
            hssfSheet.setColumnWidth(5, 3500);
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格样式
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
            //居中样式
            hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                //根据title的个数创建单元格
                hssfCell = row.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                if(i != 1){
                    hssfCell.setCellStyle(hssfCellStyle);//标题居中显示
                    hssfSheet.setDefaultColumnStyle(i,hssfCellStyle);//一列居中显示
                }
            }
            List<NewsInfo> list = newInfoService.getList();
            for (int i = 0; i < list.size(); i++) {
                row = hssfSheet.createRow(i + 1);
                NewsInfo newsInfo = list.get(i);

                row.createCell(0).setCellValue(newsInfo.getId());
                row.createCell(1).setCellValue(newsInfo.getName());
                Long id = newsInfo.getUser().getId();
                if(id != null){
                    User user = userService.getUser(id);
                    if(user != null)
                        row.createCell(2).setCellValue(user.getUsername());
                    else
                        row.createCell(2).setCellValue("admin");
                }else
                    row.createCell(2).setCellValue("admin");
                row.createCell(3).setCellValue(newsInfo.getDetail());
                row.createCell(4).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(newsInfo.getCreated()));
                row.createCell(5).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(newsInfo.getUpdated()));
                row.createCell(6).setCellValue(newsInfo.getType());
            }

            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("导出信息失败！");

        }
    }

    public void exportUser(String[] titles, ServletOutputStream out) throws Exception{
        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");
            //设置对应的索引列的列宽 从0开始算
            hssfSheet.setColumnWidth(1, 2500);
            hssfSheet.setColumnWidth(2, 9000);
            hssfSheet.setColumnWidth(3, 3500);
            hssfSheet.setColumnWidth(4, 4500);
            hssfSheet.setColumnWidth(5, 3500);
            hssfSheet.setColumnWidth(6, 3500);
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格样式
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
            //居中样式
            hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                //根据title的个数创建单元格
                hssfCell = row.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//标题居中显示
                hssfSheet.setDefaultColumnStyle(i,hssfCellStyle);//一列居中显示
            }
            //获取所有用户
            List<User> list = userService.getList();
            //创建被删除的用户的样式  createHSSFCellStyle封装方法
            HSSFCellStyle hssfCellDeleteStyle = createHSSFCellStyle(workbook,130);
            //创建被冻结的用户样式
            HSSFCellStyle hssfCellfreezeStyle = createHSSFCellStyle(workbook,180);
            for (int i = 0; i < list.size(); i++) {
                row = hssfSheet.createRow(i+1);
                User user = list.get(i);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getUsername());
                row.createCell(2).setCellValue(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
                row.createCell(3).setCellValue(user.getPhone());
                row.createCell(4).setCellValue(user.getEmail());
                row.createCell(5).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(user.getCreated()));
                row.createCell(6).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(user.getUpdated()));
                String status = user.getIsdelete() == 1 ? "正常": user.getIsdelete() == 2 ? "冻结" : "删除";
                row.createCell(7).setCellValue(status);
                //当判定用户被删除则赋上红色style属性
                if(user.getIsdelete() == 0) {
                    //putRowCell 封装方法
                    putRowCell(row,8,hssfCellDeleteStyle);
                }else if(user.getIsdelete() == 2){
                    putRowCell(row,8,hssfCellfreezeStyle);
                }
            }

            // 第七步，将文件输出到客户端浏览器
            try {
                workbook.write(out);
                out.flush();
                out.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("导出信息失败！");
        }
    }
    public Cell getCell(Row row,int i){
        Cell cell = row.getCell(i);
        cell.setCellType(CellType.STRING);
        return cell;
    }

    public boolean getBool(Row row,int cols){
        Boolean bool = false ;
        for(int i = 0 ; i < cols ; i++){
            if(StringUtils.isBlank(getCell(row,i).getStringCellValue())){
                bool = true;
                break;
            }

        }
        return bool;
    }

    public HSSFCellStyle createHSSFCellStyle(HSSFWorkbook workbook,int color){
        HSSFCellStyle hssfCellStyle = workbook.createCellStyle();
        hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
        hssfCellStyle.setFillPattern(FillPatternType.forInt(1));
        hssfCellStyle.setFillForegroundColor(new HSSFColor(color,50, Color.RED).getIndex());
        return hssfCellStyle;
    }

    public void putRowCell(HSSFRow row,int length,HSSFCellStyle hssfCellStyle){
        for (int i = 0; i < length; i++) {
            row.getCell(i).setCellStyle(hssfCellStyle);
        }
    }
}
