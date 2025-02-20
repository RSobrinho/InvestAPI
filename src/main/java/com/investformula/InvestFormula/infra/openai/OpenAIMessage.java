package com.investformula.InvestFormula.infra.openai;

public record OpenAIMessage(String role, String content) {

    public static OpenAIMessage assistantWith(String content) {
        return new OpenAIMessage(OpenAIRole.ASSISTANT.getValue(), content);
    }

    public static OpenAIMessage userWith(String content) {
        return new OpenAIMessage(OpenAIRole.USER.getValue(), content);
    }

    public static OpenAIMessage developerWith(String content) {
        return new OpenAIMessage(OpenAIRole.DEVELOPER.getValue(), content);
    }
}
