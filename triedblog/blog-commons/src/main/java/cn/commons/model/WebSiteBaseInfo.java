package cn.commons.model;

/**
 * 
 * @ClassName:  WebSiteBaseInfo   
 * @Description: 网站基本信息类
 * @author: yuyong 
 * @date:   2018年9月17日 下午10:11:02   
 *     
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved. 
 * @note: 注意：本内容仅限于xxx公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class WebSiteBaseInfo {

    private String sitename;

    private String domain;

    private String title;

    private String keywords;

    private String descript;

    private String copyright;

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

}
