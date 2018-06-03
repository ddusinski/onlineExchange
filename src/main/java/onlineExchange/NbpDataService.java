package onlineExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NbpDataService {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    private String code;
    private int counter;
    private DataResponse result;
    private RestTemplate restTemplate = new RestTemplate();

    NbpDataService(String code, int counter) {
        this.code = code;
        this.counter = counter;

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

        //dodaję rekordy stasze niż ostatnie 255 wpisów
        if (counter > 255) {
            String startDate = getDate(counter);
            String endDate = getDate(255);
            String urlCurrencyDates =
                    String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/%s/?format=json", code, startDate, endDate);
            log.info(urlCurrencyDates);
            currencyPricesList = restTemplate.getForObject(urlCurrencyDates, CurrencyPricesList.class);
            counter = 255;
        }

        //dodaję pierwsze 255 wpisów
        String urlCurrency = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/last/%d/?format=json", code, counter);
        currencyPricesList.addotherCurrencyPrices(restTemplate.getForObject(urlCurrency, CurrencyPricesList.class));
        return currencyPricesList;
    }

    private String getDate(int substractDays) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -substractDays);
        String date = dateFormat.format(cal.getTime());
        return date;
    }


    private GoldPricesList getGoldPriceList(int counter) {
        GoldPricesList goldPricesList = new GoldPricesList();

        //dodaję rekordy stasze niż ostatnie 255 wpisów
        if (counter > 255) {
            String startDate = getDate(counter);
            String endDate = getDate(255);
            String urlGoldDates =
                    String.format("http://api.nbp.pl/api/cenyzlota/%s/%s/?format=json", startDate, endDate);
            log.info(urlGoldDates);
            goldPricesList.addGoldList(restTemplate.getForObject(urlGoldDates, GoldPrice[].class));
            counter = 255;
        }

        //dodaję pierwsze 255 wpisów
        String urlGold = String.format("http://api.nbp.pl/api/cenyzlota/last/%d/?format=json", counter);
        goldPricesList.addGoldList(restTemplate.getForObject(urlGold, GoldPrice[].class));
        return goldPricesList;
    }


}


