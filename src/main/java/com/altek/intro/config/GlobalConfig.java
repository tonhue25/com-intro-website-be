package com.altek.intro.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties
@Data
@PropertySource("classpath:application.yml")
public class GlobalConfig {

    @Value("${map}")
    private String map;

    @Value("${address}")
    private String address;

    @Value("${phone}")
    private String phone;

    @Value("${mail}")
    private String mail;

}