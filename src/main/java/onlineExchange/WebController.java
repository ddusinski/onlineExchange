package onlineExchange;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @GetMapping("/")
    public String showForm(Model model) {

        //int[] datasarray= {1, 2, 3, 4, 5};

// String[] namesarray = {"Red", "Blue", "Yellow", "Green", "Purple", };
        GoldPriceList gpd = new GoldPriceList(queryToClass());

        double[] datasarray = new double[5];
        String[] namesarray = new String[5];

        for (int i=0;i<5; i++)
        {
            datasarray[i]=gpd.item[i].getCena();
            namesarray[i]=gpd.item[i].getDate();
        }




        model.addAttribute("GoldPriceList", gpd);
        model.addAttribute("datasarray", datasarray);
        model.addAttribute("namesarray", namesarray);
        return "goldGraph";

    }





    private static GoldPrice[] queryToClass()
    {
        RestTemplate restTemplate = new RestTemplate();

        GoldPrice[] cenaZlota = restTemplate.getForObject("http://api.nbp.pl/api/cenyzlota/last/5/?format=json",GoldPrice[].class);
        for (int i=0; i<cenaZlota.length;i++)
        {
            log.info(cenaZlota[i].toString());
        }
    return cenaZlota;

    }





}
