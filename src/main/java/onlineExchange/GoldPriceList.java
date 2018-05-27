package onlineExchange;

public class GoldPriceList {


    public GoldPrice[] item;
    public int itemcounter;

    public GoldPrice[] getItem() {
        return item;
    }

    public void setItem(GoldPrice[] item) {
        this.item = item;
    }

    public int getItemcounter() {
        return itemcounter;
    }

    public void setItemcounter(int itemcounter) {
        this.itemcounter = itemcounter;
    }


    public double[] getDataArray() {
        double[] dataArray = new double[this.itemcounter];
        for (int i = 0; i < this.itemcounter; i++) {
            dataArray[i] = item[i].getCena();
        }
        return dataArray;
    }

    public String[] getNameArray() {
        String[] nameArray = new String[this.itemcounter];

        for (int i = 0; i < this.itemcounter; i++) {
            nameArray[i] = item[i].getDate();
        }
        return nameArray;
    }
}
