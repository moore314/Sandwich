package watchtower.ayalacashier;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class Welcome extends AppCompatActivity {

    String employeeName;
    EditText name, pass;
    //TextView shift;
    ImageButton nextButton;
    RingProgressBar progressBar;
    int progress = 0;
    static boolean start = false;
    int RED = R.color.red;
    int TURQ = R.color.turq;

    Handler progressBarHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {

            if(start) {
                progress++;

            }
            progressBar.setProgress(progress);
            progressBarHandler.sendEmptyMessageDelayed(0,5);
        }

    };








    public void progressBarListener(final int color, final boolean state)
    {
        progressBar.setOnProgressListener(new RingProgressBar.OnProgressListener() {
            @Override
            public void progressToComplete() {
                //Log.d("TKT_welcome","progressToComplete");

                Log.d("TKT_welcome", "progComplete");
                if(start)
                {
                    Log.d("TKT_welcome", "start = true");
                    Cashier.updateShiftState(state);
                    start = false;

                }


            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Cashier.checkPrefs = getSharedPreferences(Cashier.CHECK_PREFS, 0);

        //shift = (TextView)findViewById(R.id.shiftState);
        name = (EditText)findViewById(R.id.name);
        pass = (EditText)findViewById(R.id.password);
        progressBar = (RingProgressBar)findViewById(R.id.progressBar);

        if(Cashier.checkPrefs.getBoolean(Cashier.SHIFT, false))
        {//if inShift
            Log.d("TKT_welcome","inShift");
            progressBar.setRingProgressColor(ContextCompat.getColor(this, RED));

        }
        else {
            //outOFsHIFT
            Log.d("TKT_welcome","outShift");
            progressBar.setRingProgressColor(ContextCompat.getColor(this, TURQ));
        }

        ImageButton getInShift = (ImageButton) findViewById(R.id.logo);

            getInShift.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    start = true;
                    if(Cashier.checkPrefs.getString(Cashier.EMPLOYEE_NAME, null) != null) {
                        if (Cashier.checkPrefs.getBoolean(Cashier.SHIFT, false)) {//if longPressed and shift = true; meaning, getOut
                            Log.d("TKT_welcome","if1");

                            progressBarListener(RED, false);
                            progressBarHandler.sendEmptyMessage(0);


                            //shift.setText(getString(R.string.outShift));
                            //shift.setTextColor(ContextCompat.getColor(context, R.color.red));
                            //Cashier.updateShiftState(false);
                        } else {
                            Log.d("TKT_welcome","else1");
                            progressBarListener(TURQ, true);
                            progressBarHandler.sendEmptyMessage(0);

                            //shift.setText(getString(R.string.inShift));
                            //shift.setTextColor(ContextCompat.getColor(context, R.color.turq));
                            //Cashier.updateShiftState(true);
                        }
                        return true;
                    }
                    return false;
                }
            });


        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("TKT_welcome","run");
                for(int i = 0; i<100; i++)
                {
                    try{
                        Log.d("TKT_welcome","try i: "+i);
                        Thread.sleep(100);
                        progressBarHandler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        Log.d("TKT_welcome","catch");
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        */
        inShift();


    }

    public void inShift()
    {

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
            //shift.setVisibility(View.VISIBLE);
            Log.d("TKT_welcome","employee != null");
            pass.setVisibility(View.GONE);
            name.setText(employeeName);
            name.setEnabled(false);
            if(Cashier.checkPrefs.getBoolean(Cashier.SHIFT, false))
            {
                //shift.setText(getString(R.string.inShift));
                //shift.setTextColor(ContextCompat.getColor(this, R.color.turq));
            }
            else
            {
                //shift.setText(getString(R.string.outShift));
                //shift.setTextColor(ContextCompat.getColor(this, R.color.red));
            }

        }



    }


   public void next (View v)
   {
       Log.d("TKT_welcome","next===================");
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
                    inShift();


           } else {
               Log.d("TKT_welcome", "next - else");
               Toast.makeText(this, R.string.wrong_password, Toast.LENGTH_SHORT).show();
               pass.setText(R.string.nil);
           }
       }

   }

   public void goToItemScreen()
   {
       Log.d("TKT_welcome","goToItemScreen===================");
       Intent intent = new Intent(this, ItemScreen.class);
       startActivity(intent);
   }

    public void hideKeyboard(View view) {
        Log.d("TKT_welcome","hiding keyboard===================");

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


    @Override
    protected void onResume() {
        inShift();
        super.onResume();
    }
}
