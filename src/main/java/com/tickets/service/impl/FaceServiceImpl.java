package com.tickets.service.impl;

import com.tickets.mapper.FaceMapper;
import com.tickets.service.FaceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FaceServiceImpl  implements FaceService {

    @Resource
    private FaceMapper faceMapper;

    @Override
    public List<String> getImageByActivityId(String aId) {
        return faceMapper.selectImageByActivityId(aId);
    }
}
