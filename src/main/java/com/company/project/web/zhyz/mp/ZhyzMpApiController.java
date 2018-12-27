package com.company.project.web.zhyz.mp;

import java.io.File;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaKefuMessage;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateData;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import cn.binarywang.wx.miniapp.message.WxMaMessageHandler;
import cn.binarywang.wx.miniapp.message.WxMaMessageRouter;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;

@RestController
@RequestMapping("/zhyz/miniapp/api")
public class ZhyzMpApiController {
	final Logger logger = LoggerFactory.getLogger(ZhyzMpApiController.class);

	final String APP_ID = "wxb4bab327bf7710a4";
	final String APP_SECRET = "ad3fbc9a1a0cc7c6718a3df15671d41e";

	final String ENCODING_AES_KEY = "FMmT5bZBZyXTTlLr6GIc8izLSZDMCoKGL6KyJzCaJeh";
	final String TOKEN = "bOSKGNuvXd2Cdhv2Ctz44Mu9vYZsnORL";

	@GetMapping(value = "/index", produces = "text/plain;charset=utf-8")
	public String authGet(@RequestParam(name = "signature", required = false) String signature,
			@RequestParam(name = "timestamp", required = false) String timestamp,
			@RequestParam(name = "nonce", required = false) String nonce,
			@RequestParam(name = "echostr", required = false) String echostr) {
		logger.info("\n接收到来自微信服务器的认证消息：signature = [{}], timestamp = [{}], nonce = [{}], echostr = [{}]", signature,
				timestamp, nonce, echostr);
		if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
			throw new IllegalArgumentException("请求参数非法，请核实!");
		}
		WxMaService wxService = getWxService();
		if (wxService.checkSignature(timestamp, nonce, signature)) {
			return echostr;
		}
		return "FALSE";
	}

	private WxMaService getWxService() {
		WxMaInMemoryConfig config = new WxMaInMemoryConfig();
		config.setAppid(APP_ID);
		config.setSecret(APP_SECRET);
		config.setToken(TOKEN);
		config.setAesKey(ENCODING_AES_KEY);
		config.setMsgDataFormat("JSON");
		WxMaService wxService = new WxMaServiceImpl();
		wxService.setWxMaConfig(config);
		return wxService;
	}

	@ResponseBody
	@PostMapping(value = "/index", produces = "application/xml; charset=UTF-8")
	public String post(@RequestBody String requestBody, @RequestParam("msg_signature") String msgSignature,
			@RequestParam("encrypt_type") String encryptType, @RequestParam("signature") String signature,
			@RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce) {
		this.logger.info(
				"\n接收微信请求：[msg_signature=[{}], encrypt_type=[{}], signature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				msgSignature, encryptType, signature, timestamp, nonce, requestBody);
		WxMaService wxService = getWxService();
		final boolean isJson = Objects.equals(wxService.getWxMaConfig().getMsgDataFormat(),
				WxMaConstants.MsgDataFormat.JSON);
		WxMaMessage inMessage = null;
		if (StringUtils.isBlank(encryptType)) {
			if (isJson) {
				inMessage = WxMaMessage.fromJson(requestBody);
			} else {// xml
				inMessage = WxMaMessage.fromXml(requestBody);
			}
		} else if ("aes".equalsIgnoreCase(encryptType)) {
			if (isJson) {
				inMessage = WxMaMessage.fromEncryptedJson(requestBody, wxService.getWxMaConfig());
			} else {// xml
				inMessage = WxMaMessage.fromEncryptedXml(requestBody, wxService.getWxMaConfig(), timestamp, nonce,
						msgSignature);
			}
		} else {
			throw new RuntimeException("不可识别的加密类型：" + encryptType);
		}
		if (inMessage == null) {
			throw new RuntimeException("未获取到数据");
		}
		logger.info(inMessage.toString());

		WxMaMessageRouter router = new WxMaMessageRouter(wxService);
		router.rule().handler(logHandler).next().rule().async(false).content("模板").handler(templateMsgHandler).end()
				.rule().async(false).content("文本").handler(textHandler).end().rule().async(false).content("图片")
				.handler(picHandler).end().rule().async(false).content("二维码").handler(qrcodeHandler).end();
		return "success";
	}

	private final WxMaMessageHandler templateMsgHandler = (wxMessage, context, service, sessionManager) -> service
			.getMsgService()
			.sendTemplateMsg(WxMaTemplateMessage.builder().templateId("xby0pVzgx6Q2-XORtE6PDv1wKdP79zIMn-AdZvT_n1Q")
					.formId("").data(Lists.newArrayList(new WxMaTemplateData("keyword1", "339208499", "#173177")))
					.toUser(wxMessage.getFromUser()).build());

	private final WxMaMessageHandler logHandler = (wxMessage, context, service, sessionManager) -> {
		System.out.println("收到消息：" + wxMessage.toString());
		service.getMsgService().sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("收到信息为：" + wxMessage.toJson())
				.toUser(wxMessage.getFromUser()).build());
	};

	private final WxMaMessageHandler textHandler = (wxMessage, context, service, sessionManager) -> service
			.getMsgService()
			.sendKefuMsg(WxMaKefuMessage.newTextBuilder().content("回复文本消息").toUser(wxMessage.getFromUser()).build());

	private final WxMaMessageHandler picHandler = (wxMessage, context, service, sessionManager) -> {
		try {
			WxMediaUploadResult uploadResult = service.getMediaService().uploadMedia("image", "png",
					ClassLoader.getSystemResourceAsStream("tmp.png"));
			service.getMsgService().sendKefuMsg(WxMaKefuMessage.newImageBuilder().mediaId(uploadResult.getMediaId())
					.toUser(wxMessage.getFromUser()).build());
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	};

	private final WxMaMessageHandler qrcodeHandler = (wxMessage, context, service, sessionManager) -> {
		try {
			final File file = service.getQrcodeService().createQrcode("123", 430);
			WxMediaUploadResult uploadResult = service.getMediaService().uploadMedia("image", file);
			service.getMsgService().sendKefuMsg(WxMaKefuMessage.newImageBuilder().mediaId(uploadResult.getMediaId())
					.toUser(wxMessage.getFromUser()).build());
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	};
}