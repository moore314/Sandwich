package watchtower.ayalacashier;

import java.io.Serializable;

/**
 * Created by Moore on 12/3/2017.
 */

public class CateringObjectInfo implements Serializable{

    private Double price;
    private Integer amount; //set this as double cuz I want it to be displayed with a decimel
    private String orderString;
    private String [] additions = null;
    //private String

    public CateringObjectInfo()
    {
        price = new Double(0);
        amount = new Integer(0);
        orderString = "";

    }

    public CateringObjectInfo(String p, String  a, String stringOrder)
    {
        price = new Double(p);
        amount = new Integer(a);
        orderString = stringOrder;

    }

    public CateringObjectInfo(String p, String  a, String stringOrder, String [] adds)
    {
        price = new Double(p);
        amount = new Integer(a);
        orderString = stringOrder;
        additions = new String[2];
        additions[0] = adds[0];//represents bread for salad
        additions[1] = adds[1];//represents number of additions
    }


    public String [] getAdditions()
    {
        return additions;
    }
    public boolean getBread()
    {
        return additions[0].equals(StudentOrder.YES_BREAD);
    }
    public String getAdds()
    {
        return additions[1];
    }
    public double getSaladPrice()
    {
        double allPrice = price*amount;
        if(additions[0] != StudentOrder.NO_BREAD)
        {
            allPrice ++;
        }
        allPrice += Integer.parseInt(additions[1]) * Cashier.SALAD_PRICES[Cashier.IND_SALAD_ADDITION];
        return allPrice;
    }


    public void setPrice(String  p)
    {
        price = new Double(p);
    }

    public void setAmount(String a)
    {
        amount = new Integer(a);
    }

    public int getAmount()
    {
        return amount;
    }

    public void setOrderString(String order)
    {
        orderString = order;
    }

    public double getPrice()
    {
        return price*amount;
    }

    public String getOrderString()
    {
        return orderString;
    }

    public String toString()
    {
        if(orderString != null && orderString.equals(StudentOrder.SALAD))
        {
            return
                    StudentOrder.ORDER_STRING_SALAD_ADDS + additions[1] + "\n" +
                    StudentOrder.ORDER_STRING_SALAD_BREAD + additions[0];
        }
        return amount+"";
    }

}
