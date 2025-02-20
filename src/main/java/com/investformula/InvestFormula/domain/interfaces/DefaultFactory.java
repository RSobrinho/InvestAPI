package com.investformula.InvestFormula.domain.interfaces;

public interface DefaultFactory<T, P> {
    public T create(P property);
}
