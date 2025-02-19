package com.investformula.InvestFormula.infra.brapi;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface BrapiInvestClient {

    @GetMapping("/quote/{ticker}")
    BrapiFullInvestInfo getByTicker(@RequestHeader Map<String, String> headers, @PathVariable String ticker);

    @GetMapping("/quote/list")
    BrapiGeneralInvestInfo getGeneralInfo(@RequestHeader Map<String, String> headers,
                                          @RequestParam(name = "limit") String limit,
                                          @RequestParam(name = "type") String type,
                                          @RequestParam(name = "sector") String sector);
}
