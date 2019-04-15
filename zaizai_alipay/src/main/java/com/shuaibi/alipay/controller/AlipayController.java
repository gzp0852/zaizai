package com.shuaibi.alipay.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.shuaibi.alipay.conf.AlipayConfig;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@RestController
@RequestMapping("/alipay")
public class AlipayController {

	// 获取配置文件的信息
	String app_id = AlipayConfig.app_id;
	String private_key = AlipayConfig.private_key;
	String notify_url = AlipayConfig.notify_url;
	String return_url = AlipayConfig.return_url;
	String url = AlipayConfig.url;
	String charset = AlipayConfig.charset;
	String format = AlipayConfig.format;
	String public_key = AlipayConfig.public_key;
	String signtype = AlipayConfig.signtype;


	/**
	 * 支付请求
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@GetMapping("/pay")
	public void pay(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 模拟从前台传来的数据
		String orderNo = LocalDateTime.now().toString(); // 生成订单号
		String totalAmount = "0.1"; // 支付总金额
		String subject = "ITAEMBook"; // 订单名称
		String body = "reading"; // 商品描述

		// 封装请求客户端
		AlipayClient client = new DefaultAlipayClient(url, app_id, private_key, format, charset, public_key, signtype);
				//new DefaultAlipayClient ("https://openapi.alipaydev.com/gateway.do","2016092300576764","请复制上一步中生成的密钥中的商户应用私钥","json","utf-8",
				//		"","RSA2" );
		// 支付请求
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(return_url);
		alipayRequest.setNotifyUrl(notify_url);
		AlipayTradePayModel model = new AlipayTradePayModel();
		model.setProductCode("FAST_INSTANT_TRADE_PAY"); // 设置销售产品码
		model.setOutTradeNo(orderNo); // 设置订单号
		model.setSubject(subject); // 订单名称
		model.setTotalAmount(totalAmount); // 支付总金额
		model.setBody(body); // 设置商品描述
		alipayRequest.setBizModel(model);

		String form = client.pageExecute(alipayRequest).getBody(); // 生成表单

		response.setContentType("text/html;charset=" + charset);
		response.getWriter().write(form); // 直接将完整的表单html输出到页面
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * 同步跳转
	 * 
	 * @param request
	 * @throws Exception
	 */
	@GetMapping("/returnUrl")
	public String returnUrl(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();

		// 获取支付宝GET过来反馈信息（官方固定代码）
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		boolean signVerified = AlipaySignature.rsaCheckV1(params, public_key, charset, signtype); // 调用SDK验证签名

		// 返回界面
		if (signVerified) {
			System.out.println("前往支付成功页面");
			mav.setViewName("successReturn");
		} else {
			System.out.println("前往支付失败页面");
			mav.setViewName("failReturn");
		}
//		return mav;

		return  "<html>\n" +
				"<head>\n" +
				"<script type=\"text/javascript\"> function load() { window.close(); } </script>\n" +
				"</head>\n" +
				"<body onload=\"" +
				"load()\"> </body>\n" +
				"</html>";

	}

	/**
	 * 支付宝服务器异步通知
	 * 
	 * @param request
	 * @throws Exception
	 */
	@PostMapping("/notifyUrl")
	public void notifyUrl(HttpServletRequest request) throws Exception {
		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}

		boolean signVerified = AlipaySignature.rsaCheckV1(params, public_key, charset, signtype); // 调用SDK验证签名

		if (signVerified) { // 验证成功 更新订单信息
			System.out.println("异步通知成功");
			// 商户订单号
			String out_trade_no = request.getParameter("out_trade_no");
			// 交易状态
			String trade_status = request.getParameter("trade_status");
			// 修改数据库
		} else {
			System.out.println("异步通知失败");
		}
	}

}
