package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ClassicView extends AppCompatActivity {
    EditText cashReceived;
    TextView change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_view);
        Cashier.paymentText = (TextView)findViewById(R.id.genPaymentText);
        cashReceived = (EditText)findViewById(R.id.genCashReceivedEditText);
        change = (TextView)findViewById(R.id.genChangeText);
        TextView NIS = (TextView)findViewById(R.id.genNIS);
        NIS.setText(Cashier.ILS.getSymbol(Cashier.il));

    }

    public void addToCheck(View v)
    {
        Cashier.simplePayment((Button)v, (TextView)findViewById(R.id.genChangeText));
    }

    public void check(View v)
    {
        Cashier.simpleCheck(cashReceived, change, this);
    }
    public void cancel(View v)
    {
        //// TODO: 9/27/2017 consider add r u sure? message
        Cashier.simpleCancel(cashReceived, change);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu);
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
            case R.id.reportMenu:
            {
                Log.d("TKT_itemScreen","reportMenu===================");
                Intent intent  = new Intent(this, Report.class);
                startActivity(intent);
                return true;
            }
            /*
            case R.id.endShiftMenu:
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
            */
            case R.id.updatePrices:
            {
                //employeeName = Cashier.checkPrefs.getString(Cashier.EMPLOYEE_NAME, null);
                Intent intent = new Intent(this, UpdatePrices.class);
                startActivity(intent);
                return true;
            }
            case R.id.hours:
            {
                Intent intent = new Intent(this, Hours.class);
                startActivity(intent);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.are_you_sure);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button yes = (Button)Cashier.dialog.findViewById(R.id.hellYeah);
        Button no = (Button)Cashier.dialog.findViewById(R.id.heavensNo);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cashier.dialog.dismiss();
                ClassicView.super.onBackPressed();
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

    public void general(View v)
    {
        Cashier.general(this);
    }

}
