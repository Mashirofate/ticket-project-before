package com.tickets.service;

import com.tickets.dto.Page;
import com.tickets.dto.VenueMgDto;

public interface VenueManagementService {

    /**
     * 条件分页
     *
     * @param venueMgDto
     * @return
     */
    Page getByKeys(VenueMgDto venueMgDto);

    /**
     * 更新场馆信息
     *
     * @param vmId
     * @param vmEnable
     * @return
     */
    boolean update(String vmId, Character vmEnable);
}
