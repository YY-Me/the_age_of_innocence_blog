/*
*
* DayOsTimesStatKey.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-10-08
*/
package cn.web.entity;

public class DayOsTimesStatKey {
    /**
     * 
     */
    private String day;

    /**
     * 
     */
    private String os;

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
     * @return os 
     */
    public String getOs() {
        return os;
    }

    /**
     * 
     * @param os 
     */
    public void setOs(String os) {
        this.os = os == null ? null : os.trim();
    }
}