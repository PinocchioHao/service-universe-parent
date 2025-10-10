package com.example.controller;

import com.example.entity.ApiResponse;
import com.example.entity.TestCenter;
import com.example.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/center")
public class CenterController {


    @Autowired
    private CenterService centerService;

    /** list all center */
    @GetMapping("/all")
    public ApiResponse<List<TestCenter>> all() {
        return ApiResponse.success("ok", centerService.listAll());
    }
}
