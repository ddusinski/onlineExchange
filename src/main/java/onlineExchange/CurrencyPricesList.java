package onlineExchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyPricesList implements DataResponse {

    @JsonProperty("table")
    private String tableName;
    @JsonProperty("currency")
    private String currencyName;
    @JsonProperty("code")
    private String currencyCode;
    @JsonProperty("rates")
    private List<CurrencyRate> currencyRates;

    public CurrencyPricesList() {
        this.currencyRates = new ArrayList<>();
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

    public void setCurrencyRates(List<CurrencyRate> currencyRates) {
        this.currencyRates = currencyRates;
    }

    public List<CurrencyRate> getCurrencyRates() {
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

    public String[] getGraphDates() {
        String[] names = new String[this.currencyRates.size()];

        for (int i = 0; i < this.currencyRates.size(); i++) {
            names[i] = getCurrencyRates().get(i).getEffectiveDate();
        }
        return names;
    }

    public void addotherCurrencyPrices(CurrencyPricesList secondCurrencyList) {
        this.currencyRates.addAll(secondCurrencyList.getCurrencyRates());

    }


}
