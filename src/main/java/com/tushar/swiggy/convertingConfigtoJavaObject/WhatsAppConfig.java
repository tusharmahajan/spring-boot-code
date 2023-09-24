package com.tushar.swiggy.convertingConfigtoJavaObject;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "wa")
@ConfigurationPropertiesScan
@Component
@Validated
public class WhatsAppConfig {

    private HostName hostName;
    private String token;

    public HostName getHostName() {
        return hostName;
    }

    public String getToken() {
        return token;
    }

    public void setHostName(HostName hostName) {
        this.hostName = hostName;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
