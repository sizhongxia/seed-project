package com.company.project.web.apiv1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.TokenCheck;
import com.company.project.configurer.QiniuConstant;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.PersonnelIdentity;
import com.company.project.model.PersonnelRealnameSystem;
import com.company.project.model.SysArea;
import com.company.project.model.UnitCompany;
import com.company.project.model.UnitDepartment;
import com.company.project.model.UnitGroup;
import com.company.project.model.UnitPost;
import com.company.project.model.UnitWorkType;
import com.company.project.model.om.PersonnelBaseModel;
import com.company.project.model.om.PersonnelBirthPlaceModel;
import com.company.project.model.om.PersonnelModel;
import com.company.project.model.om.PersonnelPhotoModel;
import com.company.project.model.param.apiv1.CommonParam;
import com.company.project.model.param.apiv1.PersonnelIdentityParam;
import com.company.project.model.param.apiv1.PersonnelParam;
import com.company.project.model.returns.GroupStatisticsResult;
import com.company.project.model.returns.apiv1.PersonnelBaseCensusResult;
import com.company.project.model.returns.apiv1.PersonnelResult;
import com.company.project.service.PersonnelIdentityService;
import com.company.project.service.PersonnelRealnameSystemService;
import com.company.project.service.SysAreaService;
import com.company.project.service.UnitCompanyService;
import com.company.project.service.UnitDepartmentService;
import com.company.project.service.UnitGroupService;
import com.company.project.service.UnitPostService;
import com.company.project.service.UnitWorkTypeService;
import com.company.project.unit.AesUtil;
import com.company.project.unit.QiuNiuStyle;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/apiv1/personnel")
public class PersonnelController {

	final Logger logger = LoggerFactory.getLogger(PersonnelController.class);

	@Resource
	private PersonnelRealnameSystemService personnelRealnameSystemService;
	@Resource
	private PersonnelIdentityService personnelIdentityService;
	@Resource
	private SysAreaService sysAreaService;
	@Resource
	private UnitCompanyService unitCompanyService;
	@Resource
	private UnitGroupService unitGroupService;
	@Resource
	private UnitWorkTypeService unitWorkTypeService;
	@Resource
	private UnitDepartmentService unitDepartmentService;
	@Resource
	private UnitPostService unitPostService;
	@Autowired
	private QiniuConstant qiniuConstant;

