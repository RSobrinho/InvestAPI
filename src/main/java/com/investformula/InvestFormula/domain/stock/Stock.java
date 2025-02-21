package com.investformula.InvestFormula.domain.stock;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.investformula.InvestFormula.domain.interfaces.StockFormulaResolver;
import com.investformula.InvestFormula.domain.interfaces.StockRepository;
import com.investformula.InvestFormula.domain.util.PreConditions;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "stocks")
@JsonIgnoreProperties({"resolver", "repository"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Stock implements Serializable {

    @Id
    @Column(name = "stock")
    private String stock;

    @Column(name = "sector")
    private String sector;

    @Column(name = "volume")
    private String volume;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private StockProperties properties;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private StockFormulas formulas;

    @Transient
    @JsonIgnore
    private transient StockFormulaResolver resolver;

    @Transient
    @JsonIgnore
    private transient StockRepository repository;

    protected Stock() {}

    public Stock(String stock, String sector, String volume,
                 StockFormulaResolver resolver, StockRepository repository) {
        this.stock = stock;
        this.sector = sector;
        this.volume = volume;
        this.resolver = resolver;
        this.repository = repository;
    }

    public String getStock() {
        return stock;
    }

    public String getSector() {
        return sector;
    }

    public String getVolume() {
        return volume;
    }

    public StockProperties getProperties() {
        return this.properties;
    }

    public StockFormulas getFormulas() {
        return this.formulas;
    }

    public void withProperties(StockProperties properties) {
        this.properties = properties;
    }

    public void withFormulas() {
        if (PreConditions.nonNull(properties)) {
            this.formulas = resolver.getFormulasBy(properties);
            return;
        }
        throw new IllegalArgumentException("full properties is null");
    }

    public void saveOrUpdate() {
        repository.save(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Stock) obj;
        return Objects.equals(this.stock, that.stock) &&
                Objects.equals(this.sector, that.sector) &&
                Objects.equals(this.volume, that.volume) &&
                Objects.equals(this.properties, that.properties) &&
                Objects.equals(this.formulas, that.formulas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stock, sector, volume, properties, formulas);
    }

    @Override
    public String toString() {
        return "Stock[" +
                "stock=" + stock + ", " +
                "sector=" + sector + ", " +
                "volume=" + volume + ", " +
                "properties=" + properties + ", " +
                "formulas=" + formulas + ']';
    }
}
