package com.investformula.InvestFormula.domain.stock;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.investformula.InvestFormula.domain.util.JsonConverter;
import jakarta.persistence.*;

import java.util.Map;
import java.util.Objects;


@Entity
@Table(name = "stock_formulas")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class StockFormulas {

    @Id
    @Column(name = "stock")
    private String stock;

    @Convert(converter = JsonConverter.class)
    @Column(name = "raw_formulas", columnDefinition = "json")
    private Map<String, String> rawFormulas;

    protected StockFormulas() {}

    public StockFormulas(String stock, Map<String, String> rawFormulas) {
        this.stock = stock;
        this.rawFormulas = rawFormulas;
    }

    public Map<String, String> rawFormulas() {
        return rawFormulas;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (StockFormulas) obj;
        return Objects.equals(this.rawFormulas, that.rawFormulas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawFormulas);
    }

    @Override
    public String toString() {
        return "StockFormulas[" +
                "rawFormulas=" + rawFormulas + ']';
    }

}
