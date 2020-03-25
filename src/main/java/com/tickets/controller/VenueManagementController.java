package com.tickets.controller;

import com.tickets.dto.ResponseResult;
import com.tickets.dto.VenueMgDto;
import com.tickets.service.VenueManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "场馆管理")
@RestController
@RequestMapping("/vm")
public class VenueManagementController {

    @Autowired
    private VenueManagementService venueManagementService;

    @ApiOperation(value = "场馆条件搜索", notes = "")
    @GetMapping("/search")
    public ResponseResult search(VenueMgDto venueMgDto) {
        System.out.println(venueMgDto);
        return ResponseResult.SUCCESS(venueManagementService.getByKeys(venueMgDto));
    }

    @ApiOperation(value = "更新场馆状态信息", notes = "0不启用，1启用")
    @PutMapping("/{vmId}/{enable}")
    public ResponseResult updateEnable(@PathVariable String vmId, @PathVariable Character enable) {
        venueManagementService.update(vmId, enable);
        return ResponseResult.SUCCESS();
    }

}