	@TokenCheck
	@PostMapping("/data")
	public Result<?> data(@Validated @RequestBody CommonParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		List<PersonnelResult> personnelResults = personnelRealnameSystemService.selectProjectPersonnels(param.getPid());
		List<Map<String, Object>> list = new ArrayList<>();

		if (personnelResults != null && personnelResults.size() > 0) {
			Map<String, Object> item = null;
			for (PersonnelResult pr : personnelResults) {
				item = new HashMap<>();
				try {
					item.put("id", AesUtil.encrypt(pr.getId()));
				} catch (Exception e) {
					logger.error(e.getMessage());
					return ResultGenerator.genFailResult("系统错误");
				}
				item.put("name", pr.getName());
				item.put("type", "1".equals(pr.getType()) ? "管理" : "劳务");
				item.put("empNo", pr.getEmpNo());
				item.put("code", pr.getCode());
				item.put("telephone", pr.getTelephone());
				item.put("nation", pr.getNation());
				item.put("sex", "1".equals(pr.getSex()) ? "男" : "女");
				item.put("age", pr.getAge());
				item.put("homeAddress", pr.getHomeAddress());
				list.add(item);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@TokenCheck
	@PostMapping("/save")
	public Result<?> save(@Validated @RequestBody PersonnelModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		String code = model.getCode();
		if (!IdcardUtil.isValidCard(code)) {
			return ResultGenerator.genFailResult("输入的身份证号无效");
		}

		String type = model.getType();
		if (!NumberUtils.isParsable(type)) {
			return ResultGenerator.genFailResult("无效的人员类型");
		}

		if (Integer.parseInt(type) == 2) {
			// 劳务
			if (StringUtils.isBlank(model.getGroupuuid())) {
				return ResultGenerator.genFailResult("请选择劳务人员班组");
			}
			if (StringUtils.isBlank(model.getWorktype())) {
				return ResultGenerator.genFailResult("请选择劳务人员工种");
			}
		} else if (Integer.parseInt(type) == 1) {
			// 管理
			if (StringUtils.isBlank(model.getDeptuuid())) {
				return ResultGenerator.genFailResult("请选择管理人员部门");
			}
			if (StringUtils.isBlank(model.getPost())) {
				return ResultGenerator.genFailResult("请选择管理人员岗位");
			}
		} else {
			return ResultGenerator.genFailResult("无效的人员类型");
		}

		StringBuffer birthplcae = new StringBuffer("");
		if (NumberUtils.isParsable(model.getBirthplace1())) {
			SysArea sysArea = sysAreaService.findBy("code", Integer.parseInt(model.getBirthplace1().trim()));
			if (sysArea != null) {
				birthplcae.append(sysArea.getCode().toString());

				if (NumberUtils.isParsable(model.getBirthplace2())) {
					sysArea = sysAreaService.findBy("code", Integer.parseInt(model.getBirthplace2().trim()));
					if (sysArea != null) {
						birthplcae.append(",");
						birthplcae.append(sysArea.getCode().toString());

						if (NumberUtils.isParsable(model.getBirthplace3())) {
							sysArea = sysAreaService.findBy("code", Integer.parseInt(model.getBirthplace3().trim()));
							if (sysArea != null) {
								birthplcae.append(",");
								birthplcae.append(sysArea.getCode().toString());
							}
						}

					}
				}

			}
		}

		model.setBirthplace(birthplcae.toString());
		try {
			personnelRealnameSystemService.saveByTransactional(model);
			return ResultGenerator.genSuccessResult("保存成功");
		} catch (Exception e) {
			return ResultGenerator.genFailResult(e.getMessage());
		}
	}

	@TokenCheck
	@PostMapping("/updateBaseInfo")
	public Result<?> updateBaseInfo(@Validated @RequestBody PersonnelBaseModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		String id = model.getId();
		if (StringUtils.isBlank(id)) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		String readId = null;
		try {
			readId = AesUtil.desEncrypt(model.getId());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (StringUtils.isBlank(readId)) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		String code = model.getCode();
		if (!IdcardUtil.isValidCard(code)) {
			return ResultGenerator.genFailResult("输入的身份证号无效");
		}

		PersonnelIdentity identity = personnelIdentityService.findById(Long.parseLong(readId.trim()));
		if (identity == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		PersonnelRealnameSystem personnel = personnelRealnameSystemService.findBy("empno", identity.getEmpno());
		if (personnel == null) {
			return ResultGenerator.genFailResult("未找到人员信息");
		}

		PersonnelRealnameSystem _byCode = personnelRealnameSystemService.findBy("code", code);
		if (_byCode != null && !_byCode.getEmpno().equals(personnel.getEmpno())) {
			return ResultGenerator.genFailResult("当前人员身份证号信息也存在");
		}

		personnel.setName(model.getName());
		personnel.setCode(code);
		personnel.setSex(Integer.parseInt(model.getSex().trim()));
		try {
			personnel.setBirthday(DateUtil.parse(model.getBirthday()));
		} catch (Exception e) {
		}
		personnel.setAge(Integer.parseInt(model.getAge().trim()));
		personnel.setNation(model.getNation());
		personnel.setHomeaddress(model.getHomeaddress());
		personnel.setTelephone(model.getTelephone());
		personnel.setUpdatetime(System.currentTimeMillis());
		personnelRealnameSystemService.update(personnel);
		return ResultGenerator.genSuccessResult("修改成功");
	}

	@TokenCheck
	@PostMapping("/updateBirthPlace")
	public Result<?> updateBirthPlace(@Validated @RequestBody PersonnelBirthPlaceModel model,
			BindingResult bindingResult, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		String id = model.getId();
		if (StringUtils.isBlank(id)) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		String readId = null;
		try {
			readId = AesUtil.desEncrypt(model.getId());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (StringUtils.isBlank(readId)) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		PersonnelIdentity identity = personnelIdentityService.findById(Long.parseLong(readId.trim()));
		if (identity == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		PersonnelRealnameSystem personnel = personnelRealnameSystemService.findBy("empno", identity.getEmpno());
		if (personnel == null) {
			return ResultGenerator.genFailResult("未找到人员信息");
		}

		StringBuffer birthplcae = new StringBuffer("");
		if (NumberUtils.isParsable(model.getBirthplace1())) {
			SysArea sysArea = sysAreaService.findBy("code", Integer.parseInt(model.getBirthplace1().trim()));
			if (sysArea != null) {
				birthplcae.append(sysArea.getCode().toString());

				if (NumberUtils.isParsable(model.getBirthplace2())) {
					sysArea = sysAreaService.findBy("code", Integer.parseInt(model.getBirthplace2().trim()));
					if (sysArea != null) {
						birthplcae.append(",");
						birthplcae.append(sysArea.getCode().toString());

						if (NumberUtils.isParsable(model.getBirthplace3())) {
							sysArea = sysAreaService.findBy("code", Integer.parseInt(model.getBirthplace3().trim()));
							if (sysArea != null) {
								birthplcae.append(",");
								birthplcae.append(sysArea.getCode().toString());
							}
						}

					}
				}

			}
		}

		personnel.setBirthplace(birthplcae.toString());
		personnel.setUpdatetime(System.currentTimeMillis());
		personnelRealnameSystemService.update(personnel);
		return ResultGenerator.genSuccessResult("修改成功");
	}

	@TokenCheck
	@PostMapping("/updatePhoto")
	public Result<?> updatePhoto(@Validated @RequestBody PersonnelPhotoModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		String id = model.getId();
		if (StringUtils.isBlank(id)) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		String readId = null;
		try {
			readId = AesUtil.desEncrypt(model.getId());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (StringUtils.isBlank(readId)) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		PersonnelIdentity identity = personnelIdentityService.findById(Long.parseLong(readId.trim()));
		if (identity == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		PersonnelRealnameSystem personnel = personnelRealnameSystemService.findBy("empno", identity.getEmpno());
		if (personnel == null) {
			return ResultGenerator.genFailResult("未找到人员信息");
		}
		personnel.setPhoto(model.getPhoto());
		personnel.setUpdatetime(System.currentTimeMillis());
		personnelRealnameSystemService.update(personnel);
		return ResultGenerator.genSuccessResult("修改成功");
	}

	@TokenCheck
	@PostMapping("/detail")
	public Result<?> detail(@Validated @RequestBody PersonnelParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		String id = param.getId();
		if (StringUtils.isBlank(id)) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		String readId = null;
		try {
			readId = AesUtil.desEncrypt(param.getId());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (StringUtils.isBlank(readId)) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		PersonnelIdentity identity = personnelIdentityService.findById(Long.parseLong(readId.trim()));
		if (identity == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		PersonnelRealnameSystem personnel = personnelRealnameSystemService.findBy("empno", identity.getEmpno());
		if (personnel == null) {
			return ResultGenerator.genFailResult("未找到人员信息");
		}

		Map<String, Object> data = new HashMap<>();
		data.put("id", id);
		data.put("empNo", personnel.getEmpno());
		data.put("name", personnel.getName());
		data.put("code", personnel.getCode());
		data.put("sex", personnel.getSex().toString());
		data.put("sexTxt", personnel.getSex().intValue() == 1 ? "男" : "女");
		data.put("birthday", DateUtil.format(personnel.getBirthday(), "yyyy-MM-dd"));
		data.put("age", personnel.getAge());

		String birthdayPlace = personnel.getBirthplace();
		StringBuffer bps = new StringBuffer("");
		if (StringUtils.isNotBlank(birthdayPlace)) {
			String[] birthPlaces = birthdayPlace.split("[,]");
			if (birthPlaces.length > 0) {
				String province = birthPlaces[0];
				if (NumberUtils.isParsable(province)) {
					SysArea area = sysAreaService.findBy("code", Integer.parseInt(province));
					if (area != null) {
						bps.append(area.getName());
					}
				}
			}
			if (birthPlaces.length > 1) {
				String city = birthPlaces[1];
				if (NumberUtils.isParsable(city)) {
					SysArea area = sysAreaService.findBy("code", Integer.parseInt(city));
					if (area != null) {
						bps.append(area.getName());
					}
				}
			}
			if (birthPlaces.length > 2) {
				String county = birthPlaces[2];
				if (NumberUtils.isParsable(county)) {
					SysArea area = sysAreaService.findBy("code", Integer.parseInt(county));
					if (area != null) {
						bps.append(area.getName());
					}
				}
			}
		}

		data.put("birthPlace", bps.toString());
		data.put("photo", personnel.getPhoto());
		data.put("nation", personnel.getNation());
		data.put("homeAddress", personnel.getHomeaddress());
		data.put("telephone", personnel.getTelephone());
		data.put("companyName", "");
		String companyUuid = identity.getCompanyuuid();
		if (StringUtils.isNotBlank(companyUuid)) {
			UnitCompany uc = unitCompanyService.findBy("uuid", companyUuid);
			if (uc != null) {
				data.put("companyName", uc.getCompanyname());
			}
		}
		Integer type = identity.getType();
		data.put("type", type);
		if (type.intValue() == 1) {
			data.put("typeName", "管理");
			data.put("department", "");
			data.put("post", "");
			if (StringUtils.isNotBlank(identity.getDeptuuid())) {
				UnitDepartment ud = unitDepartmentService.findBy("uuid", identity.getDeptuuid());
				if (ud != null) {
					data.put("department", ud.getDeptname());
				}
			}
			if (StringUtils.isNotBlank(identity.getPost())) {
				UnitPost up = unitPostService.findBy("uuid", identity.getPost());
				if (up != null) {
					data.put("post", up.getName());
				}
			}
		} else {
			data.put("typeName", "劳务");
			data.put("group", "");
			data.put("worktype", "");
			data.put("isGroupLeader",
					(identity.getIsgroupleader() != null && identity.getIsgroupleader().intValue() == 1) ? "是" : "否");
			if (StringUtils.isNotBlank(identity.getGroupuuid())) {
				UnitGroup ug = unitGroupService.findBy("uuid", identity.getGroupuuid());
				if (ug != null) {
					data.put("group", ug.getName());
				}
			}
			if (identity.getWorktype() != null) {
				UnitWorkType uw = unitWorkTypeService.findBy("id", identity.getWorktype());
				if (uw != null) {
					data.put("worktype", uw.getName());
				}
			}
		}

		return ResultGenerator.genSuccessResult(data);
	}

	@TokenCheck
	@PostMapping("/photo")
	public Result<?> photo(@Validated @RequestBody PersonnelParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		String id = param.getId();
		if (StringUtils.isBlank(id)) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		String readId = null;
		try {
			readId = AesUtil.desEncrypt(param.getId());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (StringUtils.isBlank(readId)) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		PersonnelIdentity identity = personnelIdentityService.findById(Long.parseLong(readId.trim()));
		if (identity == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		PersonnelRealnameSystem personnel = personnelRealnameSystemService.findBy("empno", identity.getEmpno());
		if (personnel == null) {
			return ResultGenerator.genFailResult("未找到人员信息");
		}

		Map<String, Object> data = new HashMap<>();
		data.put("id", id);
		data.put("photo", "");
		if (StringUtils.isNotBlank(personnel.getPhoto())) {
			data.put("photo",
					String.format("%s%s%s", qiniuConstant.getPath(), personnel.getPhoto(), QiuNiuStyle._140x200.code));
		}
		return ResultGenerator.genSuccessResult(data);
	}

	@TokenCheck
	@PostMapping("/delete/identity")
	public Result<?> deleteIdentity(@Validated @RequestBody PersonnelIdentityParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		String id = param.getId();
		if (StringUtils.isBlank(id)) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		String readId = null;
		try {
			readId = AesUtil.desEncrypt(param.getId());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (StringUtils.isBlank(readId)) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		PersonnelIdentity identity = personnelIdentityService.findById(Long.parseLong(readId.trim()));
		if (identity == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		identity.setState(1);
		identity.setUpdatetime(System.currentTimeMillis());
		personnelIdentityService.update(identity);
		return ResultGenerator.genSuccessResult("删除成功");
	}

	@TokenCheck
	@PostMapping("/statistics/worktype")
	public Result<?> statisticsWorktype(@Validated @RequestBody CommonParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		List<GroupStatisticsResult> personnelResults = personnelIdentityService
				.selectWorkTypeStatistics(param.getPid());
		if (personnelResults == null) {
			personnelResults = new ArrayList<>();
		}
		return ResultGenerator.genSuccessResult(personnelResults);
	}

	@TokenCheck
	@PostMapping("/statistics/basecensuss")
	public Result<?> statisticsBaseCensuss(@Validated @RequestBody CommonParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		List<PersonnelBaseCensusResult> baseCensusResults = personnelRealnameSystemService
				.selectProjectPersonnelBaseCensuss(param.getPid());

		Map<String, List<GroupStatisticsResult>> basecensuss = new HashMap<>();

		List<GroupStatisticsResult> ages = new ArrayList<>();
		ages.add(new GroupStatisticsResult("18岁以下", 0));
		ages.add(new GroupStatisticsResult("18-25岁", 0));
		ages.add(new GroupStatisticsResult("26-35岁", 0));
		ages.add(new GroupStatisticsResult("36-45岁", 0));
		ages.add(new GroupStatisticsResult("46-55岁", 0));
		ages.add(new GroupStatisticsResult("55岁以上", 0));
		basecensuss.put("ages", ages);

		List<GroupStatisticsResult> areas = new ArrayList<>();
		areas.add(new GroupStatisticsResult("北京市", 0));
		areas.add(new GroupStatisticsResult("天津市", 0));
		areas.add(new GroupStatisticsResult("上海市", 0));
		areas.add(new GroupStatisticsResult("重庆市", 0));
		areas.add(new GroupStatisticsResult("河北省", 0));
		areas.add(new GroupStatisticsResult("山西省", 0));
		areas.add(new GroupStatisticsResult("辽宁省", 0));
		areas.add(new GroupStatisticsResult("吉林省", 0));
		areas.add(new GroupStatisticsResult("黑龙江省", 0));
		areas.add(new GroupStatisticsResult("江苏省", 0));
		areas.add(new GroupStatisticsResult("浙江省", 0));
		areas.add(new GroupStatisticsResult("安徽省", 0));
		areas.add(new GroupStatisticsResult("福建省", 0));
		areas.add(new GroupStatisticsResult("江西省", 0));
		areas.add(new GroupStatisticsResult("山东省", 0));
		areas.add(new GroupStatisticsResult("河南省", 0));
		areas.add(new GroupStatisticsResult("湖北省", 0));
		areas.add(new GroupStatisticsResult("湖南省", 0));
		areas.add(new GroupStatisticsResult("广东省", 0));
		areas.add(new GroupStatisticsResult("海南省", 0));
		areas.add(new GroupStatisticsResult("四川省", 0));
		areas.add(new GroupStatisticsResult("贵州省", 0));
		areas.add(new GroupStatisticsResult("云南省", 0));
		areas.add(new GroupStatisticsResult("陕西省", 0));
		areas.add(new GroupStatisticsResult("甘肃省", 0));
		areas.add(new GroupStatisticsResult("青海省", 0));
		areas.add(new GroupStatisticsResult("台湾省", 0));
		areas.add(new GroupStatisticsResult("内蒙古自治区", 0));
		areas.add(new GroupStatisticsResult("广西壮族自治区", 0));
		areas.add(new GroupStatisticsResult("西藏自治区", 0));
		areas.add(new GroupStatisticsResult("宁夏回族自治区", 0));
		areas.add(new GroupStatisticsResult("新疆维吾尔自治区", 0));
		areas.add(new GroupStatisticsResult("香港特别行政区", 0));
		areas.add(new GroupStatisticsResult("澳门特别行政区", 0));
		basecensuss.put("areas", areas);

		List<GroupStatisticsResult> sexs = new ArrayList<>();
		sexs.add(new GroupStatisticsResult("男性", 0));
		sexs.add(new GroupStatisticsResult("女性", 0));
		basecensuss.put("sexs", sexs);

		int total = 0;
		if (baseCensusResults != null && baseCensusResults.size() > 0) {
			total = baseCensusResults.size();

			Condition areaCondition = new Condition(SysArea.class);
			areaCondition.createCriteria().andEqualTo("pcode", 0);
			List<SysArea> sysAreas = sysAreaService.findByCondition(areaCondition);

			for (PersonnelBaseCensusResult bcr : baseCensusResults) {
				ages = basecensuss.get("ages");
				GroupStatisticsResult age = ages.get(initAgeIndex(bcr.getAge()));
				age.setValue(age.getValue().intValue() + 1);

				areas = basecensuss.get("areas");
				GroupStatisticsResult area = areas.get(initAreaIndex(areas, sysAreas, bcr.getBirthplace()));
				area.setValue(area.getValue().intValue() + 1);

				sexs = basecensuss.get("sexs");
				GroupStatisticsResult sex = sexs.get(initSexIndex(bcr.getSex()));
				sex.setValue(sex.getValue().intValue() + 1);
			}
		}

		sexs = basecensuss.get("sexs");
		if (total > 0) {
			BigDecimal bd = new BigDecimal(sexs.get(0).getValue().intValue() * 100).divide(new BigDecimal(total), 0,
					RoundingMode.HALF_UP);
			sexs.get(0).setValue(bd.intValue());
			sexs.get(1).setValue(100 - bd.intValue());
		}

		return ResultGenerator.genSuccessResult(basecensuss);
	}

	private int initSexIndex(Integer sex) {
		if (sex == null) {
			return 0;
		}
		return 1 - sex.intValue();
	}

	private int initAreaIndex(List<GroupStatisticsResult> areas, List<SysArea> sysAreas, String birthplace) {
		if (StringUtils.isBlank(birthplace)) {
			return 0;
		}

		String code = birthplace.split("[,]")[0];
		if (StringUtils.isBlank(code)) {
			return 0;
		}

		String name = null;
		for (SysArea sysArea : sysAreas) {
			if (sysArea.getCode().intValue() == Integer.parseInt(code.trim())) {
				name = sysArea.getName();
				break;
			}
		}

		if (name == null) {
			return 0;
		}

		int index = 0;
		for (GroupStatisticsResult sr : areas) {
			if (sr.getName().equals(name)) {
				return index;
			}
			index++;
		}

		return 0;
	}

	private int initAgeIndex(Integer age) {
		if (age == null) {
			return 0;
		}
		if (age.intValue() < 18) {
			return 0;
		}
		if (age.intValue() <= 25) {
			return 1;
		}
		if (age.intValue() <= 35) {
			return 2;
		}
		if (age.intValue() <= 45) {
			return 3;
		}
		if (age.intValue() <= 55) {
			return 4;
		}
		return 5;
	}
}
