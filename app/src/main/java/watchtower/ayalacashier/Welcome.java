package watchtower.ayalacashier;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Welcome extends AppCompatActivity {

    String employeeName;
    EditText name, pass;
    ImageView progressBar;
    ImageButton tick;
    static boolean start = false;
    int RED = R.color.red;
    int TURQ = R.color.turq;
    public static Context context;
    Menu m;



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
        tick = (ImageButton)findViewById(R.id.nextButton);
        //TextView timerText = (TextView)findViewById(R.id.timerCount);
        inShift();
        pass.setVisibility(View.GONE);
        context = this;



            getInShift.setOnLongClickListener(new View.OnLongClickListener() {


                @Override
                public boolean onLongClick(View v) {
                    if (!Cashier.checkPrefs.getBoolean(Cashier.IS_STUDENT, false))
                    {
                        /////////////////////////////
                        start = true;
                        Log.d("TKT_welcome", "start = " + start);
                        if (Cashier.checkPrefs.getString(Cashier.EMPLOYEE_NAME, null) != null)
                        {
                        //progressBar.setVisibility(View.VISIBLE);
                            if (Cashier.checkPrefs.getBoolean(Cashier.SHIFT, false))
                            {//if longPressed and shift = true; meaning, getOut
                                Log.d("TKT_welcome", "getOutShift");

                                Cashier.dialog = new Dialog(context);
                                Cashier.dialog.setContentView(R.layout.are_you_sure);
                                TextView q = (TextView) Cashier.dialog.findViewById(R.id.text);
                                q.setText(Cashier.EXIT_SHIFT);
                                Button yes = (Button) Cashier.dialog.findViewById(R.id.hellYeah);
                                Button no = (Button) Cashier.dialog.findViewById(R.id.heavensNo);

                                yes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        progressBar.setBackgroundResource(R.drawable.circle_red);
                                        Cashier.updateShiftState(false);
                                        Cashier.dialog.dismiss();
                                        shiftExit();
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
                        else
                        {
                            Log.d("TKT_welcome", "getInShift");
                            progressBar.setBackgroundResource(R.drawable.circle_turq);
                            Cashier.updateShiftState(true);
                            shiftEntry();

                        }
                        //handler();
                        return true;
                        }

                    }

                    Log.d("TKT_welcome","student cannot get in shift");
                    return false;
                }
            });





    }

    public void shiftEntry()
    {
        //get currentTime
        Log.d("TKT_welcome","shiftEntry");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        Calendar cal = Calendar.getInstance();
        String [] dayInit = (dateFormat.format(cal.getTime())).split(" ");
        String currDate=Cashier.checkPrefs.getString(Cashier.CURR_DATE, null);

        //if(currDate == null || currDate != dayInit[Cashier.DATE])
        {//digo, if there isn't a file corresponding to current date
            currDate = dayInit[Cashier.DATE];
            Cashier.updateToday(currDate, dayInit[Cashier.TIME], null);
        }


      //  if(!Cashier.today.date.equalsIgnoreCase(dayInit[0]))
    //    {
            //Cashier.today = new Day(dayInit[0]);//create day with date
            //Cashier.today.setStartTime(dayInit[1]);//add start time
            //push to shared
  //          Cashier.updateToday();
//        }

        //Log.d("TKT_welcome","dateFormatStart: "+dateFormat.format(cal.getTime()));

        //addToShared: if c

        /**
         * DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         Calendar cal = Calendar.getInstance();
         System.out.println(dateFormat.format(cal.getTime()));
         */

        /*
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Time currTime = new Time (System.currentTimeMillis());
        Log.d("TKT_welcome","currTime: "+currTime.toString());//
        */
    }

    public void shiftExit()
    {
        Log.d("TKT_welcome","shiftExit====================");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        Calendar cal = Calendar.getInstance();
        String [] dayEnd = (dateFormat.format(cal.getTime())).split(" ");
        //Log.d("TKT_welcome","date: "+dayEnd[Cashier.DATE]);
        Cashier.updateToday(dayEnd[Cashier.DATE], null, dayEnd[Cashier.TIME]);

    }


    public void inShift()
    {

        employeeName = Cashier.checkPrefs.getString(Cashier.EMPLOYEE_NAME, null);
        if(employeeName == null)
        {//no emplyee yet
            name.setEnabled(true);
            //pass.setVisibility(View.VISIBLE);
            initBoxes();
        }
        else
            {//employee is registered
                tick.setBackground(getDrawable(R.drawable.arrow));

                if (!Cashier.checkPrefs.getBoolean(Cashier.IS_STUDENT, false))
            {//this is staff
            pass.setVisibility(View.GONE);
            name.setText(employeeName);
            name.setEnabled(false);

                if (Cashier.checkPrefs.getBoolean(Cashier.SHIFT, false)) {//in shift
                    Log.d("TKT_welcome", "inShift");
                    progressBar.setBackgroundResource(R.drawable.circle_turq);
                    //set countUp timer
                } else {//out shift
                    Log.d("TKT_welcome", "outShift");
                    progressBar.setBackgroundResource(R.drawable.circle_red);
                }
            }
            else
            {//this is a student
                Log.d("TKT_welcome","this is a student");
                pass.setVisibility(View.GONE);
                name.setText(employeeName);
                name.setEnabled(false);
                progressBar.setBackgroundResource(R.drawable.circle_turq);
                progressBar.setEnabled(false);
                onCreateOptionsMenu(null);



            }
        }
    }


   public void next (View v)
   {
       Log.d("TKT_welcome","next===================");
       String temp = Cashier.checkPrefs.getString(Cashier.EMPLOYEE_NAME, null);
       if(pass.getVisibility() == View.GONE )
       {
           //Log.d("TKT_welcome","pass is gone");
           if(temp != null)
               goToItemScreen();
           else
           {
               Log.d("TKT_welcome","studentPassword");
               employeeName = name.getText().toString();
               if(employeeName.length() < 2)
                   Toast.makeText(context, R.string.pleaseChooseName, Toast.LENGTH_SHORT).show();
               else {
                   Cashier.sharedUpdateEmployee(employeeName);
                   name.setEnabled(false);
                   pass.setEnabled(false);
                   Cashier.sharedUpdateStudentState();
                   inShift();
               }
           }
       }
       else {
           //Log.d("TKT_welcome","pass is visible");
           if (Cashier.PASSWORD.equals(pass.getText().toString())) {
               //Log.d("TKT_welcome", "next - if");
                   employeeName = name.getText().toString();
                   Cashier.sharedUpdateEmployee(employeeName);
                   name.setEnabled(false);
                   pass.setEnabled(false);
                   inShift();


           } else
               /*
               if(Cashier.STUDENT_PASSWORD.equals(pass.getText().toString()))
               {
                   Log.d("TKT_welcome","studentPassword");
                   employeeName = name.getText().toString();
                   Cashier.sharedUpdateEmployee(employeeName);
                   name.setEnabled(false);
                   pass.setEnabled(false);
                   Cashier.sharedUpdateStudentState();
                   inShift();
               }
               */
               //else
               {
               //Log.d("TKT_welcome", "next - else");
               Toast.makeText(this, R.string.wrong_password, Toast.LENGTH_SHORT).show();
               pass.setText(R.string.nil);
           }
       }

   }

   public void goToItemScreen()
   {
       Log.d("TKT_welcome","goToItemScreen===================");
       Intent intent;
       if(!Cashier.checkPrefs.getBoolean(Cashier.IS_STUDENT,false)) {
           if (Cashier.checkPrefs.getInt(Cashier.VIEW_STATE, 1) == 0) {//this is classic
               //Log.d("TKT_welcome","classicView");
               intent = new Intent(this, ClassicView.class);
           } else {
               //Log.d("TKT_welcome","itemScreen");
               intent = new Intent(this, ItemScreen.class);
           }
       }
       else
       {
           Log.d("TKT_welcome","studentMode");
           intent = new Intent(this, StudentOrder.class);
       }

       startActivity(intent);
   }

    public void hideKeyboard(View view) {
        Log.d("TKT_welcome","hiding keyboard===================");

        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    public void initBoxes()
    {
        Log.d("TKT_welcome","intiBoxes was called=======================");
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
        Log.d("TKT_welcome","onResume======================");
        inShift();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Log.d("TKT_welcome","onCreateOptionMenu==========================================");
        m = menu;
        if(!Cashier.checkPrefs.getBoolean(Cashier.IS_STUDENT, false))
        {//this is empoyee
            Log.d("TKT_welcome","welcome_menu inflated");
            getMenuInflater().inflate(R.menu.welcome_menu, menu);

            if(Cashier.checkPrefs.getString(Cashier.EMPLOYEE_NAME, null) != null) {
                MenuItem m = menu.findItem(R.id.clerk);
                m.setTitle(R.string.orderMenu);
            }
            return true;
        }
        Log.d("TKT_welcome","student: remove menu");
        return false;
        /*
        else {
            getMenuInflater().inflate(R.menu.top_menu, menu);
            Log.d("TKT_welcome","top_menu inflated");
            return true;
        }
        */
    }


    @Override
    public void onBackPressed() {
        //exit app
        super.onBackPressed();
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
            case R.id.classicView:
            {
                int state = Cashier.checkPrefs.getInt(Cashier.VIEW_STATE,1);
                if(state == 0)
                {
                    //setTextToDetail
                    //Log.d("TKT_welcome","state == 0");
                    item.setTitle(Cashier.CLASSIC_VIEW);
                }
                else
                {
                    //setTextToSimple
                    //Log.d("TKT_welcome","state == 1");
                    item.setTitle(Cashier.DETAIL_VIEW);
                }
                Cashier.setCashierView(item, item.getTitle().toString());
                //item.setTitle(R.string.detailView);
                return true;
            }
            case R.id.clerk:
            {
                if(pass.getVisibility() == View.GONE)
                {
                    Log.d("TKT_welcome","gone");
                    if(!Cashier.checkPrefs.getBoolean(Cashier.IS_STUDENT, false) && Cashier.checkPrefs.getString(Cashier.EMPLOYEE_NAME, null) != null)
                    {
                        Intent intent = new Intent(this, StudentOrder.class);
                        startActivity(intent);
                    }
                    else {
                        pass.setVisibility(View.VISIBLE);
                    }
                    item.setTitle(R.string.orderMenu);
                }
                else
                {
                    Log.d("TKT_welcome","visible");
                    pass.setVisibility(View.GONE);
                    item.setTitle(R.string.clerk);
                }
                return true;
            }

        }
        return false;
    }


}
