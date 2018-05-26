package onlineExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class CurrencyForm {

    private int currencyCode;
    private int ratesCount;

    public int getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(int currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getratesCount() {
        return ratesCount;
    }

    public void setratesCount(int ratesAmount) {
        this.ratesCount = ratesAmount;
    }
}
