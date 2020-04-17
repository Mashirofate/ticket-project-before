package com.tickets.mapper;

import com.tickets.dto.VenueActivieSearchDto;
import com.tickets.entity.VenueActivies;

import java.awt.*;
import java.util.List;
import java.util.Map;

public interface VenueActiviesMapper {

    /**
     * 创建一条记录
     * @param venueActivies
     * @return
     */
    int insert(VenueActivies venueActivies);


    List<Map<String, Object>> selectByKeys(VenueActivieSearchDto venueActivieSearchDto);

    int selectCountByKeys(VenueActivieSearchDto venueActivieSearchDto);

    int updateEnable(String vaId, Character vaEnable);

    List<Map<String,Object>> selectByEnable(Character enable);

    Map<String,Object> selectByVaId(String vaId);

}
