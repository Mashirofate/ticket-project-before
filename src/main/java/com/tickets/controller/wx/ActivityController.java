package com.tickets.controller.wx;

import com.tickets.annotations.Authentication;
import com.tickets.dto.ResponseResult;
import com.tickets.dto.TicketingSaveDto;
import com.tickets.service.VenueActiviesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "微信用户权限接口")
@RestController
@RequestMapping("/wechat/activity")
public class ActivityController {

    @Autowired
    private VenueActiviesService venueActiviesService;

    @Authentication(required = false)
    @ApiOperation(value = "启用的活动")
    @GetMapping("/open")
    public ResponseResult openActivies() {
        return ResponseResult.SUCCESS(venueActiviesService.getOpenActivies());
    }

    @Authentication(required = false)
    @ApiOperation(value = "根据id获取活动信息")
    @GetMapping("/")
    public ResponseResult getByVaId(@RequestParam String aId) {
        return ResponseResult.SUCCESS(venueActiviesService.getByVaId(aId));
    }


}
