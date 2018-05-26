package onlineExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

@Controller
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);



    @GetMapping("/")
    public String showForm(Model model)
    {
        CurrencyForm form = new CurrencyForm();
        //model.addAttribute("crList", getData());
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


    private CurrencyList getData()
    {
            RestTemplate restTemplate = new RestTemplate();
            CurrencyList list =
            restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/rates/a/gbp/last/10/?format=json",CurrencyList.class);

            for (int i =0; i<list.getCounter();i++) {
                log.info(list.getCurrencyRate(i).toString());
            }
            return list;
    }






}
