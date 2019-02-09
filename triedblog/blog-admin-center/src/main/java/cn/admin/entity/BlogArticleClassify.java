/*
*
* BlogArticleClassify.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-09-17
*/
package cn.admin.entity;

public class BlogArticleClassify {
    /**
     * 文章id
     */
    private String articleid;

    /**
     * 分类id，也就是字典库里面的id
     */
    private Integer classifyid;

    /**
     * 文章id
     * @return articleid 文章id
     */
    public String getArticleid() {
        return articleid;
    }

    /**
     * 文章id
     * @param articleid 文章id
     */
    public void setArticleid(String articleid) {
        this.articleid = articleid == null ? null : articleid.trim();
    }

    /**
     * 分类id，也就是字典库里面的id
     * @return classifyid 分类id，也就是字典库里面的id
     */
    public Integer getClassifyid() {
        return classifyid;
    }

    /**
     * 分类id，也就是字典库里面的id
     * @param classifyid 分类id，也就是字典库里面的id
     */
    public void setClassifyid(Integer classifyid) {
        this.classifyid = classifyid;
    }
}