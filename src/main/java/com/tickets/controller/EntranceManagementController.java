package com.tickets.controller;

import com.tickets.annotations.Authentication;
import com.tickets.dto.*;
import com.tickets.service.EntranceManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/em")
public class EntranceManagementController {

    @Autowired
    private EntranceManagementService entranceManagementService;

    @Authentication(isLogin = true,isRequiredUserInfo = true)
    @ApiOperation(value = "创建场馆", notes = "")
    @PostMapping("/add")
    public ResponseResult addVenue(@RequestBody EntranceManagementAddDto entranceManagementAddDto) {
        System.out.println(entranceManagementAddDto);
        entranceManagementService.save(entranceManagementAddDto);
        return ResponseResult.SUCCESS();
    }

    @Authentication(isLogin = true,isRequiredUserInfo = true)
    @ApiOperation(value = "用户条件搜索")
    @GetMapping("/search")
    public ResponseResult search(EntranceManagementSearchDto entranceManagementSearchDto) {
        return ResponseResult.SUCCESS(entranceManagementService.getByKeys(entranceManagementSearchDto));
    }
    @Authentication(isLogin = true,isRequiredUserInfo = true)
    @ApiOperation(value = "更新场馆状态信息", notes = "0不启用，1启用")
    @PutMapping("/{emId}/{enable}")
    public ResponseResult updateEnable(@PathVariable String emId, @PathVariable Character enable) {
        entranceManagementService.updateEnable(emId, enable);
        return ResponseResult.SUCCESS();
    }
    @Authentication(isLogin = true,isRequiredUserInfo = true)
    @ApiOperation(value = "删除场馆")
    @DeleteMapping("/{id}")
    public ResponseResult delById(@PathVariable String id){
        entranceManagementService.delById(id);
        return ResponseResult.SUCCESS();
    }


}
