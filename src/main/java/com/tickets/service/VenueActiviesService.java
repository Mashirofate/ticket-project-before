package com.tickets.service;

import com.tickets.dto.Page;
import com.tickets.dto.UserSeachDto;
import com.tickets.dto.VenueActivieAddDto;
import com.tickets.dto.VenueActivieSearchDto;

import java.util.List;
import java.util.Map;

public interface VenueActiviesService {

    boolean save(VenueActivieAddDto venueActivieAddDto);


    Page getByKeys(VenueActivieSearchDto venueActivieSearchDto);


    boolean updateEnable(String vaId, Character vaEnable);

    boolean remove(String vaId);

    /**
     * 获取启用的活动
     *
     * @return
     */
    List<Map<String, Object>> getOpenActivies();


    Map<String,Object> getByVaId(String vaId);
}
