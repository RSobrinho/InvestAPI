package com.investformula.InvestFormula.domain;

import com.investformula.InvestFormula.infra.PreConditions;
import jakarta.persistence.*;


@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @Column(name = "stock")
    private String stock;

    @Column(name = "sector")
    private String sector;

    @Column(name = "volume")
    private String volume;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_properties_id", referencedColumnName = "id")
    private StockProperties properties;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_formulas_id", referencedColumnName = "id")
    private StockFormulas formulas;

    @Transient
    private StockFormulaResolver resolver;

    @Transient
    private StockRepository repository;

    protected Stock() {}

    public Stock(String stock, String sector, String volume,
                 StockFormulaResolver resolver, StockRepository repository) {
        this.stock = stock;
        this.sector = sector;
        this.volume = volume;
        this.resolver = resolver;
        this.repository = repository;
    }

    public String name() {
        return stock;
    }

    public void withProperties(StockProperties properties) {
        this.properties = properties;
    }

    public void withFormulas() {
        if(PreConditions.nonNull(properties)) {
            this.formulas = resolver.getFormulasBy(properties);
            return;
        }
        throw new IllegalArgumentException("full properties is null");
    }

    public StockFormulas getFormulas() {
        return this.formulas;
    }


    public void saveOrUpdate() {
        repository.save(this);
    }
}
