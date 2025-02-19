package com.investformula.InvestFormula.domain;

import com.investformula.InvestFormula.domain.interfaces.StockProperties;
import com.investformula.InvestFormula.infra.configuration.ApiConfig;
import com.investformula.InvestFormula.infra.openai.OpenAIClient;
import com.investformula.InvestFormula.infra.openai.OpenAIMessage;
import com.investformula.InvestFormula.infra.openai.PromptRequest;
import com.investformula.InvestFormula.infra.openai.PromptResponse;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenAIStockFormulaResolver implements StockFormulaResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenAIStockFormulaResolver.class);
    private final ApiConfig apiConfig;
    private final OpenAIClient client;
    private static final String MODEL = "gpt-4o-mini"; // o3-mini
    private static final String USER_PROMPT_PATH = "/prompts/user_prompt.txt";
    private static final String DEVELOPER_PROMPT_PATH = "/prompts/developer_prompt.txt";
    private static final String ASSISTANT_PROMPT = "";

    public OpenAIStockFormulaResolver(ApiConfig apiConfig, OpenAIClient client) {
        this.apiConfig = apiConfig;
        this.client = client;
    }

    public StockFormulas getFormulasBy(StockProperties properties) {
        List<OpenAIMessage> messages = getOpenAIMessages(properties);
        PromptResponse response = client.executePrompt(getHeaders(), new PromptRequest(MODEL, messages));
        String resultMessage = response.choices().get(0).message().content();
        return new StockFormulas(convertInMap(resultMessage));
    }

    private Map<String, String> convertInMap(String message) {
        Map<String, String> formulasValues = new HashMap<>();

        if (message == null || message.isEmpty()) {
            return formulasValues;
        }

        String[] pairs = message.split("\\|");

        for (String pair : pairs) {
            String[] keyValue = pair.split(",");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();
                formulasValues.put(key, value);
            }
        }

        return formulasValues;
    }

    @NotNull
    private static List<OpenAIMessage> getOpenAIMessages(StockProperties properties) {
        String completeUserPrompt = FileReader.read(USER_PROMPT_PATH).replace("PUT_JSON_HERE", properties.toString());
        return List.of(OpenAIMessage.developerWith(FileReader.read(DEVELOPER_PROMPT_PATH)),
                OpenAIMessage.userWith(completeUserPrompt));
    }

    @NotNull
    private Map<String, String> getHeaders() {
        return Map.of("Content-Type", "application/json", "Authorization", "Bearer " + apiConfig.getApiKey());
    }

}
