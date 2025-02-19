package com.investformula.InvestFormula.domain;

import jakarta.persistence.*;

import java.util.Map;
import java.util.Objects;


@Entity
@Table(name = "stock_formulas")
public final class StockFormulas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = JsonConverter.class)
    @Column(name = "raw_formulas", columnDefinition = "json")
    private Map<String, String> rawFormulas;

    protected StockFormulas() {}

    public StockFormulas(Map<String, String> rawFormulas) {
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
