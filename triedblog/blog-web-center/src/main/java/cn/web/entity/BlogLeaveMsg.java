/*
*
* BlogLeaveMsg.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-08-02
*/
package cn.web.entity;

import java.util.Date;

public class BlogLeaveMsg {
    /**
     * 留言ID
     */
    private Long leaveId;

    /**
     * 0:默认为一级留言
     */
    private Long parentId;

    /**
     * 回复用户id
     */
    private String fromId;

    /**
     * 留言者昵称
     */
    private String fromName;

    /**
     * 留言者头像
     */
    private String fromHeadImg;

    /**
     * 目标用户id(为空表示一级评论)
     */
    private String toId;

    /**
     * 目标用户昵称
     */
    private String toName;

    /**
     * 点赞数
     */
    private Long likeNum;

    /**
     * ip地址
     */
    private String areaIp;

    /**
     * 类型
     */
    private String area;

    /**
     * 留言时间
     */
    private Date createTime = new Date();

    /**
     * 评论内容
     */
    private String content;

    /**
     * 留言ID
     * @return leave_id 留言ID
     */
    public Long getLeaveId() {
        return leaveId;
    }

    /**
     * 留言ID
     * @param leaveId 留言ID
     */
    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    /**
     * 0:默认为一级留言
     * @return parent_id 0:默认为一级留言
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 0:默认为一级留言
     * @param parentId 0:默认为一级留言
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 回复用户id
     * @return from_id 回复用户id
     */
    public String getFromId() {
        return fromId;
    }

    /**
     * 回复用户id
     * @param fromId 回复用户id
     */
    public void setFromId(String fromId) {
        this.fromId = fromId == null ? null : fromId.trim();
    }

    /**
     * 留言者昵称
     * @return from_name 留言者昵称
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * 留言者昵称
     * @param fromName 留言者昵称
     */
    public void setFromName(String fromName) {
        this.fromName = fromName == null ? null : fromName.trim();
    }

    /**
     * 留言者头像
     * @return from_head_img 留言者头像
     */
    public String getFromHeadImg() {
        return fromHeadImg;
    }

    /**
     * 留言者头像
     * @param fromHeadImg 留言者头像
     */
    public void setFromHeadImg(String fromHeadImg) {
        this.fromHeadImg = fromHeadImg == null ? null : fromHeadImg.trim();
    }

    /**
     * 目标用户id(为空表示一级评论)
     * @return to_id 目标用户id(为空表示一级评论)
     */
    public String getToId() {
        return toId;
    }

    /**
     * 目标用户id(为空表示一级评论)
     * @param toId 目标用户id(为空表示一级评论)
     */
    public void setToId(String toId) {
        this.toId = toId == null ? null : toId.trim();
    }

    /**
     * 目标用户昵称
     * @return to_name 目标用户昵称
     */
    public String getToName() {
        return toName;
    }

    /**
     * 目标用户昵称
     * @param toName 目标用户昵称
     */
    public void setToName(String toName) {
        this.toName = toName == null ? null : toName.trim();
    }

    /**
     * 点赞数
     * @return like_num 点赞数
     */
    public Long getLikeNum() {
        return likeNum;
    }

    /**
     * 点赞数
     * @param likeNum 点赞数
     */
    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    /**
     * ip地址
     * @return area_ip ip地址
     */
    public String getAreaIp() {
        return areaIp;
    }

    /**
     * ip地址
     * @param areaIp ip地址
     */
    public void setAreaIp(String areaIp) {
        this.areaIp = areaIp == null ? null : areaIp.trim();
    }

    /**
     * 类型
     * @return area 类型
     */
    public String getArea() {
        return area;
    }

    /**
     * 类型
     * @param area 类型
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * 留言时间
     * @return create_time 留言时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 留言时间
     * @param createTime 留言时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 评论内容
     * @return content 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 评论内容
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}