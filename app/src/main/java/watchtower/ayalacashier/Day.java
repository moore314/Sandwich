package watchtower.ayalacashier;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Moore on 9/24/2017.
 */

public class Day implements Comparable, Serializable{

    String startTime;
    String endTime;
    String date;
    String sumHours;

    public Day(String d , String  st, String  et, String sh)
    {
        startTime = st;
        endTime = et;
        date = d;
        sumHours = sh;
    }

    public Day(Day dia)
    {
        startTime = dia.startTime;
        endTime = dia.endTime;
        date = dia.date;
        sumHours = dia.sumHours;
    }

    public Day(String entry)
    {

        String [] temp = entry.split(">>");
        date = temp[0];
        temp = temp[1].split("=");
        temp[0] = temp[0].replace(" ","");
        String [] time = temp[0].split("-");
        startTime = time[0];
        endTime = time[1];
        temp = temp[1].split(" ");
        sumHours = temp[2];
    }



    @Override
    public int compareTo(@NonNull Object o) {
        Day temp = (Day)o;
        return date.compareTo(temp.date);
    }

    //public void setEndTime(){}


    @Override
    public String toString() {
        return date+ ">>"+startTime+" - "+endTime+"="+Cashier.ALTOGETHER_HR_TXT +sumHours;
    }
}
