package com.investformula.InvestFormula.infra.openai;

public record Choice(OpenAIMessage message, String finishReason, int index) {
}
