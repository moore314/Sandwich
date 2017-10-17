package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
                Log.d("TKT_itemScreen","sendReportToA===================");
                try {
                    Cashier.sendReportToA(this, listView, (TextView)findViewById(R.id.altogetherText));

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("TKT_itemScreen","exception happened");
                }
                return true;
            }
            case R.id.clearReport:
            {
                areYouSure();
                return true;
            }
        }
        return false;
    }
    public void areYouSure()
    {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.are_you_sure);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        TextView q = (TextView)Cashier.dialog.findViewById(R.id.text);
        q.setText(R.string.clearPurchaseReport);
        Button yes = (Button)Cashier.dialog.findViewById(R.id.hellYeah);
        Button no = (Button)Cashier.dialog.findViewById(R.id.heavensNo);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Cashier.clearAllReport(context, listView, (TextView)findViewById(R.id.altogetherText));
                Cashier.dialog.dismiss();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cashier.dialog.dismiss();
            }
        });

        Cashier.dialog.show();

    }
}
