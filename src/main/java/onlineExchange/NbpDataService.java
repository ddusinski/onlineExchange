package onlineExchange;

import org.springframework.web.client.RestTemplate;

public class NbpDataService {

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
        String urlCurrency = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/last/%d/?format=json", code, counter);
        return restTemplate.getForObject(urlCurrency, CurrencyPricesList.class);
    }

    private GoldPricesList getGoldPriceList(int counter) {
        String urlGold = String.format("http://api.nbp.pl/api/cenyzlota/last/%d/?format=json", counter);
        GoldPrice[] goldPriceTempArray = restTemplate.getForObject(urlGold, GoldPrice[].class);
        GoldPricesList goldPricesList = new GoldPricesList(goldPriceTempArray);
        return goldPricesList;
    }


}
