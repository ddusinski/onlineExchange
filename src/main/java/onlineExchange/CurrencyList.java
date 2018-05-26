package onlineExchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyList {

    @JsonProperty("table")
    private String tableName;
    @JsonProperty("currency")
    private String currencyName;
    @JsonProperty("code")
    private String currencyCode;
    @JsonProperty("rates")
    private ArrayList <CurrencyRate> currencyRate;
    private int counter;

    public int getCounter() {
        return counter;
    }

    public CurrencyRate  getCurrencyRate(int rateNumer) {
        return this.currencyRate.get(rateNumer);
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setCurrencyRate(ArrayList <CurrencyRate>  currencyRate) {
        this.counter=currencyRate.size();
        this.currencyRate = currencyRate;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString()
    {
        return "nazwa waluty: " + this.currencyName + this.currencyRate.toString();
    }
}
