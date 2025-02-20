package com.investformula.InvestFormula.application.invest;

import com.investformula.InvestFormula.domain.util.PreConditions;

import java.util.List;
import java.util.stream.Collectors;

public class CsvResponseBuilder {

    private String concatenatedCsv = "";
    private Integer headerLength = 0;

    private CsvResponseBuilder() {}

    public static CsvResponseBuilder create() {
        return new CsvResponseBuilder();
    }

    public CsvResponseBuilder withHeader(List<String> headers) {
        if (!this.concatenatedCsv.isBlank()) {
            throw new IllegalArgumentException("Headers cannot be created with existent text on csv string");
        }

        this.headerLength = headers.size();
        this.concatenatedCsv += String.join(";", headers);
        this.concatenatedCsv += "\n";
        return this;
    }

    public CsvResponseBuilder withLine(List<String> line) {
        if(this.concatenatedCsv.isBlank() || headerLength == 0) {
            throw new IllegalArgumentException("Line cannot be created without headers at first");
        }

        if(headerLength != line.size()) {
            throw new IllegalArgumentException("Line needs to have same length of headers");
        }

        this.concatenatedCsv += String.join(";", line);
        this.concatenatedCsv += "\n";
        return this;
    }

    public String build() {
        return this.concatenatedCsv;
    }
}
