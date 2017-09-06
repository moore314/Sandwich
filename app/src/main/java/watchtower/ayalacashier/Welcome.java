package watchtower.ayalacashier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    String employeeName;
    EditText name, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Cashier.checkPrefs = getSharedPreferences(Cashier.CHECK_PREFS, 0);
        name = (EditText)findViewById(R.id.name);
        pass = (EditText)findViewById(R.id.password);

        employeeName = Cashier.checkPrefs.getString(Cashier.EMPLOYEE_NAME, null);
        if(employeeName == null)
        {
            Log.d("TKT_welcome","employee = null");
            name.setEnabled(true);
            pass.setVisibility(View.VISIBLE);
            initBoxes();
        }
        else
        {
            Log.d("TKT_welcome","employee != null");
            pass.setVisibility(View.GONE);
            name.setText(employeeName);
            name.setEnabled(false);

        }

    }

   public void next (View v)
   {
       Log.d("TKT_welcome","next");
       if(pass.getVisibility() == View.GONE)
       {
           Log.d("TKT_welcome","pass is gone");
           goToItemScreen();
       }
       else {
           Log.d("TKT_welcome","pass is visible");
           if (Cashier.PASSWORD.equals(pass.getText().toString())) {
               Log.d("TKT_welcome", "next - if");
               employeeName = name.getText().toString();
               Cashier.sharedUpdateEmployee(employeeName);
               name.setEnabled(false);
               pass.setEnabled(false);
               goToItemScreen();
           // TODO: 9/5/2017 set a onRestart etc. although it seems to work fine
               
           } else {
               Log.d("TKT_welcome", "next - else");
               Toast.makeText(this, R.string.wrong_password, Toast.LENGTH_SHORT).show();
               pass.setText(R.string.nil);
           }
       }

   }

   public void goToItemScreen()
   {
       Log.d("TKT_welcome","goToItemScreen");
       //Toast.makeText(this, R.string.loading, Toast.LENGTH_SHORT).show();
       Intent intent = new Intent(this, ItemScreen.class);
       startActivity(intent);
       finish();
   }

    public void hideKeyboard(View view) {
        Log.d("TKT_welcome","hiding keyboard");

        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    public void initBoxes()
    {
        Log.d("TKT_welcome","intiBoxes was called");
        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }

            }
        });
    }



}
