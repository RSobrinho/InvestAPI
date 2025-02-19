package com.investformula.InvestFormula.infra.openai;

public enum OpenAIRole {
    SYSTEM("system"), USER("user"), DEVELOPER("developer"), ASSISTANT("assistant");
    private final String value;

    OpenAIRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
