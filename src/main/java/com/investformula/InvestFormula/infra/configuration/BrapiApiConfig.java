package com.investformula.InvestFormula.infra.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BrapiApiConfig implements ApiConfig {

    @Value("${api.key}")
    private String apiKey;

    @Override
    public String getApiKey() {
        return apiKey;
    }

    public Map<String, String> getAuthorizationHeader() {
        return Map.of("Authorization", "Bearer " + apiKey);
    }
}
