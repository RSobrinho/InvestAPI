package com.investformula.InvestFormula.application;

import com.investformula.InvestFormula.application.command.InvestContentCommand;
import com.investformula.InvestFormula.domain.Stock;
import com.investformula.InvestFormula.domain.StockFactory;
import com.investformula.InvestFormula.domain.interfaces.FullInvestInfo;
import com.investformula.InvestFormula.domain.interfaces.ExternalStockProperties;
import com.investformula.InvestFormula.infra.brapi.BrapiInvestClient;
import com.investformula.InvestFormula.domain.interfaces.GeneralInvestInfo;
import com.investformula.InvestFormula.infra.configuration.ApiConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestService {

    private final ApiConfig apiConfig;
    private final BrapiInvestClient brapiInvestClient;
    private final StockFactory stockFactory;
    private final StockPropertiesFactory stockPropertiesFactory;

    public InvestService(ApiConfig apiConfig, BrapiInvestClient brapiInvestClient,
                         StockFactory stockFactory, StockPropertiesFactory stockPropertiesFactory) {
        this.apiConfig = apiConfig;
        this.brapiInvestClient = brapiInvestClient;
        this.stockFactory = stockFactory;
        this.stockPropertiesFactory = stockPropertiesFactory;
    }

    @Cacheable(value = "stock_command", key = "#command.toString()")
    public List<Stock> getAllInvestContent(InvestContentCommand command) {
        GeneralInvestInfo generalInfo = brapiInvestClient.getGeneralInfo(apiConfig.getAuthorizationHeader(),
                command.limit(), command.type(), command.sector());

        return saveFullPropertiesOnStocks(generalInfo);
    }

    private List<Stock> saveFullPropertiesOnStocks(GeneralInvestInfo generalInvestInfo) {
        List<Stock> stocks = getIncompleteStocks(generalInvestInfo);
        fulfillStocksInfo(stocks);
        return stocks;
    }

    private List<Stock> getIncompleteStocks(GeneralInvestInfo generalInvestInfo) {
        List<Stock> stocks = new ArrayList<>();
        generalInvestInfo.stocks().forEach(stockRequest -> stocks.add(stockFactory.simpleCreate(stockRequest)));
        return stocks;
    }

    private void fulfillStocksInfo(List<Stock> incompleteStocks) {
        FullInvestInfo fullInvestInfo = getStockContent(incompleteStocks);
        incompleteStocks.forEach(stock -> {
            ExternalStockProperties properties = fullInvestInfo.findByTicker(stock.getStock());
            stock.withProperties(stockPropertiesFactory.create(properties));
            stock.withFormulas();
            stock.saveOrUpdate();
        });
    }

    private FullInvestInfo getStockContent(List<Stock> stocks) {
        String stocksName = stocks.stream().map(Stock::getStock).collect(Collectors.joining(","));
        return brapiInvestClient.getByTicker(apiConfig.getAuthorizationHeader(), stocksName);
    }
}
