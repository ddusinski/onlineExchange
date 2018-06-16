package onlineExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class NbpDataService {
    private static final Logger log = LoggerFactory.getLogger(NbpDataService.class);

    final static String URL_CURRENCY_DATES = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/%s/?format=json";
    final static String URL_CURRENCY = "http://api.nbp.pl/api/exchangerates/rates/a/%s/last/%d/?format=json";
    final static String URL_GOLD_DATES = "http://api.nbp.pl/api/cenyzlota/%s/%s/?format=json";
    final static String URL_GOLD = "http://api.nbp.pl/api/cenyzlota/last/%d/?format=json";
    private DataResponse result;
    private RestTemplate restTemplate = new RestTemplate();
    DatabaseService dbService = new DatabaseService();


    NbpDataService(String code, int counter)

    {

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
        ArrayList<String> datesRange = findDatesRange(counter);

        for (int i = datesRange.size() - 1; i > 0; i--) {
            String startDate = datesRange.get(i);
            String endDate = datesRange.get(i - 1);
            String urlCurrencyDates = String.format(URL_CURRENCY_DATES, code, startDate, endDate);
            currencyPricesList.addOtherCurrencyPrices(restTemplate.getForObject(urlCurrencyDates, CurrencyPricesList.class));
        }

        dbService.addQueries(currencyPricesList.getGraphDates(), currencyPricesList.getGraphValues(), code);
        currencyPricesList.setAvgValue(dbService.countAvgValue());
        currencyPricesList.setMaxValue(dbService.countMaxValue());
        currencyPricesList.setMinValue(dbService.countMinValue());


        return currencyPricesList;
    }


    private GoldPricesList getGoldPriceList(int counter) {
        GoldPricesList goldPricesList = new GoldPricesList();
        ArrayList<String> datesRange = findDatesRange(counter);

        for (int i = datesRange.size() - 1; i > 0; i--) {
            String startDate = datesRange.get(i);
            String endDate = datesRange.get(i - 1);
            String urlGoldDates = String.format(URL_GOLD_DATES, startDate, endDate);
            log.info(urlGoldDates);
            goldPricesList.addGoldList(restTemplate.getForObject(urlGoldDates, GoldPrice[].class));
        }
        dbService.addQueries(goldPricesList.getGraphDates(), goldPricesList.getGraphValues(), "GOLD");
        goldPricesList.setAvgValue(dbService.countAvgValue());
        goldPricesList.setMaxValue(dbService.countMaxValue());
        goldPricesList.setMinValue(dbService.countMinValue());

        return goldPricesList;
    }



    private ArrayList<String> findDatesRange(int counter) {

        ArrayList<String> datesRange = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        int rangeCounter = 0;
        datesRange.add(dateFormat.format(cal.getTime()));


        while (counter > 0) {
            if (isHoliday(cal) == false) {
                counter--;
            }
            if (rangeCounter == 366) {
                datesRange.add(dateFormat.format(cal.getTime()));
                rangeCounter = 0;

            }
            cal.add(Calendar.DATE, -1);
            rangeCounter++;
        }
        datesRange.add(dateFormat.format(cal.getTime()));
        log.info(datesRange.toString());
        return datesRange;
    }

    private boolean isHoliday(Calendar calendar)
    {
        boolean holiday = false;

        //  how to add Bożę ciało i poniedziałek wielkanoncy??

        if (calendar.get(Calendar.MONTH) == Calendar.JANUARY && calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            holiday = true;
        } else if (calendar.get(Calendar.MONTH) == Calendar.JANUARY && calendar.get(Calendar.DAY_OF_MONTH) == 6) {
            holiday = true;
        } else if (calendar.get(Calendar.MONTH) == Calendar.MAY && calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            holiday = true;
        } else if (calendar.get(Calendar.MONTH) == Calendar.MAY && calendar.get(Calendar.DAY_OF_MONTH) == 3) {
            holiday = true;
        } else if (calendar.get(Calendar.MONTH) == Calendar.AUGUST && calendar.get(Calendar.DAY_OF_MONTH) == 15) {
            holiday = true;
        } else if (calendar.get(Calendar.MONTH) == Calendar.NOVEMBER && calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            holiday = true;
        } else if (calendar.get(Calendar.MONTH) == Calendar.NOVEMBER && calendar.get(Calendar.DAY_OF_MONTH) == 11) {
            holiday = true;
        } else if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER && calendar.get(Calendar.DAY_OF_MONTH) == 25) {
            holiday = true;
        } else if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER && calendar.get(Calendar.DAY_OF_MONTH) == 26) {
            holiday = true;
        } else if ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
            holiday = true;
        } else if ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
            holiday = true;
        }
        return holiday;
    }

}


