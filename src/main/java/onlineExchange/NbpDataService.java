package onlineExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NbpDataService {
    private static final Logger log = LoggerFactory.getLogger(NbpDataService.class);

    final static String URL_CURRENCY_DATES = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/%s/?format=json";
    final static String URL_CURRENCY = "http://api.nbp.pl/api/exchangerates/rates/a/%s/last/%d/?format=json";
    final static String URL_GOLD_DATES = "http://api.nbp.pl/api/cenyzlota/%s/%s/?format=json";
    final static String URL_GOLD = "http://api.nbp.pl/api/cenyzlota/last/%d/?format=json";
    private DataResponse result;
    private RestTemplate restTemplate = new RestTemplate();

    NbpDataService(String code, int counter) {

        if (code.equals("gold")) {
            this.result = getGoldPriceList(counter);
        } else {
            this.result = getCurrencyData(counter, code);
        }
    }

    public DataResponse getResult() {
        return result;
    }

    private CurrencyPricesList getCurrencyData(int counter, String code) {
        CurrencyPricesList currencyPricesList = new CurrencyPricesList();

        if (isBiggerThenRequest(counter)) {
            String startDate = getDate(counter);
            String endDate = getDate(255);
            String urlCurrencyDates =
                    String.format(URL_CURRENCY_DATES, code, startDate, endDate);
            log.info(urlCurrencyDates);
            currencyPricesList = restTemplate.getForObject(urlCurrencyDates, CurrencyPricesList.class);
            counter = 255;
        }

        String urlCurrency = String.format(URL_CURRENCY, code, counter);
        currencyPricesList.addotherCurrencyPrices(restTemplate.getForObject(urlCurrency, CurrencyPricesList.class));
        return currencyPricesList;
    }

    private String getDate(int substractDays) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -substractDays);
        return dateFormat.format(cal.getTime());
    }


    private GoldPricesList getGoldPriceList(int counter) {
        GoldPricesList goldPricesList = new GoldPricesList();


        if (isBiggerThenRequest(counter)) {
            String startDate = getDate(counter);
            String endDate = getDate(255);
            String urlGoldDates = String.format(URL_GOLD_DATES, startDate, endDate);
            log.info(urlGoldDates);
            log.info(startDate);
            log.info(endDate);
            goldPricesList.addGoldList(restTemplate.getForObject(urlGoldDates, GoldPrice[].class));

            counter = 255;
        }


        String urlGold = String.format(URL_GOLD, counter);
        goldPricesList.addGoldList(restTemplate.getForObject(urlGold, GoldPrice[].class));
        return goldPricesList;
    }

    private Boolean isBiggerThenRequest(int requestSize) {
        return requestSize > 255;

    }


}


