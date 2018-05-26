package onlineExchange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonFormat;


@JsonIgnoreProperties(ignoreUnknown = true)

public class GoldPrice {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public String data;
    public Double cena;

    public GoldPrice() {
    }

    public String getDate() {
        return data;
    }

    public void setDate(String date) {
        this.data = date;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    @Override
    public String toString()
    {
        return "cena zlota{ data:"+ data +", wartosc="+ cena +'}';
    }





}




