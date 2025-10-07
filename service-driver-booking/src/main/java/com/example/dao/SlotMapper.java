package com.example.dao;

import com.example.entity.SlotView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SlotMapper {
    List<SlotView> listAll();

    int holdSlot(@Param("centreId") String centreId, @Param("slotTime") String slotTime);

    int bookSlot(@Param("centreId") String centreId, @Param("slotTime") String slotTime);

}
