package watchtower.ayalacashier;

import java.io.Serializable;

/**
 * Created by Moore on 12/3/2017.
 */

public class CateringObjectInfo implements Serializable{

    private Double price;
    private Integer amount; //set this as double cuz I want it to be displayed with a decimel
    //private String

    public CateringObjectInfo()
    {
        price = new Double(0);
        amount = new Integer(0);

    }

    public CateringObjectInfo(String p, String  a)
    {
        price = new Double(p);
        amount = new Integer(a);
    }

    public void setPrice(String  p)
    {
        price = new Double(p);
    }

    public void setAmount(String a)
    {
        amount = new Integer(a);
    }

    public double getAmount()
    {
        return amount;
    }

    public double getPrice()
    {
        return price*amount;
    }

    public String toString()
    {
        return amount+"";
    }

}
