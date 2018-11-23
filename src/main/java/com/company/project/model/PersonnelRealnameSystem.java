package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "personnel_realname_system")
public class PersonnelRealnameSystem {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 人员编号
     */
    @Column(name = "empNo")
    private String empno;

    /**
     * 人员姓名
     */
    private String name;

    /**
     * 身份证号       身份证号最为全表唯一主键
     */
    private String code;

    /**
     * sys_dictionary表    userSex 字段值 0 女 1 男
     */
    private Integer sex;

    /**
     * 生日         
     */
    private Date birthday;

    private Integer age;

    /**
     * 籍贯
     */
    private String birthplace;

    /**
     * 人员头像
     */
    private String photo;

    /**
     * 民族
     */
    private String nation;

    /**
     * 家庭住址
     */
    @Column(name = "homeAddress")
    private String homeaddress;

    /**
     * 手机号码
     */
    private String telephone;

    /**
     * 是否手动输入的人员实名制信息，1否  0是
     */
    @Column(name = "manualInput")
    private String manualinput;

    /**
     * 入场日期：用字符串的形式存储有可能为空。
     */
    @Column(name = "dateIn")
    private String datein;

    /**
     * 增加时间
     */
    @Column(name = "addTime")
    private Long addtime;

    /**
     * 修改时间
     */
    @Column(name = "updateTime")
    private Long updatetime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取人员编号
     *
     * @return empNo - 人员编号
     */
    public String getEmpno() {
        return empno;
    }

    /**
     * 设置人员编号
     *
     * @param empno 人员编号
     */
    public void setEmpno(String empno) {
        this.empno = empno;
    }

    /**
     * 获取人员姓名
     *
     * @return name - 人员姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置人员姓名
     *
     * @param name 人员姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取身份证号       身份证号最为全表唯一主键
     *
     * @return code - 身份证号       身份证号最为全表唯一主键
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置身份证号       身份证号最为全表唯一主键
     *
     * @param code 身份证号       身份证号最为全表唯一主键
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取sys_dictionary表    userSex 字段值 0 女 1 男
     *
     * @return sex - sys_dictionary表    userSex 字段值 0 女 1 男
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置sys_dictionary表    userSex 字段值 0 女 1 男
     *
     * @param sex sys_dictionary表    userSex 字段值 0 女 1 男
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取生日         
     *
     * @return birthday - 生日         
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置生日         
     *
     * @param birthday 生日         
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取籍贯
     *
     * @return birthplace - 籍贯
     */
    public String getBirthplace() {
        return birthplace;
    }

    /**
     * 设置籍贯
     *
     * @param birthplace 籍贯
     */
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    /**
     * 获取人员头像
     *
     * @return photo - 人员头像
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置人员头像
     *
     * @param photo 人员头像
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * 获取民族
     *
     * @return nation - 民族
     */
    public String getNation() {
        return nation;
    }

    /**
     * 设置民族
     *
     * @param nation 民族
     */
    public void setNation(String nation) {
        this.nation = nation;
    }

    /**
     * 获取家庭住址
     *
     * @return homeAddress - 家庭住址
     */
    public String getHomeaddress() {
        return homeaddress;
    }

    /**
     * 设置家庭住址
     *
     * @param homeaddress 家庭住址
     */
    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    /**
     * 获取手机号码
     *
     * @return telephone - 手机号码
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置手机号码
     *
     * @param telephone 手机号码
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取是否手动输入的人员实名制信息，1否  0是
     *
     * @return manualInput - 是否手动输入的人员实名制信息，1否  0是
     */
    public String getManualinput() {
        return manualinput;
    }

    /**
     * 设置是否手动输入的人员实名制信息，1否  0是
     *
     * @param manualinput 是否手动输入的人员实名制信息，1否  0是
     */
    public void setManualinput(String manualinput) {
        this.manualinput = manualinput;
    }

    /**
     * 获取入场日期：用字符串的形式存储有可能为空。
     *
     * @return dateIn - 入场日期：用字符串的形式存储有可能为空。
     */
    public String getDatein() {
        return datein;
    }

    /**
     * 设置入场日期：用字符串的形式存储有可能为空。
     *
     * @param datein 入场日期：用字符串的形式存储有可能为空。
     */
    public void setDatein(String datein) {
        this.datein = datein;
    }

    /**
     * 获取增加时间
     *
     * @return addTime - 增加时间
     */
    public Long getAddtime() {
        return addtime;
    }

    /**
     * 设置增加时间
     *
     * @param addtime 增加时间
     */
    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }

    /**
     * 获取修改时间
     *
     * @return updateTime - 修改时间
     */
    public Long getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置修改时间
     *
     * @param updatetime 修改时间
     */
    public void setUpdatetime(Long updatetime) {
        this.updatetime = updatetime;
    }
}