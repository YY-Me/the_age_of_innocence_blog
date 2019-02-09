/*
*
* BlogArticleLable.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-09-21
*/
package cn.admin.entity;

public class BlogArticleLable {
    /**
     * 文字id
     */
    private String articleid;

    /**
     * 标签id，也就是字典里面的
     */
    private Integer lableid;

    /**
     * 文字id
     * @return articleid 文字id
     */
    public String getArticleid() {
        return articleid;
    }

    /**
     * 文字id
     * @param articleid 文字id
     */
    public void setArticleid(String articleid) {
        this.articleid = articleid == null ? null : articleid.trim();
    }

    /**
     * 标签id，也就是字典里面的
     * @return lableid 标签id，也就是字典里面的
     */
    public Integer getLableid() {
        return lableid;
    }

    /**
     * 标签id，也就是字典里面的
     * @param lableid 标签id，也就是字典里面的
     */
    public void setLableid(Integer lableid) {
        this.lableid = lableid;
    }
}