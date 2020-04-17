package com.tickets.tasks;

import com.alibaba.fastjson.JSON;
import com.tickets.mapper.FaceMapper;
import com.tickets.service.AdmissionInformationService;
import com.tickets.service.EntranceManagementService;
import com.tickets.service.FaceService;
import com.tickets.service.TicketingStaffService;
import com.tickets.socket.RealTimeEntranceServer;
import com.tickets.socket.RealTimeOtherServer;
import com.tickets.socket.RealTimePeopleServer;
import io.jsonwebtoken.Header;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class WebSoketTask {

    @Autowired
    private AdmissionInformationService admissionInformationService;
    @Autowired
    private EntranceManagementService entranceManagementService;
    @Autowired
    private TicketingStaffService ticketingStaffService;
    @Autowired
    private FaceService faceService;

    /**
     * 推送实时人数信息
     */
    @Scheduled(fixedRate = 60 * 1000)
    public void realTimePeopleData() throws IOException {
        List<String> vaIds = RealTimePeopleServer.vaIds;
        for (String vaId : vaIds) {
            Map<String, Object> countLimitTime = admissionInformationService.getCountLimitTime(new Date(),vaId);
            RealTimePeopleServer.sendMessage(vaId, JSON.toJSONString(countLimitTime));
        }
    }

    /**
     * 推送实时入口人数信息
     */
    @Scheduled(fixedRate = 60 * 1000)
    public void realTimeEntrancePeopleData() throws IOException {
        List<String> vaIds = RealTimeEntranceServer.vaIds;
        for (String vaId : vaIds) {
            List<Map<String, Object>> entrancePeopleCount = entranceManagementService.getEntrancePeopleCount(vaId);
            RealTimeEntranceServer.sendMessage(vaId, JSON.toJSONString(entrancePeopleCount));
        }


    }

    /**
     * 其它信息推送
     *
     * @throws IOException
     */
    @Scheduled(fixedRate = 2 * 1000)
    public void RealTimeOtherData() throws IOException {
        List<String> vaIds = RealTimeOtherServer.vaIds;
        for (String vaId : vaIds) {
            Map<String, Object> rest = new HashMap<>();
            Map<String, Object> typeCount = admissionInformationService.getTypeCount(vaId);
            rest.put("typeCount",typeCount);
            rest.put("headsImg",faceService.getImageByActivityId(vaId));
            RealTimeOtherServer.sendMessage(vaId, JSON.toJSONString(rest));
        }
    }


    @Accessors(chain = true)
    @Data
    class Rest {
        private Object date;
        private int count;
    }

}
