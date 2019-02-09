/*
*
* BlogLeaveMsgMapper.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-08-02
*/
package cn.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.web.entity.BlogLeaveMsg;
import cn.web.example.BlogLeaveMsgExample;

@Mapper
public interface BlogLeaveMsgMapper {
    /**
     *
     * @mbg.generated
     */
    long countByExample(BlogLeaveMsgExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByExample(BlogLeaveMsgExample example);

    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long leaveId);

    /**
     *
     * @mbg.generated
     */
    int insert(BlogLeaveMsg record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(BlogLeaveMsg record);

    /**
     *
     * @mbg.generated
     */
    List<BlogLeaveMsg> selectByExampleWithBLOBs(BlogLeaveMsgExample example);

    /**
     *
     * @mbg.generated
     */
    List<BlogLeaveMsg> selectByExample(BlogLeaveMsgExample example);

    /**
     *
     * @mbg.generated
     */
    BlogLeaveMsg selectByPrimaryKey(Long leaveId);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") BlogLeaveMsg record, @Param("example") BlogLeaveMsgExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") BlogLeaveMsg record, @Param("example") BlogLeaveMsgExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") BlogLeaveMsg record, @Param("example") BlogLeaveMsgExample example);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(BlogLeaveMsg record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(BlogLeaveMsg record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(BlogLeaveMsg record);
}