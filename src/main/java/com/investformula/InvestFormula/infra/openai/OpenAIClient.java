package com.investformula.InvestFormula.infra.openai;

import feign.Headers;
import feign.RequestLine;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

public interface OpenAIClient {
    @PostMapping("/v1/chat/completions")
    PromptResponse executePrompt(@RequestHeader Map<String, String> headers, @RequestBody PromptRequest request);
}