package com.investformula.InvestFormula.application.invest;

import com.investformula.InvestFormula.application.factory.StockFactory;
import com.investformula.InvestFormula.application.factory.StockPropertiesFactory;
import com.investformula.InvestFormula.application.invest.command.InvestContentCommand;
import com.investformula.InvestFormula.domain.interfaces.StockFormulaResolver;
import com.investformula.InvestFormula.domain.interfaces.StockPropertiesRequest;
import com.investformula.InvestFormula.domain.interfaces.StockRepository;
import com.investformula.InvestFormula.domain.stock.Stock;
import com.investformula.InvestFormula.domain.stock.StockFormulas;
import com.investformula.InvestFormula.domain.stock.StockProperties;
import com.investformula.InvestFormula.infra.brapi.*;
import com.investformula.InvestFormula.infra.configuration.ApiConfig;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class InvestServiceTest {


    @Test
    public void shouldGetAllInvestContent() {
        InvestContentCommand command = new InvestContentCommand("finance", "0", "stock");
        BrapiStock brapiStock = new BrapiStock("ABCD4", "any", "10", "10",
                "10", "url://any", "finance", "stock");
        BrapiGeneralInvestInfo brapiGeneralInvestInfo =
                new BrapiGeneralInvestInfo(List.of(), List.of(brapiStock), List.of(), List.of());
        Stock stock = new Stock("ABCD4", "finance", "10", stockFormulaResolver, stockRepository);
        StockProperties stockProperties = new StockProperties("ABCD4", "10",
                "10", "10", "10", "10", "10",
                "10", "10", "10", "10");
        StockFormulas stockFormulas = new StockFormulas("ABCD4", Map.of("aFormula", "10"));
        BrapiStockPropertiesRequest brapiStockPropertiesRequest = new BrapiStockPropertiesRequest(
                "", "", "", "", "",
                "", "", "", "",
                "", "", "", "",
                "", "", "", "ABCD4", "",
                "", "");
        BrapiFullInvestInfo brapiFullInvestInfo = new BrapiFullInvestInfo(List.of(brapiStockPropertiesRequest),
                "", "");
        when(brapiInvestClient.getGeneralInfo(anyMap(), any(), any(), any()))
                .thenReturn(brapiGeneralInvestInfo);
        when(brapiInvestClient.getByTicker(anyMap(), anyString())).thenReturn(brapiFullInvestInfo);
        when(stockFactory.create(brapiGeneralInvestInfo.stocks().getFirst())).thenReturn(stock);
        when(stockPropertiesFactory.create(any(StockPropertiesRequest.class))).thenReturn(stockProperties);
        when(stockFormulaResolver.getFormulasBy(stockProperties)).thenReturn(stockFormulas);
        stock.withProperties(stockProperties);
        stock.withFormulas();
        List<Stock> expected = List.of(stock);

        List<Stock> result = service.getAllInvestContent(command);

        assertThat(result, equalTo(expected));
    }

    private final ApiConfig apiConfig = mock(ApiConfig.class);
    private final BrapiInvestClient brapiInvestClient = mock(BrapiInvestClient.class);
    private final StockFactory stockFactory = mock(StockFactory.class);
    private final StockPropertiesFactory stockPropertiesFactory = mock(StockPropertiesFactory.class);
    private final StockRepository stockRepository = mock(StockRepository.class);
    private final StockFormulaResolver stockFormulaResolver = mock(StockFormulaResolver.class);
    private final InvestService service = new InvestService(apiConfig, brapiInvestClient,
            stockFactory, stockPropertiesFactory, stockRepository);

}