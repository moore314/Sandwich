package watchtower.ayalacashier;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

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
        Log.d("TKT_report","onResume");
        Cashier.displayReport(listView,(TextView)findViewById(R.id.altogetherText), this);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.report_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case android.R.id.home:
            {
                onBackPressed();
                return true;
            }
            case R.id.sendReport:
            {
                Log.d("TKT_itemScreen","endShift===================");
                try {
                    Cashier.endShift(this);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("TKT_itemScreen","exception happened");
                }
                return true;
            }
        }
        return false;
    }
}
