package watchtower.ayalacashier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Catering extends AppCompatActivity {
    //// TODO: 11/27/2017 set on destroy and put order details in shared 

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering);
        context = this;

        Button salad = (Button)findViewById(R.id.saladCatering);
        Button quiche = (Button)findViewById(R.id.quicheCatering);
        Button plates = (Button)findViewById(R.id.platesCatering);
        Button pasta = (Button)findViewById(R.id.pastaCatering);
        Button soup = (Button)findViewById(R.id.soupCatering);
        Button foucachas = (Button)findViewById(R.id.foucachaCatering);
        Button bread = (Button)findViewById(R.id.breadCatering);
        Button desserts = (Button)findViewById(R.id.dessertCatering);

        salad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CateringSalads.class);
                startActivity(intent);

            }
        });

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.catering_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        if(id == R.id.facebook)
        {
            //open facebook
            Log.d("TKT_catering","opening facebook");
            return true;
        }
        if(id == R.id.cart)
        {
            //open cart
            Log.d("TKT_catering","opening cart");
            Intent intent = new Intent(this, Cart.class);
            startActivity(intent);
            return true;
        }
        return false;
    }


}
