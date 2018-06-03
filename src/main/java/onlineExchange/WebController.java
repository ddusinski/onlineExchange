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




