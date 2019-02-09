/*
*
* DayOsTimesStatMapper.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-10-08
*/
package cn.web.dao;

import cn.web.entity.DayOsTimesStat;
import cn.web.entity.DayOsTimesStatKey;
import cn.web.example.DayOsTimesStatExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface DayOsTimesStatMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(DayOsTimesStatExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(DayOsTimesStatExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(DayOsTimesStatKey key);

    /**
     *
     * @mbg.generated
     */
    int insert(DayOsTimesStat record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DayOsTimesStat record);

    /**
     *
     * @mbg.generated
     */
    List<DayOsTimesStat> selectByExample(DayOsTimesStatExample example);

    /**
     *
     * @mbg.generated
     */
    DayOsTimesStat selectByPrimaryKey(DayOsTimesStatKey key);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DayOsTimesStat record, @Param("example") DayOsTimesStatExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DayOsTimesStat record, @Param("example") DayOsTimesStatExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DayOsTimesStat record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DayOsTimesStat record);
}