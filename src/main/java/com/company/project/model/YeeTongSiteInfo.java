package com.company.project.model;

import javax.persistence.*;

@Table(name = "yeetong_siteinfo")
public class YeeTongSiteInfo {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 系统标题
     */
    @Column(name = "web_title")
    private String webTitle;

    /**
     * 系统LOGO
     */
    @Column(name = "web_logo")
    private String webLogo;

    /**
     * SEO作者
     */
    @Column(name = "seo_author")
    private String seoAuthor;

    /**
     * SEO关键词
     */
    @Column(name = "seo_keywords")
    private String seoKeywords;

    @Column(name = "seo_description")
    private String seoDescription;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 公司电话
     */
    @Column(name = "company_telno")
    private String companyTelno;

    /**
     * 公司地址
     */
    @Column(name = "company_address")
    private String companyAddress;

    /**
     * 版权信息
     */
    private String copyright;

    /**
     * 公众号名称
     */
    @Column(name = "mp_name")
    private String mpName;

    /**
     * 公众号二维码
     */
    @Column(name = "mp_qrcode")
    private String mpQrcode;

    /**
     * 启用状态
     */
    @Column(name = "use_state")
    private String useState;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取系统标题
     *
     * @return web_title - 系统标题
     */
    public String getWebTitle() {
        return webTitle;
    }

    /**
     * 设置系统标题
     *
     * @param webTitle 系统标题
     */
    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    /**
     * 获取系统LOGO
     *
     * @return web_logo - 系统LOGO
     */
    public String getWebLogo() {
        return webLogo;
    }

    /**
     * 设置系统LOGO
     *
     * @param webLogo 系统LOGO
     */
    public void setWebLogo(String webLogo) {
        this.webLogo = webLogo;
    }

    /**
     * 获取SEO作者
     *
     * @return seo_author - SEO作者
     */
    public String getSeoAuthor() {
        return seoAuthor;
    }

    /**
     * 设置SEO作者
     *
     * @param seoAuthor SEO作者
     */
    public void setSeoAuthor(String seoAuthor) {
        this.seoAuthor = seoAuthor;
    }

    /**
     * 获取SEO关键词
     *
     * @return seo_keywords - SEO关键词
     */
    public String getSeoKeywords() {
        return seoKeywords;
    }

    /**
     * 设置SEO关键词
     *
     * @param seoKeywords SEO关键词
     */
    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }

    /**
     * @return seo_description
     */
    public String getSeoDescription() {
        return seoDescription;
    }

    /**
     * @param seoDescription
     */
    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }

    /**
     * 获取公司名称
     *
     * @return company_name - 公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司名称
     *
     * @param companyName 公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取公司电话
     *
     * @return company_telno - 公司电话
     */
    public String getCompanyTelno() {
        return companyTelno;
    }

    /**
     * 设置公司电话
     *
     * @param companyTelno 公司电话
     */
    public void setCompanyTelno(String companyTelno) {
        this.companyTelno = companyTelno;
    }

    /**
     * 获取公司地址
     *
     * @return company_address - 公司地址
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * 设置公司地址
     *
     * @param companyAddress 公司地址
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    /**
     * 获取版权信息
     *
     * @return copyright - 版权信息
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * 设置版权信息
     *
     * @param copyright 版权信息
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * 获取公众号名称
     *
     * @return mp_name - 公众号名称
     */
    public String getMpName() {
        return mpName;
    }

    /**
     * 设置公众号名称
     *
     * @param mpName 公众号名称
     */
    public void setMpName(String mpName) {
        this.mpName = mpName;
    }

    /**
     * 获取公众号二维码
     *
     * @return mp_qrcode - 公众号二维码
     */
    public String getMpQrcode() {
        return mpQrcode;
    }

    /**
     * 设置公众号二维码
     *
     * @param mpQrcode 公众号二维码
     */
    public void setMpQrcode(String mpQrcode) {
        this.mpQrcode = mpQrcode;
    }

    /**
     * 获取启用状态
     *
     * @return use_state - 启用状态
     */
    public String getUseState() {
        return useState;
    }

    /**
     * 设置启用状态
     *
     * @param useState 启用状态
     */
    public void setUseState(String useState) {
        this.useState = useState;
    }
}