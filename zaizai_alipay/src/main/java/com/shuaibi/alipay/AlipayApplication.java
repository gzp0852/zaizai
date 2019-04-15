package com.shuaibi.alipay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class AlipayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlipayApplication.class, args);
	}
//	com.alipay.api.DefaultAlipayClient.DefaultAlipayClient (String serverUrl, String appId, String privateKey,String format, String charset, String alipayPulicKey, String signType );
//	AlipayClient alipayClient = new
//			DefaultAlipayClient ("https://openapi.alipaydev.com/gateway.do","2016092300576764","请复制上一步中生成的密钥中的商户应用私钥","json","utf-8",
//			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtbF11nXBbjwgkR4glK2t05KHc8H+IE3HRcOEmy3auAAzfTRDHN2xIIn1ZetUOdw6naBv4cbcrq1vj2Fq5FFuiSYuMxHFojPnKospHX2xdMjs+06Ilgf7Qf4csttlFMGAF1yMVoKgLgfzgcie6aUAqK0y0k/soXMdYup24AZUhs+aUfqB9FUlgs1NcJVurcEY6DWCtLsraxQ6pmV0MVFHZabBPNfu3vCQKhUw8OHWk2CnuGSKwcqilIa9S/2Q6D3IRl4WioGjWzuLUueWaLuwB17GHj1FyaBveBG2GLd8d2J6TMobbALdW0sxfKm5n/Lw6t+TYmpoozQP73eWh6Nu0QIDAQAB","RSA2" );
	/**
	 * 跨域过滤器
	 * @return
	 */
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);
	}

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		return corsConfiguration;
	}
}
