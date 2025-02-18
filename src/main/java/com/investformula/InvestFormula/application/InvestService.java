package com.investformula.InvestFormula.application;

import com.investformula.InvestFormula.application.command.InvestContentCommand;
import com.investformula.InvestFormula.domain.Stock;
import com.investformula.InvestFormula.domain.StockFactory;
import com.investformula.InvestFormula.infra.brapi.BrapiInvestClient;
import com.investformula.InvestFormula.domain.interfaces.GeneralInvestInfo;
import com.investformula.InvestFormula.infra.configuration.ApiConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestService {

    private final ApiConfig apiConfig;
    private final BrapiInvestClient brapiInvestClient;
    private final StockFactory stockFactory;


    public InvestService(ApiConfig apiConfig, BrapiInvestClient brapiInvestClient, StockFactory stockFactory) {
        this.apiConfig = apiConfig;
        this.brapiInvestClient = brapiInvestClient;
        this.stockFactory = stockFactory;
    }

    public void getStockContent(List<Stock> stocks) {
        String stocksName = stocks.stream().map(Stock::name).collect(Collectors.joining(","));
        brapiInvestClient.getByTicker(apiConfig.getAuthorizationHeader(), stocksName);
    }

    public List<Stock> getAllInvestContent(InvestContentCommand command) {
        GeneralInvestInfo general = brapiInvestClient.getGeneralInfo(apiConfig.getAuthorizationHeader(), command.limit(), command.type(), command.sector());
        List<Stock> stocks = new ArrayList<>();
        general.stocks().forEach(stockRequest -> stocks.add(stockFactory.simpleCreate(stockRequest)));

        return stocks;
    }
}
