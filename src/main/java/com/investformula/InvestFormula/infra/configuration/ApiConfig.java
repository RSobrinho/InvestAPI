package com.investformula.InvestFormula.infra.configuration;

import java.util.Map;

public interface ApiConfig {

    String getApiKey();
    Map<String, String> getAuthorizationHeader();
}
