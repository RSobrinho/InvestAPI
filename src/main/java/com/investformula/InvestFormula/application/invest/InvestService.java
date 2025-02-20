package com.investformula.InvestFormula.application.invest;

import com.investformula.InvestFormula.application.invest.command.InvestContentCommand;
import com.investformula.InvestFormula.application.factory.StockFactory;
import com.investformula.InvestFormula.application.factory.StockPropertiesFactory;
import com.investformula.InvestFormula.domain.interfaces.StockRepository;
import com.investformula.InvestFormula.domain.stock.Stock;
import com.investformula.InvestFormula.domain.interfaces.FullInvestInfo;
import com.investformula.InvestFormula.domain.interfaces.StockPropertiesRequest;
import com.investformula.InvestFormula.domain.stock.StockFormulas;
import com.investformula.InvestFormula.domain.stock.StockProperties;
import com.investformula.InvestFormula.domain.util.PreConditions;
import com.investformula.InvestFormula.infra.brapi.BrapiInvestClient;
import com.investformula.InvestFormula.domain.interfaces.GeneralInvestInfo;
import com.investformula.InvestFormula.infra.configuration.ApiConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestService {

    private final ApiConfig apiConfig;
    private final BrapiInvestClient brapiInvestClient;
    private final StockFactory stockFactory;
    private final StockPropertiesFactory stockPropertiesFactory;
    private final StockRepository stockRepository;

    public InvestService(ApiConfig apiConfig, BrapiInvestClient brapiInvestClient, StockFactory stockFactory,
                         StockPropertiesFactory stockPropertiesFactory, StockRepository stockRepository) {
        this.apiConfig = apiConfig;
        this.brapiInvestClient = brapiInvestClient;
        this.stockFactory = stockFactory;
        this.stockPropertiesFactory = stockPropertiesFactory;
        this.stockRepository = stockRepository;
    }

    @Cacheable(value = "stock_command", key = "#command.toString()")
    public List<Stock> getAllInvestContent(InvestContentCommand command) {
        GeneralInvestInfo generalInfo = brapiInvestClient.getGeneralInfo(apiConfig.getAuthorizationHeader(),
                command.limit(), command.type(), command.sector());

        return saveFullPropertiesOnStocks(generalInfo);
    }

    public Path writeCsv(Path filePath) throws IOException {
        List<Stock> stocks = stockRepository.findAll();
        String csvData = createStocksCsv(stocks);
        Files.writeString(filePath, csvData);
        System.out.println("CSV file created at: " + filePath.toAbsolutePath());
        return filePath;
    }

    private List<Stock> saveFullPropertiesOnStocks(GeneralInvestInfo generalInvestInfo) {
        List<Stock> stocks = getIncompleteStocks(generalInvestInfo);
        fulfillStocksInfo(stocks);
        return stocks;
    }

    private List<Stock> getIncompleteStocks(GeneralInvestInfo generalInvestInfo) {
        List<Stock> stocks = new ArrayList<>();
        generalInvestInfo.stocks().forEach(stockRequest -> stocks.add(stockFactory.create(stockRequest)));
        return stocks;
    }

    private void fulfillStocksInfo(List<Stock> incompleteStocks) {
        FullInvestInfo fullInvestInfo = getStockContent(incompleteStocks);
        incompleteStocks.forEach(stock -> {
            StockPropertiesRequest properties = fullInvestInfo.findByTicker(stock.getStock());
            stock.withProperties(stockPropertiesFactory.create(properties));
            stock.withFormulas();
            stock.saveOrUpdate();
        });
    }

    private FullInvestInfo getStockContent(List<Stock> stocks) {
        String stocksName = stocks.stream().map(Stock::getStock).collect(Collectors.joining(","));
        return brapiInvestClient.getByTicker(apiConfig.getAuthorizationHeader(), stocksName);
    }

    private String createStocksCsv(List<Stock> stocks) {
        CsvResponseBuilder csvBuilder = CsvResponseBuilder.create()
                .withHeader(List.of("ticker/stock", "sector", "volume", "regular_market_price",
                        "earnings_per_share", "price_earnings", "fifty_two_week_high", "fifty_two_week_low",
                        "regular_market_day_high", "regular_market_day_low", "regular_market_change",
                        "regular_market_previous_close", "regular_market_change_percent", "raw_formulas"));

        stocks.forEach(stock -> {
            StockProperties properties = stock.getProperties();
            StockFormulas formulas = stock.getFormulas();
            csvBuilder.withLine(List.of(
                    PreConditions.blankWhenNull(stock.getStock()),
                    PreConditions.blankWhenNull(stock.getSector()),
                    PreConditions.blankWhenNull(stock.getVolume()),
                    PreConditions.blankWhenNull(properties.regularMarketPrice()),
                    PreConditions.blankWhenNull(properties.earningsPerShare()),
                    PreConditions.blankWhenNull(properties.priceEarnings()),
                    PreConditions.blankWhenNull(properties.fiftyTwoWeekHigh()),
                    PreConditions.blankWhenNull(properties.fiftyTwoWeekLow()),
                    PreConditions.blankWhenNull(properties.regularMarketDayHigh()),
                    PreConditions.blankWhenNull(properties.regularMarketDayLow()),
                    PreConditions.blankWhenNull(properties.regularMarketChange()),
                    PreConditions.blankWhenNull(properties.regularMarketPreviousClose()),
                    PreConditions.blankWhenNull(properties.regularMarketChangePercent()),
                    PreConditions.blankWhenNull(formulas.stringifyRawFormulas())));
        });
        return csvBuilder.build();
    }
}
