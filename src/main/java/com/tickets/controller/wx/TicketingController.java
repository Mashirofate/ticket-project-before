package com.tickets.controller.wx;

import com.tickets.annotations.Authentication;
import com.tickets.dto.ResponseResult;
import com.tickets.dto.TicketingSaveDto;
import com.tickets.service.TicketingStaffService;
import com.tickets.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Api(tags = "票务接口")
@RestController
@RequestMapping("/wechat/ts")
public class TicketingController {

    @Autowired
    private TicketingStaffService ticketingStaffServicef;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Authentication(isLogin = true, isRequiredUserInfo = true)
    @PostMapping("/add")
    public ResponseResult add(@RequestBody TicketingSaveDto ticketingSaveDto) {
        ticketingStaffServicef.save(ticketingSaveDto);
        return ResponseResult.SUCCESS();
    }

    @Authentication(isLogin = true, isRequiredUserInfo = true)
    @GetMapping("/byWid")
    public ResponseResult getById() {
        String id = (String) httpServletRequest.getAttribute("id");
        List<Map<String, Object>> byWid = null;
        if (!StringUtils.isEmpty(id)) {
            byWid = ticketingStaffServicef.getById(id);
        }
        return ResponseResult.SUCCESS(byWid);
    }
}
