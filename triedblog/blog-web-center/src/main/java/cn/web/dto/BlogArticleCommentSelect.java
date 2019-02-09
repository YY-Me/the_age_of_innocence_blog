package cn.web.dto;

import java.io.Serializable;
import java.util.List;

import cn.web.entity.BlogArticleComment;

/**
 * 帖子评论查询简单列表
 * 
 * @author 余勇
 * @email 1396513066@qq.com
 * @date 2018年6月30日
 */
public class BlogArticleCommentSelect implements Serializable {

    /**
     * @author 余勇
     * @email 1396513066@qq.com
     * @date 2018年6月30日
     */
    private static final long serialVersionUID = -4808015690231103229L;

    private BlogArticleComment parent;

    private List<BlogArticleCommentSelect> child;

    public BlogArticleComment getParent() {
        return parent;
    }

    public void setParent(BlogArticleComment parent) {
        this.parent = parent;
    }

    public List<BlogArticleCommentSelect> getChild() {
        return child;
    }

    public void setChild(List<BlogArticleCommentSelect> child) {
        this.child = child;
    }

}
