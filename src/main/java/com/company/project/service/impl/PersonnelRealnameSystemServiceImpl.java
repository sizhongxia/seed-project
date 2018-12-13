package com.company.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.core.AbstractService;
import com.company.project.dao.PersonnelRealnameSystemMapper;
import com.company.project.model.PersonnelIdentity;
import com.company.project.model.PersonnelRealnameSystem;
import com.company.project.model.om.PersonnelModel;
import com.company.project.model.returns.apiv1.PersonnelBaseCensusResult;
import com.company.project.model.returns.apiv1.PersonnelResult;
import com.company.project.service.PersonnelIdentityService;
import com.company.project.service.PersonnelRealnameSystemService;
import com.company.project.unit.IdWorker;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.RandomUtil;
import tk.mybatis.mapper.entity.Condition;

/**
 * Created by SiZhongXia on 2018/11/22.
 */
@Service
@Transactional
public class PersonnelRealnameSystemServiceImpl extends AbstractService<PersonnelRealnameSystem>
		implements PersonnelRealnameSystemService {

	private final Logger logger = LoggerFactory.getLogger(PersonnelRealnameSystemServiceImpl.class);

	@Resource
	private PersonnelRealnameSystemMapper personnelRealnameSystemMapper;
	@Resource
	private IdWorker idWorker;
	@Resource
	private PersonnelIdentityService personnelIdentityService;

	@Override
	public void saveByTransactional(PersonnelModel model) {

		PersonnelRealnameSystem record = new PersonnelRealnameSystem();
		record.setCode(model.getCode()); // 身份证号查询唯一性
		PersonnelRealnameSystem realnameSystem = personnelRealnameSystemMapper.selectOne(record);
		if (realnameSystem == null) {
			realnameSystem = new PersonnelRealnameSystem();
			String maxEmpNo = personnelRealnameSystemMapper.selectMaxEmpNo();
			if (NumberUtils.isParsable(maxEmpNo)) {
				maxEmpNo = Integer.parseInt(maxEmpNo) + RandomUtil.randomInt(1, 9)+"";
			} else {
				maxEmpNo = RandomUtil.randomInt(100001, 100010)+"";
			}
			realnameSystem.setEmpno(maxEmpNo);
			realnameSystem.setName(model.getName());
			realnameSystem.setCode(model.getCode());
			realnameSystem.setSex(Integer.parseInt(model.getSex().trim()));
			DateTime birthday = null;
			try {
				birthday = DateUtil.parse(model.getBirthday());
			} catch (Exception e) {
				logger.error(e.getMessage());
				birthday = IdcardUtil.getBirthDate(model.getCode());
			}
			if (birthday == null) {
				throw new RuntimeException("请输入有效的出生日期");
			}
			realnameSystem.setAge(Integer.parseInt(model.getAge().trim()));
			realnameSystem.setBirthday(birthday);
			realnameSystem.setBirthplace(model.getBirthplace());
			realnameSystem.setPhoto(model.getPhoto());
			realnameSystem.setNation(model.getNation());
			realnameSystem.setHomeaddress(model.getHomeaddress());
			realnameSystem.setTelephone(model.getTelephone());
			realnameSystem.setManualinput("0");
			realnameSystem.setDatein("");
			realnameSystem.setAddtime(System.currentTimeMillis());
			realnameSystem.setUpdatetime(System.currentTimeMillis());
			personnelRealnameSystemMapper.insert(realnameSystem);
		}

		Condition condition = new Condition(PersonnelIdentity.class);
		condition.createCriteria().andEqualTo("prouuid", model.getProuuid())
				.andEqualTo("empno", realnameSystem.getEmpno())
				.andEqualTo("type", Integer.parseInt(model.getType().trim()));
		List<PersonnelIdentity> personnelIdentities = personnelIdentityService.findByCondition(condition);
		if (personnelIdentities != null && personnelIdentities.size() > 0) {
			throw new RuntimeException("当前用户身份已存在");
		}

		PersonnelIdentity personnelIdentity = new PersonnelIdentity();
		personnelIdentity.setEmpno(realnameSystem.getEmpno());
		personnelIdentity.setType(Integer.parseInt(model.getType().trim()));
		personnelIdentity.setCompanyuuid(model.getCompanyuuid());
		personnelIdentity.setProuuid(model.getProuuid());

		if (personnelIdentity.getType().intValue() == 1) {
			// 管理
			personnelIdentity.setDeptuuid(model.getDeptuuid());
			personnelIdentity.setPost(model.getPost());
		} else if (personnelIdentity.getType().intValue() == 2) {
			// 劳务
			personnelIdentity.setGroupuuid(model.getGroupuuid());
			personnelIdentity.setIsgroupleader(Integer.parseInt(model.getIsgroupleader()));
			personnelIdentity.setWorktype(Integer.parseInt(model.getWorktype()));
		} else {
			throw new RuntimeException("无效的人员类型");
		}
		personnelIdentity.setState(0);
		personnelIdentity.setAddtime(System.currentTimeMillis());
		personnelIdentity.setUpdatetime(System.currentTimeMillis());
		personnelIdentityService.save(personnelIdentity);
	}

	@Override
	public List<PersonnelResult> selectProjectPersonnels(String proUuid) {
		return this.personnelRealnameSystemMapper.selectProjectPersonnels(proUuid);
	}

	@Override
	public List<PersonnelBaseCensusResult> selectProjectPersonnelBaseCensuss(String proUuid) {
		return this.personnelRealnameSystemMapper.selectProjectPersonnelBaseCensuss(proUuid);
	}

}
