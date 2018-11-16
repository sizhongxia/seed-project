package com.company.project.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.company.project.configurer.QiniuConstant;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.UnitCompany;
import com.company.project.model.UnitProject;
import com.company.project.service.UnitCompanyService;
import com.company.project.service.UnitProjectService;
import com.company.project.unit.UuidUtil;
import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

@Controller
@RequestMapping("/upload")
public class UploadController {
	private Logger logger = LoggerFactory.getLogger(UploadController.class.getName());

	@Autowired
	private QiniuConstant qiniuConstant;
	@Autowired
	private UnitCompanyService unitCompanyService;
	@Autowired
	private UnitProjectService unitProjectService;

	@ResponseBody
	@PostMapping("/image")
	public Result<?> image(@RequestParam("avatar") MultipartFile multipartFile) throws IOException {
		FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
		String key = UuidUtil.initShort();
		if (uploadQNImg(inputStream, key)) {
			return ResultGenerator.genSuccessResult(String.format("%s%s%s", qiniuConstant.getPath(), key, "-200x200"));
		}
		return ResultGenerator.genFailResult("上传文件失败，请稍候重试");
	}

	@ResponseBody
	@PostMapping("/change/companylogo")
	public Result<?> changeCompanyLogo(@RequestParam("avatar") MultipartFile multipartFile, HttpServletRequest request)
			throws IOException {
		String uuid = request.getParameter("uuid");
		if (StringUtils.isBlank(uuid)) {
			return ResultGenerator.genFailResult("无效的公司ID");
		}

		UnitCompany company = unitCompanyService.findBy("uuid", uuid);
		if (company == null) {
			return ResultGenerator.genFailResult("无效的公司ID");
		}

		FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
		String key = UuidUtil.initShort();
		if (!uploadQNImg(inputStream, key)) {
			return ResultGenerator.genFailResult("上传文件失败，请稍候重试");
		}

		company.setLogo(key);
		company.setUpdatetime(System.currentTimeMillis());
		unitCompanyService.update(company);

		return ResultGenerator.genSuccessResult(String.format("%s%s", qiniuConstant.getPath(), key));
	}

	@ResponseBody
	@PostMapping("/change/projectlocationmap")
	public Result<?> changeProjectLocationMap(@RequestParam("avatar") MultipartFile multipartFile,
			HttpServletRequest request) throws IOException {

		String uuid = request.getParameter("uuid");
		if (StringUtils.isBlank(uuid)) {
			return ResultGenerator.genFailResult("无效的工地ID");
		}

		UnitProject project = unitProjectService.findBy("uuid", uuid);
		if (project == null) {
			return ResultGenerator.genFailResult("无效的工地ID");
		}

		FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
		String key = UuidUtil.initShort();
		if (!uploadQNImg(inputStream, key)) {
			return ResultGenerator.genFailResult("上传文件失败，请稍候重试");
		}

		project.setLocationmap(key);
		project.setUpdatetime(System.currentTimeMillis());
		unitProjectService.update(project);

		Map<String, String> res = new HashMap<>();
		res.put("url", String.format("%s%s", qiniuConstant.getPath(), key));
		res.put("type", "projectlocationmap");
		return ResultGenerator.genSuccessResult(res);
	}

	@ResponseBody
	@PostMapping("/change/projectlogo")
	public Result<?> changeProjectLogo(@RequestParam("avatar") MultipartFile multipartFile, HttpServletRequest request)
			throws IOException {

		String uuid = request.getParameter("uuid");
		if (StringUtils.isBlank(uuid)) {
			return ResultGenerator.genFailResult("无效的工地ID");
		}

		UnitProject project = unitProjectService.findBy("uuid", uuid);
		if (project == null) {
			return ResultGenerator.genFailResult("无效的工地ID");
		}

		FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
		String key = UuidUtil.initShort();
		if (!uploadQNImg(inputStream, key)) {
			return ResultGenerator.genFailResult("上传文件失败，请稍候重试");
		}

		project.setLogo(key);
		project.setUpdatetime(System.currentTimeMillis());
		unitProjectService.update(project);

		Map<String, String> res = new HashMap<>();
		res.put("url", String.format("%s%s", qiniuConstant.getPath(), key));
		res.put("type", "projectlogo");
		return ResultGenerator.genSuccessResult(res);
	}

	/**
	 * 将图片上传到七牛云
	 */
	private boolean uploadQNImg(FileInputStream file, String key) {
		// 构造一个带指定Zone对象的配置类
		Configuration cfg = new Configuration(Zone.huabei());
		// 其他参数参考类注释
		UploadManager uploadManager = new UploadManager(cfg);
		// 生成上传凭证，然后准备上传
		try {
			Auth auth = Auth.create(qiniuConstant.getAccessKey(), qiniuConstant.getSecretKey());
			String upToken = auth.uploadToken(qiniuConstant.getBucket());
			Response response = uploadManager.put(file, key, upToken, null, null);
			// 解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			logger.info("Upload File Success: key={}, hash={}", putRet.key, putRet.hash);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
