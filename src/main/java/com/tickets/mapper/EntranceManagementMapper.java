package com.tickets.mapper;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.tickets.dto.EntranceManagementSearchDto;
import com.tickets.dto.UserSeachDto;
import com.tickets.entity.EntranceManagement;

import java.util.List;
import java.util.Map;

public interface EntranceManagementMapper {

    int insert(EntranceManagement entranceManagement);

    List<Map<String,Object>> selectEntrancePeopleCount(String vaId);

    List<Map<String, Object>> selectByKeys(EntranceManagementSearchDto entranceManagementSearchDto);

    int selectCountByKeys(EntranceManagementSearchDto entranceManagementSearchDto);


    int updateEnable(String emId, Character emEnable);


}
