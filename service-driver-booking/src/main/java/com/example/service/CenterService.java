package com.example.service;

import com.example.dao.TestCenterMapper;
import com.example.entity.TestCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class CenterService {
    private static final Logger log = LoggerFactory.getLogger(CenterService.class);

    @Autowired
    private TestCenterMapper centerMapper;


    /** list all center */
    public List<TestCenter> listAll() {
        return centerMapper.selectAll();
    }


}