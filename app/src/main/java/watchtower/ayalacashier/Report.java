package watchtower.ayalacashier;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

public class Report extends AppCompatActivity {

    ListView listView;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        listView = (ListView)findViewById(R.id.listView);
        context = this;
        //Cashier.displayReport(listView);
        //Cashier.displayReport(listView,(TextView)findViewById(R.id.altogetherText));

    }


    @Override
    protected void onResume() {
        Cashier.displayReport(listView,(TextView)findViewById(R.id.altogetherText), this);
        super.onResume();
    }
}
