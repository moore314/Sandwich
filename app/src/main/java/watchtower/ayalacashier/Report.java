package watchtower.ayalacashier;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

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
    }

    public void displayReport()
    {

    }

    @Override
    protected void onResume() {
        Cashier.displayReport(listView);
        super.onResume();
    }
}
