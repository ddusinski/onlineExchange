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
    private ArrayList<CurrencyRate> currencyRates;
    private int counter;

    public int getCounter() {
        return counter;
    }

    public CurrencyRate getCurrencyRate(int rateNumer) {
        return this.currencyRates.get(rateNumer);
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

    public void setCurrencyRates(ArrayList<CurrencyRate> currencyRates) {
        this.counter = currencyRates.size();
        this.currencyRates = currencyRates;
    }

    public ArrayList<CurrencyRate> getCurrencyRates() {
        return currencyRates;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public double[] getGraphValues() {
        double[] values = new double[this.currencyRates.size()];

        for (int i = 0; i < this.currencyRates.size(); i++) {
            values[i] = getCurrencyRates().get(i).getCurrencyMidRate();
        }
        return values;
    }

    public String[] getGraphNames() {
        String[] names = new String[this.currencyRates.size()];

        for (int i = 0; i < this.currencyRates.size(); i++) {
            names[i] = getCurrencyRates().get(i).getEffectiveDate();
        }
        return names;
    }

    @Override
    public String toString() {
        return "nazwa waluty: " + this.currencyName + this.currencyRates.toString();
    }
}
