package com.tickets.controller;

import com.tickets.annotations.Authentication;
import com.tickets.dto.*;
import com.tickets.service.VenueActiviesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "场馆活动接口")
@RestController
@RequestMapping("/va")
public class VenueActiviesController {

    @Autowired
    private VenueActiviesService venueActiviesService;

    @Authentication(isLogin = true, isRequiredUserInfo = true)
    @ApiOperation(value = "创建一个用户")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody VenueActivieAddDto venueActivieAddDto) {
        venueActiviesService.save(venueActivieAddDto);
        return ResponseResult.SUCCESS();
    }

    @Authentication(isLogin = true, isRequiredUserInfo = true)
    @ApiOperation(value = "场馆活动条件搜索")
    @GetMapping("/search")
    public ResponseResult search(VenueActivieSearchDto venueActivieSearchDto) {
        return ResponseResult.SUCCESS(venueActiviesService.getByKeys(venueActivieSearchDto));
    }

    @Authentication(isLogin = true, isRequiredUserInfo = true)
    @ApiOperation(value = "启用的活动")
    @GetMapping("/open")
    public ResponseResult openActivies() {
        return ResponseResult.SUCCESS(venueActiviesService.getOpenActivies());
    }

    @Authentication(isLogin = true, isRequiredUserInfo = true)
    @ApiOperation(value = "删除活动", notes = "根据vaId删除")
    @DeleteMapping("/{vaId}")
    public ResponseResult delById(@PathVariable String vaId) {
        venueActiviesService.remove(vaId);
        return ResponseResult.SUCCESS();
    }

    @Authentication(isLogin = true, isRequiredUserInfo = true)
    @ApiOperation(value = "更新活动的状态", notes = "")
    @PutMapping("/{vaId}/{vaEnable}")
    public ResponseResult updateEnable(@PathVariable String vaId, @PathVariable Character vaEnable) {
        venueActiviesService.updateEnable(vaId, vaEnable);
        return ResponseResult.SUCCESS();
    }

    @Authentication(isLogin = true, isRequiredUserInfo = true)
    @ApiOperation(value = "根据id获取活动信息")
    @GetMapping("/{id}")
    public ResponseResult getByVaId(@PathVariable String id) {
        return ResponseResult.SUCCESS(venueActiviesService.getByVaId(id));
    }


}
