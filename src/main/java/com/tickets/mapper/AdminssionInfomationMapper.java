package com.tickets.mapper;

import java.util.Date;
import java.util.Map;

public interface AdminssionInfomationMapper {

    /**
     * 获取过去5分钟入场人数
     * @return
     */
    Map<String,Object> selectCountLimitTime(Date date, String vaId);

    Map<String,Object> selectTypeCount(String vaId);
}
