package com.tickets.service.impl;

import com.tickets.mapper.AdminssionInfomationMapper;
import com.tickets.service.AdmissionInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@Service
public class AdmissionInformationImpl implements AdmissionInformationService {

    @Resource
    private AdminssionInfomationMapper adminssionInfomationMapper;

    @Override
    public Map<String,Object> getCountLimitTime(Date date, String vaId) {
        return adminssionInfomationMapper.selectCountLimitTime(date,vaId);
    }

    @Override
    public Map<String, Object> getTypeCount(String vaId) {
        return adminssionInfomationMapper.selectTypeCount(vaId);
    }
}
