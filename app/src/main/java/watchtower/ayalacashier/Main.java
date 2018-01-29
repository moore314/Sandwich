package watchtower.ayalacashier;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Main extends AppCompatActivity {
    //// TODO: 11/20/2017 I set both main and welcome to be launchers; don't forget to remove welcome's after main is all done 

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TKT_main","onCreate===========");
        context = this;
    }

    public boolean redirect(View v)
    {
        Intent intent = null;

        if(v.getId() == R.id.design)
            Cashier.openInstagram(this);
        else {

            if (v.getId() == R.id.cafeteria)
                intent = new Intent(this, Welcome.class);

            if (v.getId() == R.id.catering)
                intent = new Intent(this, Catering.class);


            //if(intent != null)
            startActivity(intent);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.facebook_menu, menu);
        return true;
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
            Cashier.openFacebook(context);
            return true;
        }
        return false;
    }

}
