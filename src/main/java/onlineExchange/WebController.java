package onlineExchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;


@Controller

public class WebController {
    private static final Logger log = LoggerFactory.getLogger(Application.class);


    @Autowired
    JdbcTemplate jdbcTemplate;

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



        createMySQLdb(nbpDataService.getResult().getGraphDates(), nbpDataService.getResult().getGraphValues(),graphForm.getCurrencyCode());
        log.info(Arrays.toString(nbpDataService.getResult().getGraphValues()));


        return "makeGraph";
    }

    private void createMySQLdb(String[] date, double[] values, String code)
    {
        jdbcTemplate.execute("DROP TABLE IF EXISTS EXCHANGE_QUATATIONS ");
        jdbcTemplate.execute("CREATE TABLE EXCHANGE_QUATATIONS(id INT AUTO_INCREMENT, date VARCHAR(10), value DOUBLE , code VARCHAR(3), PRIMARY KEY (ID) );");

        for (int i=0;i<date.length;i++) {
            jdbcTemplate.update("INSERT INTO EXCHANGE_QUATATIONS (date, value, code) VALUES (?,?,?)", new Object[]{date[i],values[i],code});
        }

        List<String>lista=jdbcTemplate.queryForList("SELECT value FROM EXCHANGE_QUATATIONS",String.class);
        log.info(lista.toString());
    }

}




