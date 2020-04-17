package com.tickets.utils;

import com.sun.xml.internal.ws.util.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
    /**
     * 获取并解析excel文件，返回一个二维集合
     *
     * @param file 上传的文件
     * @return 二维集合（第一重集合为行，第二重集合为列，每一行包含该行的列集合，列集合包含该行的全部单元格的值）
     */
    public static List<Map<String, Object>> analysis(MultipartFile file) {
        List<Map<String, Object>> list = new ArrayList<>();
        //获取文件名称
        String fileName = file.getOriginalFilename();
        try {
            //获取输入流
            InputStream in = file.getInputStream();
            //判断excel版本
            Workbook workbook = null;
            if (judegExcelEdition(fileName)) {
                workbook = new XSSFWorkbook(in);
            } else {
                workbook = new HSSFWorkbook(in);
            }

//            获取头部
            List<String> header = new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);
            Row sheetRow1 = sheet.getRow(0);
            for (int j = 0; j < sheetRow1.getPhysicalNumberOfCells(); j++) {
                //将每一个单元格的值装入列集合
                header.add(sheetRow1.getCell(j).getStringCellValue());
            }

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                //循环获取工作表的每一行
                sheetRow1 = sheet.getRow(i);
                Map<String, Object> cel = new HashMap<>();
                for (int j = 0; j < sheetRow1.getPhysicalNumberOfCells(); j++) {
                    sheet.getRow(i).getCell(j).setCellType(CellType.STRING);
                    cel.put(header.get(j), sheetRow1.getCell(j).getStringCellValue());
                }
                list.add(cel);

                //关闭资源
                workbook.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("===================未找到文件======================");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("===================上传失败======================");
        }

        return list;
    }

    /**
     * 判断上传的excel文件版本（xls为2003，xlsx为2017）
     *
     * @param fileName 文件路径
     * @return excel2007及以上版本返回true，excel2007以下版本返回false
     */
    private static boolean judegExcelEdition(String fileName) {
        if (fileName.matches("^.+\\.(?i)(xls)$")) {
            return false;
        } else {
            return true;
        }

    }

}
