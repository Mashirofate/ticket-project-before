package com.tickets.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VenueManagementAddDto {
    @JsonProperty(value = "vmName")
    private String vmName;
    @JsonProperty(value = "vmEnable")
    private Character vmEnable;
    @JsonProperty(value = "vmNote")
    private String vmNote;
}
