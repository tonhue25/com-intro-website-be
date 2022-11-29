package com.altek.intro.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties
@PropertySource("classpath:application.yml")
public class GlobalConfig {
    @Value("${address}")
    private String address;

    @Value("${phone}")
    private String phone;

    @Value("${mail}")
    private String mail;

    @Override
    public String toString() {
        return "GlobalConfig{" +
                "address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}