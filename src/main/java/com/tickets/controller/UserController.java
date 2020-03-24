package com.tickets.controller;

import com.tickets.dto.LoginDto;
import com.tickets.dto.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/u")
public class UserController {

    @ApiOperation(value = "创建一个用户")
    @PostMapping("/")
    public ResponseResult add() {
        return new ResponseResult();
    }

    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginDto loginDto) {

       String token = "132";
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        return ResponseResult.SUCCESS(map);
    }
    @GetMapping("/info")
    public ResponseResult info(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","刘佳伟");
        map.put("role", new String[]{"admin"});
        return ResponseResult.SUCCESS(map);
    }
}
