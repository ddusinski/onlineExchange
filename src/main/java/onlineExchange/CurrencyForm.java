package onlineExchange;

import javax.validation.constraints.NotNull;

public class CurrencyForm {
    @NotNull
    private String currencyCode;
    @NotNull
    private int ratesCount;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getratesCount() {
        return ratesCount;
    }

    public void setratesCount(int ratesAmount) {
        this.ratesCount = ratesAmount;
    }
}
