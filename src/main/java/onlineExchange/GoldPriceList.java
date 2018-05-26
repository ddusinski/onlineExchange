package onlineExchange;

public class GoldPriceList {
    public GoldPrice[] item;

    public GoldPrice[] getItem() {
        return item;
    }

    public void setItem(GoldPrice[] item) {
        this.item = item;
    }
    public GoldPriceList(GoldPrice[] item)
    {
        this.item=item;
    }
}
