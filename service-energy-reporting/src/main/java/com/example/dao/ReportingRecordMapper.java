package com.example.dao;

import com.example.entity.ReportingRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportingRecordMapper {

    void insert(ReportingRecord record);

    ReportingRecord findByReportId(String reportId);

    void updateStatus(String reportId, String status);

    List<ReportingRecord> findAll();
}
