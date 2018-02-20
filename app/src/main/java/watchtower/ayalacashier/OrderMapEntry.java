package watchtower.ayalacashier;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Moore on 2/13/2018.
 */

public class OrderMapEntry implements Serializable{
    private String key;
    private CateringObjectInfo value;

    public OrderMapEntry(String k, CateringObjectInfo v)
    {
        key = k;
        value = v;
    }

    public String getKey()
    {
        return key;
    }

    public CateringObjectInfo getValue()
    {
        return value;
    }
    
    public void setValue(CateringObjectInfo c)
    {//// TODO: 2/13/2018 check if this is a deep copy semantics
        value = c;
    }

    public String toString()
    {
        if(value.getOrderString() != null && !key.equals(StudentOrder.SALAD))
        {

                Log.d("TKT_orderMapEntry", "not null");
                return key + "=" + value.toString() + ": " + value.getOrderString();
        }

        else {
            Log.d("TKT_orderMapEntry", "null");
            return key + "=" + value.toString();
        }


    }
}
