package com.investformula.InvestFormula.infra.configuration;

import com.investformula.InvestFormula.application.InvestService;
import com.investformula.InvestFormula.domain.StockFactory;
import com.investformula.InvestFormula.infra.brapi.BrapiInvestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvestConfig {

    @Bean
    public HelloWorldService helloWorldService() {
        return new HelloWorldService();
    }

    @Bean
    public StockFactory stockFactory() {
        return new StockFactory();
    }

    @Bean
    public InvestService investService(BrapiApiConfig brapiApiConfig, BrapiInvestClient brapiInvestClient) {
        return new InvestService(brapiApiConfig, brapiInvestClient, stockFactory());
    }

}
