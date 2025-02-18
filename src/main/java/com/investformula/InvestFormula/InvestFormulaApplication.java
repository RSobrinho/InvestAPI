package com.investformula.InvestFormula;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InvestFormulaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestFormulaApplication.class, args);
	}

}
