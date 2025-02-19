package com.investformula.InvestFormula.infra.openai;

public record OpenAIMessage(String role, String content) {

    // Provide examples to the model for how it should respond to the current request.
    // it's the return message of the chat
    public static OpenAIMessage assistantWith(String content) {
        return new OpenAIMessage(OpenAIRole.ASSISTANT.getValue(), content);
    }

    // Pass your end-user's message (or effective command) to the model.
    public static OpenAIMessage userWith(String content) {
        return new OpenAIMessage(OpenAIRole.USER.getValue(), content);
    }

    // Describe how the model should generally behave and respond.
    public static OpenAIMessage developerWith(String content) {
        return new OpenAIMessage(OpenAIRole.DEVELOPER.getValue(), content);
    }
}
