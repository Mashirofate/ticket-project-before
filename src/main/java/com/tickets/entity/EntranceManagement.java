package com.tickets.entity;

import lombok.Data;

import java.util.Date;

@Data
public class EntranceManagement {
    private String emId;
    private String emName;
    private Integer emEnable;
    private String emNote;
    private String emVmId;
    private Date emCreationTime;
    private String emUId;
}
