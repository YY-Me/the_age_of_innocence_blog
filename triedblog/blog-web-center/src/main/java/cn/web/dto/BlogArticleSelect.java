package cn.web.dto;

import java.util.List;

import cn.web.entity.BlogArticle;
import cn.web.entity.BlogLabelClassify;

/**
 * 帖子、标签、发布人
 * 
 * @author 偶尔有点困
 * @date 2018年6月10日
 */
public class BlogArticleSelect extends BlogArticle {

    private static final long serialVersionUID = -6105546108044262572L;

    private String nickname;

    private String headimgurl;

    private Long comPv = 0L;

    List<BlogLabelClassify> lable;

    List<BlogLabelClassify> classify;

    BlogArticle pre;

    BlogArticle next;

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Long getComPv() {
        return comPv;
    }

    public void setComPv(Long comPv) {
        this.comPv = comPv;
    }

    public BlogArticle getPre() {
        return pre;
    }

    public void setPre(BlogArticle pre) {
        this.pre = pre;
    }

    public BlogArticle getNext() {
        return next;
    }

    public void setNext(BlogArticle next) {
        this.next = next;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<BlogLabelClassify> getLable() {
        return lable;
    }

    public void setLable(List<BlogLabelClassify> lable) {
        this.lable = lable;
    }

    public List<BlogLabelClassify> getClassify() {
        return classify;
    }

    public void setClassify(List<BlogLabelClassify> classify) {
        this.classify = classify;
    }

}
