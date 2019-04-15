package com.shuaibi.alipay.conf;

import org.springframework.context.annotation.Configuration;

/**
 * @author gzp
 * @date 2018/12/4 16:46
 */
public class AlipayConfig {
	//开发者中心 / 研发服务 / 沙箱环境 / 沙箱应用/ 信息配置/ 必看部分/ APPID（填自己的，下面都是改过的~）
	public static String app_id = "2016092300576764";

	//开发者中心 / 研发服务 / 沙箱环境 / 沙箱应用/ 信息配置/ 必看部分/ 生成公钥时对应的私钥（填自己的，下面都是改过的~）
	public static String private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCbqmDP0qZHDRxnvAyyQ1tK+il1/Rvy/Z6JT8I6acJkCm9etWS4sC7jCWKBgwfQDTXYjcTAI0O6isb8+rZwMK3J46yQb+s77GyU9T2aSQkHQ9+LkR+bK2X1HiAeTFaZO925kfEhRCQmLkruHhb4Ix1Wfvce4FF90D5eP2IzBTMFb7hhI5rnMnB6VexRU4NZMF8hXq6ZllkWL4YW/IilayjdtDz8BzVqz5QpXg61a5/SA1vh6V1fVgJS8QbRm52vEpUHuD25pxvQx8LrnINFXPW58Rv4g0WHP8L2vez2TWhzcJAcc5J4xUAPn7+v5N5XDJnSKnBRb8sbK0MwPNQ7/capAgMBAAECggEBAJnFwUULLj7Z8Ml4toISj8iNPw/TLRzo9QdnxtK1TtZMiJ/iSNE5GdglmS2fJsSbiCmYQlgbffh0xwV5FWfWbpcVVY6JQLM9ZjXv3R7gLw8COX5Bqhs6z8lILSpA9vsAbJO4W58lFSCyckqvZrlQ1tZvLedOaLIEWNf5fSQxaqlemwwQKm9IWIVMjRf1b9Cb5A7BZlF6+IGUq7ISv5Oo3qrm/UXXdbW6WpYd6wBKQ0u8I1DEIa2yH9tOqymJpeN9PDkQ0fvY12akx1QgOQ9TSwGF4MV/X/7WPNwYKBH5Rh494t8QAHyk/hhrOf6ugxccxJQTEMR8ujjNTEuMVuB94EECgYEA3cBJP7NJzCletPNbCU0Q+omY3V6iyswD3XuCv8XNHrLaMOO5MMonWWzmgem8KjXTnWD0XgpSJhWwQcwbkGyWGEyKy9BpSJpLKMy3FumeeY0doqwat5P8Fi1mcnxoRVWX/6fQEYMljDqBRrOpZxtjJRLCDvz+32Ch4hUqXfsjHw0CgYEAs7UqTUkwBniFIj/MIF8+I4bPnqLBBzGN8O2atZlG4NKFvcf3fAKs8RLz9oY6OX9GafEeTVGtrrpPFxRxtTukFB+csMQDPc5w58d2ALmFuVo7x1Om7mj7eJSuuZgFd4zdw4Jvh85D13Z+7n+3zA3G7K9/Q6SUm/0QuNcGPdSVPw0CgYEA0C+ulTdlmV9dotS2K01HG3ADLBGlFG/lURKb9h9mK+QOJMgnHT8tIi+zWVRva9GNErvrsD5QUaEbESrm1ficEcQKqr7HmXoCBhPr8H70YXmy0P5zNtagXNHS1Lkny7BIO6T5GvfdUIG8zPvvlvWHZ07tEdVEE8NNOExWslUM7CECgYEAoECrymO7iemrf1E47J1CQlgTiTlFvO55zyFzVwGqbQ4O6aAKl/Gi2w+1sl8cSO2wkwTLqtNteGT35nL6u0zGEpPmHNOOuG0Bzg7Wseq+zv0iVavJLs07gtnDvglMxsE9eP02C/rqEJfAtVn7QuH+RE5zxJRdWYUghlRpYnXR6XkCgYAtPaBl7Wwdp48U2cRHZm3ZDv8O9jnFn5QFdJlMZibR/couaWdnas6wvwybeHvprGTUfC6BfTeXDE+aS+ym5s+B+sc6sO9XqLYUDcz8JENRtSfWzQXsx76JdaUxWKKpRcUdj6jojwbroO4wjN57xMN4E1JNGGrS1wXZQT+g6WD0vA==";

	//Controller Mapping:得放到服务器上，且使用域名解析 IP
	public static String notify_url = "http://127.0.0.1:8903/alipay/notifyUrl";

	//Controller Mapping:得放到服务器上，且使用域名解析 IP
	public static String return_url = "http://127.0.0.1:8903/alipay/returnUrl";

	//开发者中心 / 研发服务 / 沙箱环境 / 沙箱应用/ 信息配置/ 必看部分/ 支付宝网关（注意沙箱alipaydev，正式则为 alipay）
	public static String url = "https://openapi.alipaydev.com/gateway.do";

	public static String charset = "UTF-8";

	public static String format = "json";

	//开发者中心 / 研发服务 / 沙箱环境 / 沙箱应用/ 信息配置/ 必看部分/ 公钥（填自己的，下面都是改过的~）
	public static String public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtbF11nXBbjwgkR4glK2t05KHc8H+IE3HRcOEmy3auAAzfTRDHN2xIIn1ZetUOdw6naBv4cbcrq1vj2Fq5FFuiSYuMxHFojPnKospHX2xdMjs+06Ilgf7Qf4csttlFMGAF1yMVoKgLgfzgcie6aUAqK0y0k/soXMdYup24AZUhs+aUfqB9FUlgs1NcJVurcEY6DWCtLsraxQ6pmV0MVFHZabBPNfu3vCQKhUw8OHWk2CnuGSKwcqilIa9S/2Q6D3IRl4WioGjWzuLUueWaLuwB17GHj1FyaBveBG2GLd8d2J6TMobbALdW0sxfKm5n/Lw6t+TYmpoozQP73eWh6Nu0QIDAQAB";

	public static String signtype = "RSA2";

}