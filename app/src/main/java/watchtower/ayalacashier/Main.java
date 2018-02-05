package watchtower.ayalacashier;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Main extends AppCompatActivity {

    static final int MY_PERMISSION_INT = 1;
    Context context;
    Intent callIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TKT_main", "onCreate===========");
        Cashier.checkPrefs = getSharedPreferences(Cashier.CHECK_PREFS, 0);
        context = this;
    }

    public boolean redirect(View v) {
        Intent intent = null;

        if (v.getId() == R.id.design)
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
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        if (id == R.id.facebook) {
            //open facebook
            Log.d("TKT_catering", "opening facebook");
            Cashier.openFacebook(context);
            return true;
        }

        if (id == R.id.callA) {
            Log.d("TKT_main", "got here111");
            callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(Cashier.number));

            //if permission denied when app was first installed
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                //ask the user for permission
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE))
                {
                    //show explanation or sth?
                    Log.d("TKT_main","explanation");
                }
                else
                {
                    Log.d("TKT_main","no explanation");
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},MY_PERMISSION_INT);
                }
            }
            else
            {//else: permission was granted
                Log.d("TKT_main","not sure what's here...");
                //// TODO: 2/3/2018 uncomment this for QA and production
                //startActivity(callIntent);
            }

            return true;
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case MY_PERMISSION_INT:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Log.d("TKT_main","onReqPerResult: calling A");
                    //// TODO: 2/3/2018 uncomment this for QA and production
                    //startActivity(callIntent);
                }
                else
                    Log.d("TKT_main","onReqPerResult: else");
            }
        }
    }
}
