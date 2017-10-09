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
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours);
        TextView altogetherHours = (TextView)findViewById(R.id.altogetherHours);
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
                //Toast.makeText(Hours.this, "date selected: "+dayOfMonth+"--"+month+"--"+year, Toast.LENGTH_SHORT).show();
                //1.get file of corresponding date:

                String dia=dayOfMonth+"", mes=month+"";
                if(dayOfMonth<10)
                    dia = "0"+dayOfMonth;
                if(month<10)
                    mes = "0"+month;

                String pressedDate = dia+"/"+mes+"/"+year%100;
                Log.d("TKT_hours","pressedDate: "+pressedDate);
                File file = new File(Welcome.context.getFilesDir().toString(), currMonth+"");
                try {
                    //FileInputStream inputStream = new FileInputStream(file);
                    ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                    String fileString = (String)objectInputStream.readObject();
                    Log.d("TKT_hours","fileString: "+fileString);
                    displayHourDialog(fileString, pressedDate);
                    //run through all Day till i find pressedDate

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("TKT_hours","exception!");
                    Toast.makeText(Hours.this, R.string.noEntries, Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    public void displayHourDialog(String file, String pressedDate)
    {
        Log.d("TKT_hours","displayHourDialog");
        String [] inFile = file.split("\n");
        for(int i = 0; i < inFile.length; i ++)
        {
            String [] fileEntry = inFile[i].split(">>");
            if (fileEntry[0].equals(pressedDate))
            {

                String mensaje = pressedDate+"\n";
                String [] rest = fileEntry[1].split("=");
                mensaje+=rest[0]+"\n"+rest[1];
                Log.d("TKT_hours","mensaje: "+mensaje);
                //inFile[i]; //fileEntry[1]+"\n";
                AlertDialog.Builder message = new AlertDialog.Builder(this);
                message.setMessage(mensaje).create();
                message.show();
            }
            else
                Toast.makeText(this, R.string.noEntries, Toast.LENGTH_SHORT).show();
        }
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
                showDialog();
                return true;
            }
        }
        return false;
    }

    public void showDialog()
    {
        Log.d("TKT_hours","showDialog");
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.change_hours_manually_dialog);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        NumberPicker picker = (NumberPicker)Cashier.dialog.findViewById(R.id.monthPicker);
        final ListView entryListView = (ListView)Cashier.dialog.findViewById(R.id.updateHoursListView);
        Button cancel = (Button)Cashier.dialog.findViewById(R.id.hoursCancelButton);
        picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker.setMinValue(0);
        picker.setMaxValue(12);
        picker.setWrapSelectorWheel(false);
        picker.setDisplayedValues(Cashier.MONTHS);
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //Log.d("TKT_hours","oldVal: "+oldVal);
                Log.d("TKT_hours","newVal: "+newVal);
                //jan = 0, dec = 11
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
    public void changeThisEntryHours(String entry, int month)
    {
        Log.d("TKT_hours","changeThisEntryHours");
        Log.d("TKT_hours","entry: "+entry);
        String entryNoSpace = entry.replace(" ","");
        Log.d("TKT_hours","entryNoSpace: "+entryNoSpace);
        String [] entrySplit = entryNoSpace.split("::");



        //======set current start&end times in timePicker
        String [] times = entrySplit[1].split("-");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date beg = null, end = null;
        try {
            beg = format.parse(times[0]);
            end = format.parse(times[1]);
            Log.d("TKT_hours","beg: "+times[0]);
            Log.d("TKT_hours","end: "+times[1]);

        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("TKT_hours","can't parse");
        }

        //setting time on picker is right below titleText.setText(title)


        //======set title to corresponding date
        String title = entrySplit[2];
        if(month<10)
            title+="/0"+month;
        else
            title+="/"+month;
        title+="/"+Calendar.getInstance().get(Calendar.YEAR);
        Log.d("TKT_hours","title: "+title);


        //======show dialog
        updateDialog = new Dialog(this);
        updateDialog.setContentView(R.layout.change_hours);
        updateDialog.setCanceledOnTouchOutside(false);
        TextView titleText = (TextView) updateDialog.findViewById(R.id.entryDate);
        final TimePicker st = (TimePicker)updateDialog.findViewById(R.id.startTimePicker);
        final TimePicker ed = (TimePicker)updateDialog.findViewById(R.id.endTimePicker);
        Button update = (Button)updateDialog.findViewById(R.id.finishUpdtHrs);
        Button cancel = (Button)updateDialog.findViewById(R.id.cancelUpdtHrs);
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
        Log.d("TKT_hours","stMin: "+cSt.get(Calendar.MINUTE));
        Log.d("TKT_hours","edMin: "+cEnd.get(Calendar.MINUTE));


        st.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                START = hourOfDay+":"+minute;
                Log.d("TKT_hours","START: "+START);
            }
        });

        ed.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                END = hourOfDay+":"+minute;
                Log.d("TKT_hours","END: "+END);
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set new hours
                //st.getHour();
                String newHours = START+" - "+END;
                /* TODO: 10/10/2017  update the list in shared
                entry format:
                  יי/חח/שש>>שעת_התחלה - שעת_סיום=סה"כ שעות: דד/שש
                1. get string from shared
                2. create a new string with new hours
                3. update shared again - make sure update is shown on listView
                */
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

}



