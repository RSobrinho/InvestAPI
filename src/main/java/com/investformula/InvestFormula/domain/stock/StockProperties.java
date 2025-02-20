package com.investformula.InvestFormula.domain.stock;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "stocks_properties")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class StockProperties {

    @Id
    @Column(name = "stock")
    private String stock;

    @Column(name = "regular_market_price")
    private String regularMarketPrice;

    @Column(name = "earnings_per_share")
    private String earningsPerShare;

    @Column(name = "price_earnings")
    private String priceEarnings;

    @Column(name = "fifty_two_week_high")
    private String fiftyTwoWeekHigh;

    @Column(name = "fifty_two_week_low")
    private String fiftyTwoWeekLow;

    @Column(name = "regular_market_day_high")
    private String regularMarketDayHigh;

    @Column(name = "regular_market_day_low")
    private String regularMarketDayLow;

    @Column(name = "regular_market_change")
    private String regularMarketChange;

    @Column(name = "regular_market_previous_close")
    private String regularMarketPreviousClose;

    @Column(name = "regular_market_changePercent")
    private String regularMarketChangePercent;

    protected StockProperties() {}

    public StockProperties(String stock, String regularMarketPrice, String earningsPerShare, String priceEarnings,
                           String fiftyTwoWeekHigh, String fiftyTwoWeekLow, String regularMarketDayHigh,
                           String regularMarketDayLow, String regularMarketChange,
                           String regularMarketPreviousClose,
                           String regularMarketChangePercent) {
        this.stock = stock;
        this.regularMarketPrice = regularMarketPrice;
        this.earningsPerShare = earningsPerShare;
        this.priceEarnings = priceEarnings;
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
        this.regularMarketDayHigh = regularMarketDayHigh;
        this.regularMarketDayLow = regularMarketDayLow;
        this.regularMarketChange = regularMarketChange;
        this.regularMarketPreviousClose = regularMarketPreviousClose;
        this.regularMarketChangePercent = regularMarketChangePercent;
    }

    public String stock() {
        return stock;
    }

    public String regularMarketPrice() {
        return regularMarketPrice;
    }

    public String earningsPerShare() {
        return earningsPerShare;
    }

    public String priceEarnings() {
        return priceEarnings;
    }

    public String fiftyTwoWeekHigh() {
        return fiftyTwoWeekHigh;
    }

    public String fiftyTwoWeekLow() {
        return fiftyTwoWeekLow;
    }

    public String regularMarketDayHigh() {
        return regularMarketDayHigh;
    }

    public String regularMarketDayLow() {
        return regularMarketDayLow;
    }

    public String regularMarketChange() {
        return regularMarketChange;
    }


    public String regularMarketPreviousClose() {
        return regularMarketPreviousClose;
    }

    public String regularMarketChangePercent() {
        return regularMarketChangePercent;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (StockProperties) obj;
        return Objects.equals(this.stock, that.stock) &&
                Objects.equals(this.regularMarketPrice, that.regularMarketPrice) &&
                Objects.equals(this.earningsPerShare, that.earningsPerShare) &&
                Objects.equals(this.priceEarnings, that.priceEarnings) &&
                Objects.equals(this.fiftyTwoWeekHigh, that.fiftyTwoWeekHigh) &&
                Objects.equals(this.fiftyTwoWeekLow, that.fiftyTwoWeekLow) &&
                Objects.equals(this.regularMarketDayHigh, that.regularMarketDayHigh) &&
                Objects.equals(this.regularMarketDayLow, that.regularMarketDayLow) &&
                Objects.equals(this.regularMarketChange, that.regularMarketChange) &&
                Objects.equals(this.regularMarketPreviousClose, that.regularMarketPreviousClose) &&
                Objects.equals(this.regularMarketChangePercent, that.regularMarketChangePercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stock, regularMarketPrice, earningsPerShare, priceEarnings, fiftyTwoWeekHigh, fiftyTwoWeekLow, regularMarketDayHigh, regularMarketDayLow, regularMarketChange, regularMarketPreviousClose, regularMarketChangePercent);
    }

    @Override
    public String toString() {
        return "StockProperties[" +
                "stock=" + stock + ", " +
                "regularMarketPrice=" + regularMarketPrice + ", " +
                "earningsPerShare=" + earningsPerShare + ", " +
                "priceEarnings=" + priceEarnings + ", " +
                "fiftyTwoWeekHigh=" + fiftyTwoWeekHigh + ", " +
                "fiftyTwoWeekLow=" + fiftyTwoWeekLow + ", " +
                "regularMarketDayHigh=" + regularMarketDayHigh + ", " +
                "regularMarketDayLow=" + regularMarketDayLow + ", " +
                "regularMarketChange=" + regularMarketChange + ", " +
                "regularMarketPreviousClose=" + regularMarketPreviousClose + ", " +
                "regularMarketChangePercent=" + regularMarketChangePercent + ']';
    }

}
