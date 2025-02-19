package com.investformula.InvestFormula.infra.configuration;

import com.investformula.InvestFormula.application.InvestService;
import com.investformula.InvestFormula.domain.OpenAIStockFormulaResolver;
import com.investformula.InvestFormula.domain.StockFactory;
import com.investformula.InvestFormula.infra.brapi.BrapiInvestClient;
import com.investformula.InvestFormula.infra.openai.OpenAIClient;
import feign.Feign;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvestConfig {

    private final ObjectFactory<HttpMessageConverters> messageConverters;


    public InvestConfig(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.messageConverters = messageConverters;
    }

    @Bean
    public BrapiInvestClient brapiInvestClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new SpringEncoder(messageConverters))
                .decoder(new SpringDecoder(messageConverters))
                .contract(new SpringMvcContract())
                .target(BrapiInvestClient.class, "https://brapi.dev/api");
    }

    @Bean
    public OpenAIClient openAIClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new SpringEncoder(messageConverters))
                .decoder(new SpringDecoder(messageConverters))
                .contract(new SpringMvcContract())
                .target(OpenAIClient.class, "https://api.openai.com");
    }

    @Bean
    public BrapiApiConfig brapiApiConfig() {
        return new BrapiApiConfig();
    }

    @Bean
    public OpenAIApiConfig openAIApiConfig() {
        return new OpenAIApiConfig();
    }

    @Bean
    public OpenAIStockFormulaResolver openAIStockFormulaResolver() {
        return new OpenAIStockFormulaResolver(openAIApiConfig(), openAIClient());
    }

    @Bean
    public StockFactory stockFactory() {
        return new StockFactory(openAIStockFormulaResolver());
    }

    @Bean
    public InvestService investService() {
        return new InvestService(brapiApiConfig(), brapiInvestClient(), stockFactory());
    }

}
