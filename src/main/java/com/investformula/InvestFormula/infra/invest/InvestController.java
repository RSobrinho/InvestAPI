package com.investformula.InvestFormula.infra.invest;

import com.investformula.InvestFormula.application.invest.InvestService;
import com.investformula.InvestFormula.domain.stock.Stock;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @GetMapping(value = "/export")
    public ResponseEntity<FileSystemResource> export(HttpServletResponse response) {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"data.csv\"");

        try {
            Path filePath = Paths.get("data.csv");
            investService.writeCsv(filePath);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=" + filePath.getFileName().toString())
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(new FileSystemResource(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
