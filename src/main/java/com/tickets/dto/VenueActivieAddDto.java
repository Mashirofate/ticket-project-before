package com.tickets.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VenueActivieAddDto {

    @JsonProperty(value = "vaName")
    private String vaName;
    @JsonProperty(value = "vaEnable")
    private Integer vaEnable;
    @JsonProperty(value = "vaNote")
    private String vaNote;
    @JsonProperty(value = "vaTicketnumber")
    private Integer vaTicketnumber;
    @JsonProperty(value = "vaEmployeenumber")
    private Integer vaEmployeenumber;
    @JsonProperty(value = "almage")
    private String almage;

}
