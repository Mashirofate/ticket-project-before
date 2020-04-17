package com.tickets.mapper;

import java.util.List;
import java.util.Map;

public interface EquipmentInformationMapper {
    List<Map<String, Object>> selectEmByVaId(String vaId);
}
