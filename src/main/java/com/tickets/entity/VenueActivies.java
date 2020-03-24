package com.tickets.entity;

import lombok.Data;

import java.util.Date;

@Data
public class VenueActivies {
    private String vaId;
    private String vaName;
    private Integer vaEnable;
    private String vaNote;
    private Date vaCreationtime;
    private String vaUId;
}
