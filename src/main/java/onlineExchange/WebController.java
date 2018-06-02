package onlineExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class WebController {
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @GetMapping("/")
    public String showForm(Model model) {
        CurrencyForm form = new CurrencyForm();
        model.addAttribute("crForm", form);
        return "index";
    }

    @PostMapping("/")
    public String checkComplexNumberInputForm(Model model, @Valid @ModelAttribute("crForm") CurrencyForm graphForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "index";
        }

        NbpDataService nbpDataService = new NbpDataService(graphForm.getCurrencyCode(), graphForm.getRatesCount());
        model.addAttribute("datasArray", nbpDataService.getResult().getGraphDates());
        model.addAttribute("pricesArray", nbpDataService.getResult().getGraphValues());

        double[] dd = nbpDataService.getResult().getGraphValues();

        for (int i = 0; i < dd.length; i++) {
            log.info(String.valueOf(dd[i]));
        }

        return "makeGraph";
    }
}
/*
    private CurrencyPricesList getCurrencyData(int counter, String code) {
        RestTemplate restTemplate = new RestTemplate();
        CurrencyPricesList list = new CurrencyPricesList();

        if (counter > 255) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -255);
            String endDate = dateFormat.format(cal.getTime());
            log.info(endDate);
            cal.add(Calendar.DATE, -(counter - 255));
            String startDate = dateFormat.format(cal.getTime());
            log.info(startDate);
            //log.info("http://api.nbp.pl/api/exchangerates/rates/a/" + code + "/" + startDate + "/" + endDate + "/?format=json");
             list =
                    restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/" + code + "/" + startDate + "/" + endDate + "/?format=json", CurrencyPricesList.class);
            counter = 255;
        }

            list.addotherCurrencyPrices(
                restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/" + code + "/last/" + counter + "/?format=json", CurrencyPricesList.class));

            for (int i = 0; i < listA.getCurrencyRates().size(); i++) {
            log.info(listA.getCurrencyRate(i).toString());

            list.addotherCurrencyPrices(list);

        return list;
    }








*/



