package com.investformula.InvestFormula.infra.invest;

import com.investformula.InvestFormula.application.service.InvestService;
import com.investformula.InvestFormula.domain.stock.Stock;
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
