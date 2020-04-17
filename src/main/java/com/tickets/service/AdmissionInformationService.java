package com.tickets.service;

import java.util.Date;
import java.util.Map;

public interface AdmissionInformationService {

    /**
     * 获取过去5分钟新增数据量
     * @return
     */
    Map<String,Object> getCountLimitTime(Date date, String vaId);

    /**
     * 获取 票务入场， 工作人员入场， 未实名入场， 实名入场
     * @param vaId
     * @return
     */
    Map<String,Object> getTypeCount(String vaId);
}
