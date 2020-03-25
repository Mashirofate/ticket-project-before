package com.tickets.service.impl;

import com.tickets.dto.Page;
import com.tickets.dto.VenueMgDto;
import com.tickets.mapper.VenueManagementMapper;
import com.tickets.service.VenueManagementService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class VenueManagementServiceImpl implements VenueManagementService {

    @Resource
    private VenueManagementMapper venueManagementMapper;

    @Override
    public Page getByKeys(VenueMgDto venueMgDto) {
        Page<Map<String, Object>> page = new Page<>();
        BeanUtils.copyProperties(venueMgDto, page);
        int count = venueManagementMapper.selectCountByKeys(venueMgDto);
        page.setTotal(count);
        if (count != 0) {
            page.setRecords(venueManagementMapper.selectByKeys(venueMgDto));
        }
        return page;
    }

    @Override
    public boolean update(String vmId, Character vmEnable) {
        return venueManagementMapper.updateEnable(vmId,vmEnable) == 1;
    }
}
