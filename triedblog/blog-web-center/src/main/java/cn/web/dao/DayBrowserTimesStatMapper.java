/*
*
* DayBrowserTimesStatMapper.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-10-08
*/
package cn.web.dao;

import cn.web.entity.DayBrowserTimesStat;
import cn.web.entity.DayBrowserTimesStatKey;
import cn.web.example.DayBrowserTimesStatExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface DayBrowserTimesStatMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(DayBrowserTimesStatExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(DayBrowserTimesStatExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(DayBrowserTimesStatKey key);

    /**
     *
     * @mbg.generated
     */
    int insert(DayBrowserTimesStat record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DayBrowserTimesStat record);

    /**
     *
     * @mbg.generated
     */
    List<DayBrowserTimesStat> selectByExample(DayBrowserTimesStatExample example);

    /**
     *
     * @mbg.generated
     */
    DayBrowserTimesStat selectByPrimaryKey(DayBrowserTimesStatKey key);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") DayBrowserTimesStat record, @Param("example") DayBrowserTimesStatExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") DayBrowserTimesStat record, @Param("example") DayBrowserTimesStatExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DayBrowserTimesStat record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DayBrowserTimesStat record);
}