package onlineExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

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
