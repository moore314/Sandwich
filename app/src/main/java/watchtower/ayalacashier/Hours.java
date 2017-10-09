package watchtower.ayalacashier;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Calendar;

public class Hours extends AppCompatActivity {

    CalendarView calendar;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours);
        TextView altogetherHours = (TextView)findViewById(R.id.altogetherHours);
        cal = Calendar.getInstance();

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
                //// TODO: 9/26/2017 set onClickListener for dates
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
                return true;
            }
        }
        return false;
    }
}



