package com.tickets.controller;

import com.tickets.annotations.Authentication;
import com.tickets.dto.ResponseResult;
import com.tickets.dto.TicketingStaffSearchDto;
import com.tickets.dto.VenueMgDto;
import com.tickets.service.TicketingStaffService;
import com.tickets.utils.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(tags = "活动接口")
@RestController
@RequestMapping("/ts")
public class TicketingStaffController {

    @Autowired
    private TicketingStaffService ticketingStaffService;

    @Authentication(isLogin = true,isRequiredUserInfo = true)
    @ApiOperation(value = "场馆条件搜索", notes = "")
    @GetMapping("/search")
    public ResponseResult search(TicketingStaffSearchDto ticketingStaffSearchDto) {
        return ResponseResult.SUCCESS(ticketingStaffService.getByKeys(ticketingStaffSearchDto));
    }
    @Authentication(required = false)
    @ApiOperation(value = "下载批量导入票务信息模板", notes = "")
    @GetMapping("/model/download")
    public void modelDownload(HttpServletResponse response) throws IOException {
        ticketingStaffService.exportExcel(response);
    }
    @Authentication(required = false)
    @ApiOperation(value = "批量添加用户", notes = "需要下载固定的模板")
    @PostMapping("/addBatch")
    public ResponseResult addBatch(@RequestParam("file") MultipartFile file, @RequestParam String aId) {
        List<Map<String, Object>> analysis = ExcelUtils.analysis(file);
        ticketingStaffService.saveBath(analysis, aId);
        return ResponseResult.SUCCESS();
    }


}
