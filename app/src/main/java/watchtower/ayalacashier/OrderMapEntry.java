package watchtower.ayalacashier;

/**
 * Created by Moore on 2/13/2018.
 */

public class OrderMapEntry {
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
}
