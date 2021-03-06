package onlineExchange;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoldPricesList implements DataResponse {

    @JsonProperty("CenaZlota")
    private List<GoldPrice> item;
    private double avgValue;
    private double minValue;
    private double maxValue;

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
    public void setAvgValue(double avgValue) {
        this.avgValue = avgValue;
    }

    public double getAvgValue(){
        return this.avgValue;
    }


    public double getMaxValue() {
        return maxValue;
    }


    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }
}
