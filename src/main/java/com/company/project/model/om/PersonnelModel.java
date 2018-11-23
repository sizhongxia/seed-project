package com.company.project.model.om;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class PersonnelModel {

	private String empno;

	@NotBlank(message = "请输入用户名称")
	private String name;
	@NotBlank(message = "请输入用户身份证号")
	private String code;
	@NotBlank(message = "请选择用户性别")
	private String sex;
	@NotBlank(message = "请输入用户出生日期")
	private String birthday;

	@Min(value = 16, message = "年龄值无效")
	@Max(value = 100, message = "年龄值无效")
	@NotBlank(message = "请输入用户年龄")
	private String age;

	@NotBlank(message = "请输入用户籍贯")
	private String birthplace;
	private String photo;
	@NotBlank(message = "请选择用户所属民族")
	private String nation;
	@NotBlank(message = "请输入用户家庭住址")
	private String homeaddress;
	@NotBlank(message = "请输入用户联系方式")
	private String telephone;

	private String manualinput;

	private String datein;

	private String type;
	private String companyuuid;
	private String prouuid;

	private String groupuuid;
	private String isgroupleader;
	private String worktype;
	private String deptuuid;
	private String post;

	public String getEmpno() {
		return empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getHomeaddress() {
		return homeaddress;
	}

	public void setHomeaddress(String homeaddress) {
		this.homeaddress = homeaddress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDatein() {
		return datein;
	}

	public void setDatein(String datein) {
		this.datein = datein;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompanyuuid() {
		return companyuuid;
	}

	public void setCompanyuuid(String companyuuid) {
		this.companyuuid = companyuuid;
	}

	public String getProuuid() {
		return prouuid;
	}

	public void setProuuid(String prouuid) {
		this.prouuid = prouuid;
	}

	public String getGroupuuid() {
		return groupuuid;
	}

	public void setGroupuuid(String groupuuid) {
		this.groupuuid = groupuuid;
	}

	public String getIsgroupleader() {
		return isgroupleader;
	}

	public void setIsgroupleader(String isgroupleader) {
		this.isgroupleader = isgroupleader;
	}

	public String getWorktype() {
		return worktype;
	}

	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}

	public String getDeptuuid() {
		return deptuuid;
	}

	public void setDeptuuid(String deptuuid) {
		this.deptuuid = deptuuid;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getManualinput() {
		return manualinput;
	}

	public void setManualinput(String manualinput) {
		this.manualinput = manualinput;
	}

}
