/*
*
* DayAreaTimesStatMapper.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-10-08
*/
package cn.web.dao;

import cn.web.entity.DayAreaTimesStat;
import cn.web.entity.DayAreaTimesStatKey;
import cn.web.example.DayAreaTimesStatExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface DayAreaTimesStatMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(DayAreaTimesStatExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(DayAreaTimesStatExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(DayAreaTimesStatKey key);

    /**
     *
     * @mbg.generated
     */
    int insert(DayAreaTimesStat record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DayAreaTimesStat record);

    /**
     *
     * @mbg.generated
     */
    List<DayAreaTimesStat> selectByExample(DayAreaTimesStatExample example);

    /**
     *
     * @mbg.generated
     */
    DayAreaTimesStat selectByPrimaryKey(DayAreaTimesStatKey key);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DayAreaTimesStat record, @Param("example") DayAreaTimesStatExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DayAreaTimesStat record, @Param("example") DayAreaTimesStatExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DayAreaTimesStat record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DayAreaTimesStat record);
}