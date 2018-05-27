package onlineExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Controller
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);


    @GetMapping("/")
    public String showForm(Model model) {
        CurrencyForm form = new CurrencyForm();
        model.addAttribute("crList", form);
        return "index";
    }

    /*public String showForm(Model model) {
        GoldPriceList gpd = new GoldPriceList( 100);
        model.addAttribute("GoldPriceList", gpd);
        model.addAttribute("datasarray", gpd.getDataArray());
        model.addAttribute("namesarray", gpd.getNameArray());
        return "goldGraph";
    }

*/

    @PostMapping("/")
    public String checkComplexNumberInputForm(Model model, @Valid @ModelAttribute("crForm") CurrencyForm graphForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "index";
        }
        if (!(graphForm.getCurrencyCode() == "gold")) {

            CurrencyList result = getCurrencyData(graphForm.getratesCount(), graphForm.getCurrencyCode());
            model.addAttribute("results", result);
            model.addAttribute("datasarray", result.getGraphValues());
            model.addAttribute("namesarray", result.getGraphNames());
        } else {
            CurrencyList result = getGoldPriceList(graphForm.getratesCount());
            model.addAttribute("results", result);
            model.addAttribute("datasarray", result.getGraphValues());
            model.addAttribute("namesarray", result.getGraphNames());
        }


        return "makeGraph";
    }


    private CurrencyList getCurrencyData(int counter, String code) {
        RestTemplate restTemplate = new RestTemplate();
        CurrencyList list =
                restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/" + code + "/last/" + counter + "/?format=json", CurrencyList.class);

        for (int i = 0; i < list.getCounter(); i++) {
            log.info(list.getCurrencyRate(i).toString());
        }
        return list;
    }

    private CurrencyList getGoldPriceList(int counter) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://api.nbp.pl/api/cenyzlota/last/" + counter + "/?format=json", CurrencyList.class);
    }


}
