package onlineExchange;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
public class WebController {



    @GetMapping("/")
    public String showForm(Model model) {


        GoldPriceList gpd = new GoldPriceList( 10);

        model.addAttribute("GoldPriceList", gpd);
        model.addAttribute("datasarray", gpd.getDataArray());
        model.addAttribute("namesarray", gpd.getNameArray());
        return "goldGraph";

    }











}
