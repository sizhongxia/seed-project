package com.company.project.model;

import javax.persistence.*;

@Table(name = "admin_user")
public class AdminUser {
    /**
     * 自增id   不作为主键
     */
    @Id
    private Integer id;

    /**
     * 数据id唯一标识
     */
    private String uuid;

    /**
     * 用户身份证号，   全表唯一不重复
     */
    @Column(name = "idNumber")
    private String idnumber;

    /**
     * 人员登陆用户名   全表唯一不重复
     */
    @Column(name = "userName")
    private String username;

    /**
     * 手机号               全表唯一不重复
     */
    private String phone;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户添加时间
     */
    @Column(name = "addTime")
    private Long addtime;

    /**
     * 更新个人信息时间
     */
    @Column(name = "updateTime")
    private Long updatetime;

    /**
     * 用户密码md5加密
     */
    private String password;

    /**
     * 用户头像文件名
     */
    private String photo;

    /**
     * 用户性别  0 女 1 男
     */
    private Integer sex;

    /**
     * 用户状态 0正常1删除2停用
     */
    private Integer state;

    /**
     * 系统用户1
     */
    private Integer type;

    /**
     * admin_company企业
     */
    @Column(name = "companyUuid")
    private String companyuuid;

    /**
     * 用户所属部门
     */
    @Column(name = "departmentUuid")
    private String departmentuuid;

    /**
     * 获取自增id   不作为主键
     *
     * @return id - 自增id   不作为主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增id   不作为主键
     *
     * @param id 自增id   不作为主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取数据id唯一标识
     *
     * @return uuid - 数据id唯一标识
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置数据id唯一标识
     *
     * @param uuid 数据id唯一标识
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * 获取用户身份证号，   全表唯一不重复
     *
     * @return idNumber - 用户身份证号，   全表唯一不重复
     */
    public String getIdnumber() {
        return idnumber;
    }

    /**
     * 设置用户身份证号，   全表唯一不重复
     *
     * @param idnumber 用户身份证号，   全表唯一不重复
     */
    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    /**
     * 获取人员登陆用户名   全表唯一不重复
     *
     * @return userName - 人员登陆用户名   全表唯一不重复
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置人员登陆用户名   全表唯一不重复
     *
     * @param username 人员登陆用户名   全表唯一不重复
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取手机号               全表唯一不重复
     *
     * @return phone - 手机号               全表唯一不重复
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号               全表唯一不重复
     *
     * @param phone 手机号               全表唯一不重复
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取用户姓名
     *
     * @return name - 用户姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户姓名
     *
     * @param name 用户姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取用户添加时间
     *
     * @return addTime - 用户添加时间
     */
    public Long getAddtime() {
        return addtime;
    }

    /**
     * 设置用户添加时间
     *
     * @param addtime 用户添加时间
     */
    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取更新个人信息时间
     *
     * @return updateTime - 更新个人信息时间
     */
    public Long getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置更新个人信息时间
     *
     * @param updatetime 更新个人信息时间
     */
    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取用户密码md5加密
     *
     * @return password - 用户密码md5加密
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码md5加密
     *
     * @param password 用户密码md5加密
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户头像文件名
     *
     * @return photo - 用户头像文件名
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置用户头像文件名
     *
     * @param photo 用户头像文件名
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * 获取用户性别  0 女 1 男
     *
     * @return sex - 用户性别  0 女 1 男
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置用户性别  0 女 1 男
     *
     * @param sex 用户性别  0 女 1 男
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取用户状态 0正常1删除2停用
     *
     * @return state - 用户状态 0正常1删除2停用
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置用户状态 0正常1删除2停用
     *
     * @param state 用户状态 0正常1删除2停用
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取系统用户1
     *
     * @return type - 系统用户1
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置系统用户1
     *
     * @param type 系统用户1
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取admin_company企业
     *
     * @return companyUuid - admin_company企业
     */
    public String getCompanyuuid() {
        return companyuuid;
    }

    /**
     * 设置admin_company企业
     *
     * @param companyuuid admin_company企业
     */
    public void setCompanyuuid(String companyuuid) {
        this.companyuuid = companyuuid;
    }

    /**
     * 获取用户所属部门
     *
     * @return departmentUuid - 用户所属部门
     */
    public String getDepartmentuuid() {
        return departmentuuid;
    }

    /**
     * 设置用户所属部门
     *
     * @param departmentuuid 用户所属部门
     */
    public void setDepartmentuuid(String departmentuuid) {
        this.departmentuuid = departmentuuid;
    }
}