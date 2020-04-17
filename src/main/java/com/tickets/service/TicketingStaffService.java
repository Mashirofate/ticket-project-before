package com.tickets.service;

import com.tickets.dto.Page;
import com.tickets.dto.TicketingSaveDto;
import com.tickets.dto.TicketingStaffSearchDto;
import com.tickets.dto.VenueMgDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface TicketingStaffService {

    Page getByKeys(TicketingStaffSearchDto ticketingStaffSearchDto);

    /**
     * 下载票务导入的模板
     *
     * @param response
     */
    void exportExcel(HttpServletResponse response);


    void saveBath(List<Map<String, Object>> list,String aId);

    boolean save(TicketingSaveDto ticketingSaveDto);

    List<Map<String,Object>> getById(String wId);
}
