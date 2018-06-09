package onlineExchange;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class GoldPrice {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("data")
    private String data;
    @JsonProperty("cena")
    private Double price;

    public String getDate() {
        return data;
    }

    public void setDate(String date) {
        this.data = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setCena(Double price) {
        this.price = price;
    }


}




