/*
*
* BlogLabelClassify.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-09-21
*/
package cn.web.entity;

public class BlogLabelClassify {
    /**
     * 
     */
    private Long id;

    /**
     * 分类、标签名称
     */
    private String name;

    /**
     * 0：标签，1：分类
     */
    private String type;

    /**
     * 背景
     */
    private String color;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 分类、标签名称
     * @return name 分类、标签名称
     */
    public String getName() {
        return name;
    }

    /**
     * 分类、标签名称
     * @param name 分类、标签名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 0：标签，1：分类
     * @return type 0：标签，1：分类
     */
    public String getType() {
        return type;
    }

    /**
     * 0：标签，1：分类
     * @param type 0：标签，1：分类
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 背景
     * @return color 背景
     */
    public String getColor() {
        return color;
    }

    /**
     * 背景
     * @param color 背景
     */
    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }
}