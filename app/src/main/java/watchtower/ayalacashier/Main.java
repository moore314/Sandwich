package watchtower.ayalacashier;

import android.Manifest;
import android.app.Dialog;
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
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    static final int MY_PERMISSION_INT = 1;
    Context context;
    Intent callIntent;
    Boolean checkMe = false;

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
            {
                intent = new Intent(this, Welcome.class);
                if(!Cashier.checkPrefs.getBoolean(Cashier.DONT_SHOW_AGAIN_PAYPAL, false))
                {
                    openPayPalCreate(intent);
                }
                else
                    startActivity(intent);

            }


            if (v.getId() == R.id.catering)
            {
                intent = new Intent(this, Catering.class);
                if(!Cashier.checkPrefs.getBoolean(Cashier.DONT_SHOW_AGAIN_PAYPAL, false))
                {
                    openPayPalCreate(intent);
                }
                else
                    startActivity(intent);

            }

        }
        return true;
    }


    public void openPayPalCreate(final Intent intent)
    {
        Log.d("TKT_cart", "dontShowAgainPayPalDialog==============");
        //could have done that condision only on the dialog.show() method, but eh...

        {
            Cashier.dialog = new Dialog(this);
            Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            Cashier.dialog.setContentView(R.layout.dialog_create_paypal_account);
            Cashier.dialog.setCanceledOnTouchOutside(false);
            Button payPalRegister = (Button) Cashier.dialog.findViewById(R.id.openPayPalRegister);
            Button later = (Button)Cashier.dialog.findViewById(R.id.cancelCheck);
            TextView text = (TextView) Cashier.dialog.findViewById(R.id.dontShowAgainTxt);
            final CheckBox checkBox = (CheckBox) Cashier.dialog.findViewById(R.id.dontShowAgainBox);

            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) {
                        checkBox.setChecked(false);
                        checkMe = false;
                    } else {
                        checkBox.setChecked(true);
                        checkMe = true;
                    }
                }
            });

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkMe = isChecked;
                }
            });

            payPalRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkMe)
                        Cashier.updateDontShowAgainPayPal();
                    Cashier.dialog.dismiss();
                    Cashier.openPayPalRegister(context);
                }
            });
            later.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkMe)
                        Cashier.updateDontShowAgainPayPal();
                    Cashier.dialog.dismiss();
                    startActivity(intent);
                }
            });
            Cashier.dialog.show();
        }
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
