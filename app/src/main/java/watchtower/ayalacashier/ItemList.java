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
                name = n;
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

        public boolean equals(Item item)
        {
            return item.name == this.name;
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

    public boolean addItem(Item item)
    {
        Log.d("TKT_itemList","addItem");
        boolean working = true;
        Item temp = new Item(item);
        if(this.contains(temp) == null)
        {
            if (head == null) {
                head = temp;
                tail = head;
                return true;
            }
            else
                {
                Item run = head;
                if (run.compareTo(temp) == -1 || run.compareTo(temp) == 0) {//run is smaller or equals
                    temp.next = head;
                    head = temp;
                    return true;
                }
                while (run.next != null)
                {
                    if (run.compareTo(temp) == 1 && (run.next.compareTo(temp) == -1 || run.next.compareTo(temp) == 0)) {
                        temp.next = run.next;
                        run.next = temp;
                        return true;
                    }

                    run = run.next;

                }
                /*
                if (working) {
                    tail.next = temp;
                    tail = tail.next;
                }
                */
            }
        }
        tail.next = temp;
        tail = tail.next;
        return false;
    }

    public boolean remove(Item item)
    {
        Log.d("TKT_itemList","remove: "+item.toString());
        Log.d("TKT_itemList","this.toString b4 remove: "+this.toString());
        boolean working = true;
        Item run = head;
        if(run != null && run == item)
        {//if it's the 1st item in list
            head = head.next;
            run.next = null;
            return true;
        }
        else {
            while (run.next != null) {
                Log.d("TKT_itemList", "run.next: " + run.next.toString());
                if (run.next == item) {
                    if(run.next == tail)
                    {
                        run.next = null;
                        tail = run;
                    }
                    else
                    {
                        run.next = item.next;
                        item.next = null;
                    }
                    return true;
                }

                run = run.next;
            }
        }
    return false;
    }



    public void merge(ItemList list)
    {
        Item run = list.head;
        while (run != null)
        {
            Item temp = this.contains(run);
            if(temp != null)
            {
                Log.d("TKT_itemList","merge: this contains run");
                remove(temp);
                temp.increase(run.amount);
                this.addItem(temp);
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
