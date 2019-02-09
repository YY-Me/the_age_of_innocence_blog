/*
*
* SystemRoleMenu.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-09-17
*/
package cn.admin.entity;

public class SystemRoleMenu {
    /**
     * 
     */
    private Long roleid;

    /**
     * 
     */
    private Long menuid;

    public SystemRoleMenu() {
        super();
    }

    public SystemRoleMenu(Long roleid, Long menuid) {
        super();
        this.roleid = roleid;
        this.menuid = menuid;
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

    /**
     * 
     * @return menuid 
     */
    public Long getMenuid() {
        return menuid;
    }

    /**
     * 
     * @param menuid 
     */
    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }
}