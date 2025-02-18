package com.investformula.InvestFormula.infra.configuration;

import com.investformula.InvestFormula.infra.brapi.BrapiInvestClient;
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
public class FeignConfig {

    private final ObjectFactory<HttpMessageConverters> messageConverters;

    public FeignConfig(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.messageConverters = messageConverters;
    }

    @Bean
    public BrapiInvestClient investClient() {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new SpringEncoder(messageConverters))
                .decoder(new SpringDecoder(messageConverters))
                .contract(new SpringMvcContract())

                .target(BrapiInvestClient.class, "https://brapi.dev/api");
    }
}
