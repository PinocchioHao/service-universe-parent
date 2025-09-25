package com.example.dao;

import com.example.entity.ParkingRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ParkingRecordMapper {

    int insert(ParkingRecord record);

    int update(ParkingRecord record);

    ParkingRecord selectOngoingByLicense(@Param("licensePlate") String licensePlate);

    List<ParkingRecord> selectHistoryByLicense(@Param("licensePlate") String licensePlate);
}
