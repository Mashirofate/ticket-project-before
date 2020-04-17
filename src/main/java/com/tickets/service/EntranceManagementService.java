package com.tickets.service;

import com.tickets.dto.EntranceManagementAddDto;
import com.tickets.dto.EntranceManagementSearchDto;
import com.tickets.dto.Page;
import com.tickets.dto.VenueMgDto;

import java.util.List;
import java.util.Map;

public interface EntranceManagementService {

    /**
     * 获取各个入口的人数
     * @return
     */
    List<Map<String,Object>> getEntrancePeopleCount(String vaId);


    Page getByKeys(EntranceManagementSearchDto entranceManagementSearchDto);


    boolean save(EntranceManagementAddDto entranceManagementAddDto);


    boolean delById(String emId);

    boolean updateEnable(String emId, Character emEnable);
}
