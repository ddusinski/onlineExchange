package onlineExchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class CurrencyRate {

    @JsonProperty("no")
    private String rateNumber;
    @JsonProperty("effectiveDate")
    private String EffectiveDate;
    @JsonProperty("mid")
    private double currencyMidRate;

    public double getCurrencyMidRate() {
        return currencyMidRate;
    }

    public String getEffectiveDate() {
        return EffectiveDate;
    }

    public String getRateNumber() {
        return rateNumber;
    }

    public void setCurrencyMidRate(double currencyMidRate) {
        this.currencyMidRate = currencyMidRate;
    }

    public void setEffectiveDate(String effectiveDate) {
        EffectiveDate = effectiveDate;
    }

    public void setRateNumber(String rateNumber) {
        this.rateNumber = rateNumber;
    }

    @Override
    public String toString() {
        return " wartość waluty: "+this.currencyMidRate;
    }
}
