package watchtower.ayalacashier;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Moore on 9/7/2017.
 */

public class ItemList  implements  Serializable{


    public boolean isValidInt(String string)
    {
        if(string == null || string.isEmpty())
            return false;
        for(int i = 0; i < string.length(); i++)
        {
            if(!Character.isDigit(string.charAt(i)))
                return false;
        }
        return true;
    }

    public class Item implements Comparable
    {
        String name;
        int amount;
        Item next = null;

        public Item(String n)
        {
            String [] itemDetails = n.split(" ");
            boolean isInt = isValidInt(itemDetails[itemDetails.length-1]);
            if(!isInt)
            {
                Log.d("TKT_itemList","gotHere");
                Log.d("TKT_itemList","item[last]: " + itemDetails[itemDetails.length-1]);
                name = n;
                Log.d("TKT_itemList","name: "+name);
                amount = 1;
            }
            else
            {
                Log.d("TKT_itemList","else");
                name = "";
                for(int i=0; i<itemDetails.length-1; i++)
                {
                    if(i == 0)
                        name += itemDetails[i];
                    else
                        name += " " + itemDetails[i];
                    Log.d("TKT_itemList","nameFromList: "+name);
                }
                amount = Integer.parseInt(itemDetails[itemDetails.length-1]);

            }

        }

        public Item(Item item)
        {
            name = item.name;
            amount = item.amount;
            next = item.next;
        }

        public Item(String n, int p)
        {
            name = n;
            amount = p;
        }

        public void increase()
        {
            amount++;
        }
        public void increase(int a)
        {
            amount+=a;
        }

        @Override
        public int compareTo(@NonNull Object o) {
            Item temp = (Item)o;
            if(amount > temp.amount)
                return 1;
            if(amount < temp.amount)
                return -1;
            return 0;
        }

        @Override
        public String toString() {
            return name+" "+amount;
        }

    }

    Item head;
    Item tail;

    public ItemList()
    {
        head = tail = null;
    }

    public void addItem(Item item)
    {
        boolean working = true;
        Item temp = new Item(item);
        if(head == null)
        {
            head = temp;
            tail = head;
        }
        else
        {
            Item run = head;
            if(run.compareTo(temp) == -1 || run.compareTo(temp) == 0)
            {//run is smaller or equals
                temp.next = head;
                head = temp;
                working = false;
            }
            while(run.next != null && working)
            {


                    if (run.compareTo(temp) == 1 && (run.next.compareTo(temp) == -1 || run.next.compareTo(temp) == 0))
                    {
                        temp.next = run.next;
                        run.next = temp;
                        working = false;
                    }

                run = run.next;

            }
            if(working) {
                tail.next = temp;
                tail = tail.next;
            }
        }
    }

    public void updateList(Item item)
    {
        boolean working = true;
        //TODO remove it and add it again
        Item run = head;
        while(run.next != null && working)
        {
            if(run.next == item)
            {
                run.next = item.next;
                item.next = null;
                working = false;
            }
            run = run.next;
        }
        this.addItem(item);
    }

    public void merge(ItemList list)
    {
        Item run = list.head;
        while (run != null)
        {
            Item temp = this.contains(run);
            if(temp != null)
            {
                Log.d("TKT_itemList","run.amount: "+run.amount);
                Log.d("TKT_itemList","temp.amount:" +temp.amount);
                temp.increase(run.amount);
                updateList(temp);

            }
            else
            {
                this.addItem(run);
            }
            run = run.next;
        }
    }

    public Item contains(String itemName)
    {

        Item run = head;
        while(run!=null) {
            if (run.name.equals(itemName))
                return run;
            run = run.next;
        }
        return null;
    }

    public Item contains(Item item)
    {

        Item run = head;
        while(run!=null) {
            if (run.name.equals(item.name))
                return run;
            run = run.next;
        }
        return null;
    }

    public ArrayList<String> getArrayList()
    {
        ArrayList<String> ans = new ArrayList<>();
        Item run = head;
        while(run != null)
        {
            ans.add(run.toString());
            run = run.next;
        }
        return ans;

    }

    public void clearAll()
    {
        head = tail = null;
    }

    @Override
    public String toString() {

        Item run = head;
        String ans = "";
        while(run!=null)
        {
            ans += run.toString()+"\n";
            run = run.next;
        }
        return ans;
    }





}
