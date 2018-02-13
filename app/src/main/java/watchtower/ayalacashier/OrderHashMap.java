package watchtower.ayalacashier;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Moore on 2/13/2018.
 */

public class OrderHashMap{
   private int size;
   //private OrderMapEntry<String, CateringObjectInfo>
    LinkedList<OrderMapEntry> values = new LinkedList<OrderMapEntry>();

    public CateringObjectInfo get(String key)
    {
        for(int i = 0; i < values.size(); i++)
        {
            if(values.get(i) != null)
            {
                if(values.get(i).getKey().equals(key))
                {
                    return values.get(i).getValue();
                }
            }
        }
        return null;
    }

    public void put(String key, CateringObjectInfo value)
    {
        boolean insert = true;
        for(int i = 0; i < values.size(); i++)
        {
            if(values.get(i).getKey().equals(key))
            {
                values.get(i).setValue(value);
                insert = false;
            }
        }
        if(insert)
        {
            int index = getIndexToAdd(key);
            values.add(index, new OrderMapEntry(key,value));
        }
    }

    public void remove(String key)
    {
        for(int i = 0; i < values.size(); i ++)
        {
            if(values.get(i).getKey().equals(key))
                values.remove(i);//check if okay
        }
    }

    public Set<String> keySet()
    {
        Set <String> set = new HashSet<>();
        for (int i = 0; i < values.size(); i ++)
        {
            set.add(values.get(i).getKey());
        }
        return set;
    }

    String salad = "סלט";
    String quiche = "קיש";
    String plate = "פלט";
    String rent = "אולם";
    String delivery = "משלוח";
    String cash = "מזומן";
    String pasta = "פסטה";
    String soup = "מרק";
    String dessert = "פאי";
    String bread = "לחמניות";


    public int getIndexToAdd(String key)
    {
        int index = values.size();
        boolean stop = false;

        if(key.contains(rent) || key.contains(delivery) || key.contains(cash))
            index = 0;

        //// TODO: 2/13/2018 make sure list isnt mt
        if(key.contains(salad)) {
            if(values.size() > 1)
                index = 1;
            else
                index = 0;
        }
        else
            if(key.contains(quiche))
            {
                for(int i = 0; i < values.size() && !stop; i++)
                {
                    if(values.get(i).getKey().contains(quiche)) {
                        index = i;
                        stop = true;
                    }
                }
            }
            else
                if(key.contains(plate))
                {
                    for(int i = 0; i < values.size() && !stop; i++ )
                    {
                        if(values.get(i).getKey().contains(plate)) {
                            index = i;
                            stop = true;
                        }
                    }
                }
                else
                    if(key.contains(pasta))
                    {
                        for(int i = 0; i < values.size() && !stop; i++)
                        {
                            if(values.get(i).getKey().contains(pasta))
                            {
                                index = i;
                                stop = true;
                            }
                        }
                    }
                    else
                        if(key.contains(soup))
                        {
                            for(int i = 0; i < values.size() && !stop; i++)
                            {
                                if(values.get(i).getKey().contains(soup))
                                {
                                    index = i;
                                    stop = true;
                                }
                            }
                        }
                        else
                            if(key.contains(dessert))
                            {
                                for(int i = 0; i < values.size() && !stop; i++)
                                {
                                    if(values.get(i).getKey().contains(dessert))
                                    {
                                        index = i;
                                        stop = true;
                                    }
                                }
                            }
                            else
                                if(key.contains(bread))
                                {
                                    for(int i = 0; i < values.size() && !stop; i++)
                                    {
                                        if(values.get(i).getKey().contains(bread))
                                        {
                                            index = i;
                                            stop = true;
                                        }
                                    }
                                }
        return index;
    }



}
