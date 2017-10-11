package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Hours extends AppCompatActivity {

    CalendarView calendar;
    Calendar cal;
    Dialog updateDialog;
    public static Context context;
    int NEW_VAL = 0;
    String START = "", END = "";
    static TextView altogetherHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours);
        altogetherHours = (TextView)findViewById(R.id.altogetherHours);
        cal = Calendar.getInstance();
        context = this;
        calendar = (CalendarView)findViewById(R.id.calendarView);
        int tempMonth = cal.get(Calendar.MONTH);
        final int currMonth = ++tempMonth;
        //int day = cal.get(Calendar.DAY_OF_MONTH);
        //int year = cal.get(Calendar.YEAR);
        altogetherHours.setText(Cashier.setAltogetherHours(currMonth));

        //Log.d("TKT_hours","month: "+month);
        //Log.d("TKT_hours","day: "+day);
        //Log.d("TKT_hours","year: "+year);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month++;

                String dia=dayOfMonth+"", mes=month+"";
                if(dayOfMonth<10)
                    dia = "0"+dayOfMonth;
                if(month<10)
                    mes = "0"+month;

                String pressedDate = generateDateFormat(dia, mes, year);// dia+"/"+mes+"/"+year%100;

                //File file = new File(Welcome.context.getFilesDir().toString(), currMonth+"");
                //File file = new File(Welcome.context.getFilesDir().toString(), Cashier.DAY_MATRIX);
               // try


                    Day [][] dayMat = Cashier.getMatrixFromDB();
                    if (dayMat == null)
                        Toast.makeText(Hours.this, R.string.noEntries, Toast.LENGTH_SHORT).show();
                    else
                    {
                        displayHourDialog(dayMat, dayOfMonth, month);
                    }



            }
        });
    }


    public String generateDateFormat(String day, String month, int year)
    {
        return day+"/"+month+"/"+year%100;
    }

    public void displayHourDialog(Day [][] dayMat, int i, int j)
    {
        //i = rows = days
        //j = cols = months
        Log.d("TKT_hours","displayHourDialog===========");
       if(dayMat[i-1][j-1] != null)
       {

           Log.d("TKT_hours","dayMat[i-1][j-1]: "+dayMat[i-1][j-1]);
           AlertDialog.Builder message = new AlertDialog.Builder(this);
           message.setMessage(generateMessage(dayMat[i-1][j-1])).create();
           message.show();
       }
       else
           Toast.makeText(Hours.this, R.string.noEntries, Toast.LENGTH_SHORT).show();

    }

    public String generateMessage(Day day)
    {
        //day.toString() format:
        //  יי/חח/שש>>שעת_התחלה - שעת_סיום=סה"כ שעות: דד/שש     //
        String dayString = day.toString();
        String [] rest = dayString.split(">>");
        String message = rest[0]+"\n";
        rest = rest[1].split("=");
        message += rest[0]+"\n"+rest[1];
        return message;

    }



    public void sendHoursReport(View v)
    {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hours_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id)
        {
            case android.R.id.home:
            {
                onBackPressed();
                return true;
            }
            case R.id.manualUpdate:
            {
                //manualUpdate
                //dialog that on top there are months, and below there will be a listView of all month entries
                showChangeDialog(0);
                return true;
            }
        }
        return false;
    }

    public void addNewEntryDialog()
    {
        Calendar cal = Calendar.getInstance();
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_hours);
        TimePicker startTime = (TimePicker)dialog.findViewById(R.id.startTimePicker1);
        TimePicker endTime = (TimePicker)dialog.findViewById(R.id.endTimePicker1);
        DatePicker picker = (DatePicker)dialog.findViewById(R.id.datePicker);

        startTime.setIs24HourView(true);
        endTime.setIs24HourView(true);

        //picker.setMinDate(cal.get(Calendar.YEAR));
        //picker.setMaxDate(cal.get(Calendar.YEAR));


        dialog.show();
    }

    public void showChangeDialog(int monthInit)
    {
        Log.d("TKT_hours","showChangeDialog");
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.change_hours_manually_dialog);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        NumberPicker picker = (NumberPicker)Cashier.dialog.findViewById(R.id.monthPicker);
        final ListView entryListView = (ListView)Cashier.dialog.findViewById(R.id.updateHoursListView);
        Button cancel = (Button)Cashier.dialog.findViewById(R.id.hoursCancelButton);
        final Button addNewEntry = (Button)Cashier.dialog.findViewById(R.id.newEntry);
        picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setMinValue(0);
        picker.setMaxValue(12);
        picker.setWrapSelectorWheel(false);
        picker.setDisplayedValues(Cashier.MONTHS);
        picker.setValue(monthInit);
        if(monthInit != 0)
        {
            NEW_VAL = monthInit;
            Cashier.displayHoursFromFile(entryListView, monthInit);
        }

        addNewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewEntryDialog();
            }
        });

        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Log.d("TKT_hours","oldVal: "+oldVal);
                //Log.d("TKT_hours","newVal: "+newVal);
                //jan = 1, dec = 12
                //retrieve corresponding file: 1 for jan, 2 for feb etc.
                if(newVal == 0)
                {
                    entryListView.setAdapter(null);
                }
                else {
                    NEW_VAL = newVal;
                    Cashier.displayHoursFromFile(entryListView, newVal);
                }

            }
        });

        entryListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TKT_hours","onLongClick");
                String entry = entryListView.getItemAtPosition(position).toString();
                changeThisEntryHours(entry,NEW_VAL);
                return true;
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cashier.dialog.dismiss();
            }
        });



        Cashier.dialog.show();
    }
    public void changeThisEntryHours(String entry, final int month)
    {
        Log.d("TKT_hours","changeThisEntryHours");

        String entryNoSpace = entry.replace(" ","");
        String [] entrySplit = entryNoSpace.split("::");

        final String currentDiff = entrySplit[0];
        Log.d("TKT_hours","currDiff: "+currentDiff);

        //======set current start&end times in timePicker
        String [] times = entrySplit[1].split("-");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date beg = null, end = null;
        try {
            beg = format.parse(times[0]);
            end = format.parse(times[1]);
            START = times[0];
            END = times[1];

        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("TKT_hours","can't parse");
        }

        //setting time on picker is right below titleText.setText(title)


        //======set title to corresponding date
        String title = entrySplit[2];//TODO the assignment seems redundant
        if(month<10)
            title+="/0"+month;
        else
            title+="/"+month;
        final String TITLE = title+"/"+Calendar.getInstance().get(Calendar.YEAR)%100;//did that so it could be accessed from update.OnClickListener
        title+="/"+Calendar.getInstance().get(Calendar.YEAR);
        //Log.d("TKT_hours","title: "+title);


        //======show dialog
        updateDialog = new Dialog(this);
        updateDialog.setContentView(R.layout.change_hours);
        updateDialog.setCanceledOnTouchOutside(false);
        TextView titleText = (TextView) updateDialog.findViewById(R.id.entryDate);
        final TimePicker st = (TimePicker)updateDialog.findViewById(R.id.startTimePicker);
        final TimePicker ed = (TimePicker)updateDialog.findViewById(R.id.endTimePicker);
        Button update = (Button)updateDialog.findViewById(R.id.finishUpdtHrs);
        Button cancel = (Button)updateDialog.findViewById(R.id.cancelUpdtHrs);
        ImageButton deleteEntry = (ImageButton)updateDialog.findViewById(R.id.deleteEntryButton);
        st.setIs24HourView(true);
        ed.setIs24HourView(true);
        titleText.setText(title);
        //set time to timePicker
        Calendar cSt = Calendar.getInstance();
        cSt.setTime(beg);
        st.setHour(cSt.get(Calendar.HOUR_OF_DAY));
        st.setMinute(cSt.get(Calendar.MINUTE));
        Calendar cEnd = Calendar.getInstance();
        cEnd.setTime(end);
        ed.setHour(cEnd.get(Calendar.HOUR_OF_DAY));
        ed.setMinute(cEnd.get(Calendar.MINUTE));

        deleteEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String [] entries = TITLE.split("/");
                int day = Integer.parseInt(entries[0]);
                int month = Integer.parseInt(entries[1]);
                Day [][] days = Cashier.getMatrixFromDB();
                days[day-1][month-1] = null;
                Cashier.writeMatToDB(days);

                updateDialog.dismiss();
                if(Cashier.dialog.isShowing())
                    Cashier.dialog.dismiss();
                showChangeDialog(month);
            }
        });


        st.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(minute<10)
                    START = hourOfDay+":0"+minute;
                else
                    START = hourOfDay+":"+minute;
                Log.d("TKT_hours","START: "+START);
            }
        });

        ed.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(minute<10)
                    END = hourOfDay+":0"+minute;
                else
                    END = hourOfDay+":"+minute;
                Log.d("TKT_hours","END: "+END);
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set new hours
                //st.getHour();
                String diff = Cashier.hourDifference(START,END);
                /*
                entry format:
                  יי/חח/שש>>שעת_התחלה - שעת_סיום=סה"כ שעות: דד/שש
                */

                Log.d("TKT_hours","entry: "+TITLE);
                String newEntry = generateEntry(TITLE, START, END, diff);
                Log.d("TKT_hours","newEntry: "+newEntry);

                //get mat from db
                //Day newDayObj = new Day(newEntry);
                String [] dayOfMonth = newEntry.split(">>");
                dayOfMonth = dayOfMonth[0].split("/");
                int day = Integer.parseInt(dayOfMonth[0]);
                Log.d("TKT_hours","day: "+day);
                //Log.d("TKT_hours","newDayObj: "+newDayObj.toString());
                Day [][] dayMat = Cashier.getMatrixFromDB();
                dayMat[day-1][month-1] = new Day(newEntry);//newEntry;
                Cashier.writeMatToDB(dayMat);
                Cashier.updateAltogetherHours(currentDiff, dayMat[day-1][month-1].sumHours, month);
                Cashier.dialog.dismiss();
                showChangeDialog(month);
                Toast.makeText(Hours.this, R.string.successHrUpdate, Toast.LENGTH_SHORT).show();
                updateDialog.dismiss();





            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialog.dismiss();
            }
        });

        updateDialog.show();

    }



    public String generateEntry(String date, String startTime, String endTime, String sumHours) {
        Log.d("TKT_hours","generateEntry===========");
        //  יי/חח/שש>>שעת_התחלה - שעת_סיום=סה"כ שעות: דד/שש    //

        return date+ ">>"+startTime+" - "+endTime+"="+Cashier.ALTOGETHER_HR_TXT +sumHours;
    }




}



