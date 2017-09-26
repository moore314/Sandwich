package watchtower.ayalacashier;

import android.support.annotation.NonNull;

/**
 * Created by Moore on 9/24/2017.
 */

public class Day implements Comparable{

    String startTime;
    String endTime;
    static String date;

    public Day(String d , String  st, String  et)
    {
        startTime = st;
        endTime = et;
        date = d;
    }

    public Day(String d)
    {
        date = d;
    }

    public void setStartTime(String st)
    {

    }

    @Override
    public int compareTo(@NonNull Object o) {
        Day temp = (Day)o;
        return date.compareTo(temp.date);
    }

    //public void setEndTime(){}


    @Override
    public String toString() {
        return date+ ": "+startTime+" - "+endTime;
    }
}
