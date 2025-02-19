package com.investformula.InvestFormula.infra.invest;

import com.investformula.InvestFormula.application.InvestService;
import com.investformula.InvestFormula.domain.Stock;
import com.investformula.InvestFormula.infra.configuration.ApiConfig;
import com.investformula.InvestFormula.infra.invest.request.GetAllRequest;
import com.investformula.InvestFormula.infra.invest.request.InvestStocksResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class InvestController {

    private final InvestService investService;

    public InvestController(InvestService investService) {
        this.investService = investService;
    }

    @GetMapping("/quote")
    public ResponseEntity<InvestStocksResponse> getAll(@RequestParam String sector, @RequestParam String limit,
                                                       @RequestParam String type) {
        GetAllRequest getAllRequest = GetAllRequest.from(sector, limit, type);
        List<Stock> stocks = investService.getAllInvestContent(getAllRequest.toCommand());
        return ResponseEntity.of(Optional.of(InvestStocksResponse.from(stocks)));
    }
}
