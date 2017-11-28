package watchtower.ayalacashier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class Cart extends AppCompatActivity {

    ListView lisa;
    TextView totalSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        totalSum = (TextView)findViewById(R.id.altogetherTextCatering);
        lisa = (ListView)findViewById(R.id.orderListCatering);
        Cashier.displayOrderCatering(lisa, totalSum, this);
    }
}
