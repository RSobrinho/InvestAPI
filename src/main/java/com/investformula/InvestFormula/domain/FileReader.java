package com.investformula.InvestFormula.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileReader.class);
    public static String read(String filePath) {
        try (InputStream is = FileReader.class.getResourceAsStream(filePath)) {
            if (is == null) {
                throw new IllegalArgumentException("filePath not found: " + filePath);
            }
            return new String(is.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.error("An exception occurred when trying to read filePath: {}", filePath, e);
        }
        return "";
    }
}
