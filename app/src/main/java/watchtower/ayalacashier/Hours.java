package watchtower.ayalacashier;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Hours extends AppCompatActivity {

    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours);
        TextView altogetherHours = (TextView)findViewById(R.id.altogetherHours);


        calendar = (CalendarView)findViewById(R.id.calendarView);
        int month = Calendar.MONTH;//todo check: gives a wrong month number
        altogetherHours.setText(Cashier.setAltogetherHours(month));

        Log.d("TKT_hours","month: "+month);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //// TODO: 9/26/2017 set onClickListener for dates
                Toast.makeText(Hours.this, "date selected: "+dayOfMonth+"--"+month+"--"+year, Toast.LENGTH_SHORT).show();
                //1.get file of corresponding date: ALTOGETHER_HOURS+month

                //2.each entry looks like that: dd/mm/yy: startTime - endTime
                //look for entry that starts with same date as currentDate

                //3. display it in a dialog


            }
        });
    }


    public void sendHoursReport(View v)
    {

    }
}
