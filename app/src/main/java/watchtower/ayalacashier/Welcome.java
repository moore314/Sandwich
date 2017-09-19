package watchtower.ayalacashier;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    String employeeName;
    EditText name, pass;
    //TextView shift;
    ImageButton nextButton;
    //RingProgressBar progressBar;
    //ProgressBar progressBar;
    ImageView progressBar;
    //int progress = 0;
    static boolean start = false;
    int RED = R.color.red;
    int TURQ = R.color.turq;
    Context context;
    /*
    Handler progressBarHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {

            if(start) {
                ///Log.d("TKT_welcome","handleMessage");
                progress++;
            }
            progressBar.setProgress(progress);
            progressBarHandler.sendEmptyMessageDelayed(0,5);

        }

    };
    */
    //ImageView circle_turq;
    //Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Cashier.checkPrefs = getSharedPreferences(Cashier.CHECK_PREFS, 0);
        //shift = (TextView)findViewById(R.id.shiftState);
        name = (EditText)findViewById(R.id.name);
        pass = (EditText)findViewById(R.id.password);
        //progressBar = (RingProgressBar) findViewById(R.id.progressBar);
        progressBar = (ImageView)findViewById(R.id.stateCircle);
        ImageButton getInShift = (ImageButton) findViewById(R.id.logo);
        inShift();
        context = this;

            getInShift.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    start = true;
                    Log.d("TKT_welcome","start = "+start);
                    if(Cashier.checkPrefs.getString(Cashier.EMPLOYEE_NAME, null) != null) {
                        //progressBar.setVisibility(View.VISIBLE);
                        if (Cashier.checkPrefs.getBoolean(Cashier.SHIFT, false))
                        {//if longPressed and shift = true; meaning, getOut
                            Log.d("TKT_welcome","getOutShift");
                            progressBar.setBackgroundResource(R.drawable.circle_red);
                            //progressBar.setBackgroundColor(ContextCompat.getColor(context, RED));
                            //progressBar.setBackgroundResource(RED);

                            //progressBarListener(RED, TURQ, false);
                            //progressBarHandler.sendEmptyMessage(0);
                            //shift.setText(getString(R.string.outShift));
                            //shift.setTextColor(ContextCompat.getColor(context, R.color.red));
                            Cashier.updateShiftState(false);
                            shiftExit();

                        } else {
                            Log.d("TKT_welcome","getInShift");
                            progressBar.setBackgroundResource(R.drawable.circle_turq);
                            //progressBar.setBackgroundColor(ContextCompat.getColor(context, TURQ));
                            //progressBar.setBackgroundResource(TURQ);
                            /// /progressBarListener(TURQ, RED, true);
                            //progressBarHandler.sendEmptyMessage(0);
                            //shift.setText(getString(R.string.inShift));
                            //shift.setTextColor(ContextCompat.getColor(context, R.color.turq));
                            Cashier.updateShiftState(true);
                            shiftEntry();

                        }
                        //handler();
                        return true;
                    }
                    return false;
                }
            });





    }

    public void shiftEntry()
    {
        //// TODO: 9/20/2017 file start time; add shiftList screen and option to send to ayala; format will include date(maybe as a calender), time, hours each day, altogether monthly hours
    }

    public void shiftExit()
    {
        //// TODO: 9/20/2017 file end time
    }


/*
    public void progressBarListener(final int prog, final int currColor, final boolean state)
    {
        progressBar.setRingColor(ContextCompat.getColor(this, prog));
        progressBar.setRingProgressColor(ContextCompat.getColor(this, currColor));

        progressBar.setOnProgressListener(new RingProgressBar.OnProgressListener() {
            @Override
            public void progressToComplete() {
                //Log.d("TKT_welcome","progressToComplete");

                if(start)
                {
                    Cashier.updateShiftState(state);
                    progress = 0;
                    start = false;

                }


            }
        });
    }



*/

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
            Log.d("TKT_welcome","employee != null");
            pass.setVisibility(View.GONE);
            name.setText(employeeName);
            name.setEnabled(false);

            if(Cashier.checkPrefs.getBoolean(Cashier.SHIFT, false))
            {
                Log.d("TKT_welcome","inShift");
                progressBar.setBackgroundResource(R.drawable.circle_turq);
                //progressBar.setBackgroundResource(TURQ);
                //progressBar.setVisibility(View.INVISIBLE);
                //circle_turq.setBackgroundColor(ContextCompat.getColor(this, TURQ));
                //progressBar.setRingColor(ContextCompat.getColor(this, TURQ));
                //progressBar.setRingProgressColor(ContextCompat.getColor(this, RED));
            }
            else
            {
                Log.d("TKT_welcome","outShift");
                progressBar.setBackgroundResource(R.drawable.circle_red);
                //progressBar.setBackgroundColor(ContextCompat.getColor(this, RED));
                //progressBar.setBackgroundResource(RED);
                //circle_turq.setBackgroundColor(ContextCompat.getColor(this, RED));
                //progressBar.setRingColor(ContextCompat.getColor(this, RED));
                //progressBar.setRingProgressColor(ContextCompat.getColor(this, TURQ));
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
