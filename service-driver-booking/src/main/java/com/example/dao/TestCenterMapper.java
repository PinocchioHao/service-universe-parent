package com.example.dao;

import com.example.entity.TestCenter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TestCenterMapper {

    List<TestCenter> selectAll();

    /** Try to occupy: Idle (0) → Occupied (1), return 1 if successful, return 0 if failed */
    int occupyIfFree(@Param("id") Long centerId);

    /** Release: occupied (1) → free (0) */
    int release(@Param("id") Long centerId);

}
