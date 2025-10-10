package com.example.service;

import com.example.dao.SlotMapper;
import com.example.entity.ApiResponse;
import com.example.entity.SlotView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SlotService {

    @Autowired
    private SlotMapper slotMapper;

    public List<SlotView> listAll() {
        return slotMapper.listAll();
    }
}