package onlineExchange;

import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GoldPriceList {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public GoldPrice[] item;
    public int itemcounter;

    public GoldPrice[] getItem() {
        return item;
    }

    public void setItem(GoldPrice[] item) {
        this.item = item;
    }
   /* public GoldPriceList(int itemcounter)
    {
        this.item=getPriceList(itemcounter);
        this.itemcounter=itemcounter;
        reasultPrint();
    }
*/
    public int getItemcounter() {
        return itemcounter;
    }

    public void setItemcounter(int itemcounter) {
        this.itemcounter = itemcounter;
    }



    private void reasultPrint()
    {
        for (int i=0; i<item.length;i++)
        {
            log.info(item[i].toString());
        }
    }

    public double[] getDataArray()
    {
        double[] dataArray = new double[this.itemcounter];
        for (int i=0;i<this.itemcounter; i++)
        {
            dataArray[i]=item[i].getCena();
        }
        return dataArray;
    }

    public String[] getNameArray()
    {
        String[] nameArray = new String[this.itemcounter];

        for (int i=0;i<this.itemcounter; i++)
        {
            nameArray[i]=item[i].getDate();
        }
        return nameArray;
    }
}
