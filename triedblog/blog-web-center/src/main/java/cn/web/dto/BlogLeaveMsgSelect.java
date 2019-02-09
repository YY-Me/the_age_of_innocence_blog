package cn.web.dto;

import java.io.Serializable;
import java.util.List;

import cn.web.entity.BlogLeaveMsg;

public class BlogLeaveMsgSelect implements Serializable {

    /**
     * @author 余勇
     * @email 1396513066@qq.com
     * @date 2018年8月2日
     */
    private static final long serialVersionUID = 1L;

    private BlogLeaveMsg parent;

    private List<BlogLeaveMsgSelect> child;

    public BlogLeaveMsg getParent() {
        return parent;
    }

    public void setParent(BlogLeaveMsg parent) {
        this.parent = parent;
    }

    public List<BlogLeaveMsgSelect> getChild() {
        return child;
    }

    public void setChild(List<BlogLeaveMsgSelect> child) {
        this.child = child;
    }
}
