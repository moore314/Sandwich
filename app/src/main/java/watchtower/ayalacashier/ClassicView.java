package watchtower.ayalacashier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ClassicView extends AppCompatActivity {
    EditText cashRec;
    TextView change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_view);
        Cashier.paymentText = (TextView)findViewById(R.id.genPaymentText);
        cashRec = (EditText)findViewById(R.id.genCashReceivedEditText);
        change = (TextView)findViewById(R.id.genChangeText);

    }

    public void addToCheck(View v)
    {
        Cashier.simplePayment((Button)v, (TextView)findViewById(R.id.genChangeText));


    }

    public void check(View v)
    {

    }
    public void cancel(View v)
    {
        //// TODO: 9/27/2017 consider add r u sure? message
        Cashier.simpleCancel(cashRec, change);



    }
}
