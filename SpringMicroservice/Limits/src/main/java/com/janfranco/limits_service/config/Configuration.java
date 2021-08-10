package com.janfranco.limits_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("limits")
@Component
@Getter
@Setter
public class Configuration {

    private int minimum;
    private int maximum;
}
