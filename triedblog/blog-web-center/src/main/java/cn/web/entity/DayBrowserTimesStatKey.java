/*
*
* DayBrowserTimesStatKey.java
* Copyright(C) 2017-2020 fendo公司
* @date 2018-10-08
*/
package cn.web.entity;

public class DayBrowserTimesStatKey {
    /**
     * 
     */
    private String day;

    /**
     * 
     */
    private String browser;

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
     * @return browser 
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * 
     * @param browser 
     */
    public void setBrowser(String browser) {
        this.browser = browser == null ? null : browser.trim();
    }
}