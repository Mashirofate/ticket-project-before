package com.tickets.mapper;

import com.tickets.dto.TicketingStaffSearchDto;
import com.tickets.dto.VenueMgDto;
import com.tickets.entity.TicketingStaff;

import java.util.List;
import java.util.Map;

public interface TicketingStaffMapper {


    int insert(TicketingStaff ticketingStaff);

    List<Map<String, Object>> selectByKeys(TicketingStaffSearchDto ticketingStaffSearchDto);

    int selectCountByKeys(TicketingStaffSearchDto ticketingStaffSearchDto);

    /**
     * 获取一些统计数据
     * @param vaId
     * @return
     */
    int selectDataByVaId(String vaId);

    List<Map<String,Object>> selectById(String id);
}
