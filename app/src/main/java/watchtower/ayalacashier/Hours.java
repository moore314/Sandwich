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
        int month = Calendar.MONTH;
        altogetherHours.setText(Cashier.setAltogetherHours(month));

        Log.d("TKT_hours","month: "+month);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(Hours.this, "date selected: "+dayOfMonth+"--"+month+"--"+year, Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void hoursOfCurrentMonth()
    {

    }

    public void sendHoursReport(View v)
    {

    }
}
