/*
*
* SystemUserRole.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-09-17
*/
package cn.admin.entity;

public class SystemUserRole {
    /**
     * 
     */
    private String userid;

    /**
     * 
     */
    private Long roleid;

    /**
     * 
     * @return userid 
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 
     * @param userid 
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * 
     * @return roleid 
     */
    public Long getRoleid() {
        return roleid;
    }

    /**
     * 
     * @param roleid 
     */
    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }
}