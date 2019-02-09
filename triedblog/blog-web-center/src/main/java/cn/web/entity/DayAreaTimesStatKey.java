/*
*
* DayAreaTimesStatKey.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-10-08
*/
package cn.web.entity;

public class DayAreaTimesStatKey {
    /**
     * 
     */
    private String day;

    /**
     * 
     */
    private String area;

    /**
     * 
     * @return day 
     */
    public String getDay() {
        return day;
    }

    /**
     * 
     * @param day 
     */
    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
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
}