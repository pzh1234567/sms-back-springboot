package com.example.smsbackspringboot.demos.common;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author
 * @Date Created in  2023/1/13 15:06
 * @DESCRIPTION:
 * @Version V1.0
 */

@Data
@Component
//读取yml文件中alipay 开头的配置
@ConfigurationProperties(prefix = "alipay")
public class AliPayConfig {
    private String appId;
    private String appPrivateKey;
    private String alipayPublicKey;
    //异步通知回调地址（可选）
    private String notifyUrl;
    //支付成功后的回调地址
    private String returnUrl;
}
