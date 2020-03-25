package com.tickets.controller;

import com.tickets.dto.LoginDto;
import com.tickets.dto.ResponseResult;
import com.tickets.dto.UserSeachDto;
import com.tickets.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/u")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "创建一个用户")
    @PostMapping("/")
    public ResponseResult add() {
        return new ResponseResult();
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginDto loginDto) {
        String token = "132";
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        System.out.println("登录啦");
        return ResponseResult.SUCCESS(map);
    }

    @ApiOperation(value = "获取用户信息", notes = "前提是以通过 /login接口获得了token信息，且请求头携带了token信息。")
    @GetMapping("/info")
    public ResponseResult info() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "刘佳伟");
        map.put("role", new String[]{"admin"});
        return ResponseResult.SUCCESS(map);
    }

    @ApiOperation(value = "用户条件搜索")
    @GetMapping("/search")
    public ResponseResult search(UserSeachDto seachDto) {
        return ResponseResult.SUCCESS(userService.getByKeys(seachDto));
    }

    @ApiOperation(value = "删除用户", notes = "根据uId删除")
    @DeleteMapping("/{uId}")
    public ResponseResult delById(@PathVariable String uId) {
        userService.remove(uId);
        return ResponseResult.SUCCESS();
    }

    @ApiOperation(value = "下载批量导入用户模板", notes = "")
    @GetMapping("/model/download")
    public void modelDownload(HttpServletResponse response) throws IOException {
        //表头数据
        String[] header = {"ID", "姓名", "性别", "年龄", "地址", "分数"};
        //数据内容
        String[] student1 = {"1", "小红", "女", "23", "成都青羊区", "96"};
        String[] student2 = {"2", "小强", "男", "26", "成都金牛区", "91"};
        String[] student3 = {"3", "小明", "男", "28", "成都武侯区", "90"};
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("学生表");
        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);
        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);
        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
        }

        //模拟遍历结果集，把内容加入表格
        //模拟遍历第一个学生
        HSSFRow row1 = sheet.createRow(1);
        for (int i = 0; i < student1.length; i++) {
            HSSFCell cell = row1.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(student1[i]);
            cell.setCellValue(text);
        }

        //模拟遍历第二个学生
        HSSFRow row2 = sheet.createRow(2);
        for (int i = 0; i < student2.length; i++) {
            HSSFCell cell = row2.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(student2[i]);
            cell.setCellValue(text);
        }

        //模拟遍历第三个学生
        HSSFRow row3 = sheet.createRow(3);
        for (int i = 0; i < student3.length; i++) {
            HSSFCell cell = row3.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(student3[i]);
            cell.setCellValue(text);
        }
        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");
        //这后面可以设置导出Excel的名称，此例中名为student.xls
        response.setHeader("Content-disposition", "attachment;filename=student.xls");
        //刷新缓冲
        response.flushBuffer();
        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }


    @ApiOperation(value = "批量添加用户", notes = "需要下载固定的模板")
    @PostMapping("/addBatch")
    public ResponseResult addBatch(@RequestParam("file") MultipartFile file){
        return ResponseResult.SUCCESS();
    }

    @ApiOperation(value = "批量删除用户", notes = "根据uId删除")
    @DeleteMapping("/batch")
    public ResponseResult delByIds(@RequestParam String ids) {
        String[] uIds = ids.split(",");
        userService.removeBatch(uIds);
        return ResponseResult.SUCCESS();
    }

}
