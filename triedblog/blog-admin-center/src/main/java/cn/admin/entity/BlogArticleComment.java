/*
*
* BlogArticleComment.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-09-17
*/
package cn.admin.entity;

import java.util.Date;

public class BlogArticleComment {
    /**
     * 评论ID
     */
    private Long commentId;

    /**
     * 文章id
     */
    private String articleId;

    /**
     * 0:默认为文章评论
     */
    private Long parentId;

    /**
     * 回复用户id
     */
    private String fromId;

    /**
     * 
     */
    private String fromName;

    /**
     * 
     */
    private String fromHeadImg;

    /**
     * 目标用户id(为空表示一级评论)
     */
    private String toId;

    /**
     * 
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
     * 
     */
    private String area;

    /**
     * 评论时间
     */
    private Date createTime;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论ID
     * @return comment_id 评论ID
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * 评论ID
     * @param commentId 评论ID
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    /**
     * 文章id
     * @return article_id 文章id
     */
    public String getArticleId() {
        return articleId;
    }

    /**
     * 文章id
     * @param articleId 文章id
     */
    public void setArticleId(String articleId) {
        this.articleId = articleId == null ? null : articleId.trim();
    }

    /**
     * 0:默认为文章评论
     * @return parent_id 0:默认为文章评论
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 0:默认为文章评论
     * @param parentId 0:默认为文章评论
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
     * 
     * @return from_name 
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * 
     * @param fromName 
     */
    public void setFromName(String fromName) {
        this.fromName = fromName == null ? null : fromName.trim();
    }

    /**
     * 
     * @return from_head_img 
     */
    public String getFromHeadImg() {
        return fromHeadImg;
    }

    /**
     * 
     * @param fromHeadImg 
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
     * 
     * @return to_name 
     */
    public String getToName() {
        return toName;
    }

    /**
     * 
     * @param toName 
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
     * 
     * @return area 
     */
    public String getArea() {
        return area;
    }

    /**
     * 
     * @param area 
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * 评论时间
     * @return create_time 评论时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 评论时间
     * @param createTime 评论时间
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