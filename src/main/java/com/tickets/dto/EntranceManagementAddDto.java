package com.tickets.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EntranceManagementAddDto {
    @JsonProperty(value = "emName")
    private String emName;
    @JsonProperty(value = "emEnable")
    private Integer emEnable;
    @JsonProperty(value = "emNote")
    private String emNote;
    @JsonProperty(value = "emVmId")
    private String emVmId;
}
