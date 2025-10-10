package com.example.controller;

import com.example.entity.SlotView;
import com.example.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/slot")
public class SlotController {

    @Autowired
    private SlotService slotService;


    @GetMapping("/getSlotList")
    public List<SlotView> slots() {
        return slotService.listAll();
    }
}
