package com.shuaibi.zaizaisecurity.core;

import com.shuaibi.zaizaisecurity.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author gzp
 * @date 2018/12/19 10:33
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
