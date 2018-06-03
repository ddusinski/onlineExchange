package onlineExchange;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoldPricesList implements DataResponse {

    @JsonProperty("CenaZlota")
    private List<GoldPrice> item;

    GoldPricesList() {
        this.item =new ArrayList<>() ;

    }


    public void addGoldList(GoldPrice[] prices)
    {
        this.item.addAll(Arrays.asList(prices));

    }


    public List<GoldPrice> getItem() {
        return item;
    }

    public void setItem(List<GoldPrice> item) {
        this.item = item;
    }

    public double[] getGraphValues() {
        double[] dataArray = new double[item.size()];
        for (int i = 0; i < this.item.size(); i++) {
            dataArray[i] = item.get(i).getPrice();
        }
        return dataArray;
    }

    public String[] getGraphDates() {
        String[] nameArray = new String[this.item.size()];

        for (int i = 0; i < this.item.size(); i++) {
            nameArray[i] = item.get(i).getDate();
        }
        return nameArray;
    }
}
