package com.tickets.dto;

import lombok.Data;

@Data
public class Page {
    private Long current;
    private Long size;
    private Long total;
}
