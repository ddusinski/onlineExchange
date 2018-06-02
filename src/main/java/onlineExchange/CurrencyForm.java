package onlineExchange;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CurrencyForm {
    @NotNull(message = "You should choose the currency")
    private String currencyCode;
    @NotNull
    @Min(value = 1, message = "Should be bigger then 1")
    private int ratesCount;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getRatesCount() {
        return ratesCount;
    }

    public void setRatesCount(int ratesAmount) {
        this.ratesCount = ratesAmount;
    }
}
