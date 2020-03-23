package com.tickets.controller;

import com.tickets.config.WebSocketServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
public class TestController {


    @RequestMapping("/push/{toUserId}/{message}")
    public String pushToWeb(@PathVariable String message, @PathVariable String toUserId) throws IOException {

        List<Map<Object,Integer>> map = new ArrayList<>();
        Map<Object, Integer> map1;
        for (int i = 0; i < 100; i++){
            map1 = new HashMap<>();
            map1.put(new Date(),i);
            map.add(map1);
        }

        WebSocketServer.sendInfo(message, map.toString());

        WebSocketServer.sendInfo(map.toString(), toUserId);
        return "SUCCESS";
    }

}
