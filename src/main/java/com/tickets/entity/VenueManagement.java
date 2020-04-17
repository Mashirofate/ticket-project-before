package com.tickets.entity;

import lombok.Data;

import java.util.Date;

@Data
public class VenueManagement {

    private String vmId;
    private String vmName;
    private Character vmEnable;
    private String vmNote;
    private Date vmCreationtime;

}
