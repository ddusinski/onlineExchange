package onlineExchange;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;


@SpringBootApplication
public class Application {


    public static void main(String args[]) {
        SpringApplication.run(Application.class);


    }



}
