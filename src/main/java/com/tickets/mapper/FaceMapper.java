package com.tickets.mapper;

import java.util.List;

public interface FaceMapper {
    /**
     * 获取最新的10个人脸图像
     */
    List<String> selectImageByActivityId(String aId);
}
