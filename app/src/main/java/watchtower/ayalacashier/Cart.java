package watchtower.ayalacashier;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.util.Calendar;
import java.util.Map;

public class Cart extends AppCompatActivity {

    ListView lisa;
    TextView totalSum;
    int chosenAmount = 1;//to be used in numnerPicker
    Context context;
    Button orderNow;
    Activity act;
    Calendar cal;
    CalendarView calendar;
    String dateToOrder;
    int updateMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        context = this;
        act = this;
        totalSum = (TextView)findViewById(R.id.altogetherTextCatering);
        lisa = (ListView)findViewById(R.id.orderListCatering);
        orderNow = (Button)findViewById(R.id.orderNow);
        Cashier.displayOrderCatering(lisa, totalSum, context,0);
        if(Cashier.checkPrefs.getBoolean(Cashier.PAYPAL_PAID_CATERING, false))
        {
            lisa.setEnabled(false);
        }
        else {
            lisa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    String entry = lisa.getItemAtPosition(position).toString();
                    if (entry.equals(getString(R.string.deliveryCatering)) || entry.equals(getString(R.string.rentThePlace)) || entry.equals(getString(R.string.cashPayement))) {
                        changeAmountDialog(entry, position, true);
                    } else {
                        Log.d("TKT_cart", "onCreate.entry: " + entry);
                        changeAmountDialog(entry, position, false);
                    }
                    return true;
                }
            });
        }

        orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactInfoDialog();
                    //Cashier.sendCateringOrderToA(context,lisa,totalSum);

            }
        });
        Cashier.configuration = new PayPalConfiguration().environment(PayPalConfiguration.ENVIRONMENT_SANDBOX).clientId(Cashier.paypalClientId);
        Cashier.service = new Intent(this, PayPalService.class);
        Cashier.service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, Cashier.configuration);
        startService(Cashier.service);//paypal service listening to calls of payment
        //===============


    }


    public void contactInfoDialog()
    {
        Log.d("TKT_cart","contactInfoDialog==============");
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Cashier.dialog.setContentView(R.layout.dialog_contact_info);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        final EditText name = (EditText)Cashier.dialog.findViewById(R.id.contactInfoEdit);
        final EditText phone = (EditText)Cashier.dialog.findViewById(R.id.contactPhoneEdit);
        final DatePicker date = (DatePicker)Cashier.dialog.findViewById(R.id.datePicker);
        Button cancel = (Button)Cashier.dialog.findViewById(R.id.contactInfoCancel);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.contactInfoProceed);
        cal = Calendar.getInstance();
        date.setMinDate(System.currentTimeMillis() - 1000);
        updateMonth = cal.get(Calendar.MONTH)+1;
        dateToOrder = generateDateFormat(cal.get(Calendar.DAY_OF_MONTH),updateMonth,cal.get(Calendar.YEAR));
        String nameFromShared = Cashier.checkPrefs.getString(Cashier.CATERING_CONTACT_INFO_NAME, null);
        if(nameFromShared!=null)
            name.setText(nameFromShared);

        String phoneFromShared = Cashier.checkPrefs.getString(Cashier.CATERING_CONTACT_INFO_PHONE, null);
        if(phoneFromShared!=null)
            phone.setText(phoneFromShared);

        date.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                //Log.d("Date", "Year=" + year + " Month=" + (month + 1) + " day=" + dayOfMonth);
                dateToOrder = generateDateFormat(dayOfMonth, (month+1), year);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cashier.dialog.dismiss();
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save contactInto to shared
                if(name.getText().toString().length() < 2)
                {
                    AlertDialog.Builder message = new AlertDialog.Builder(context);
                    message.setMessage(R.string.pleaseChooseName).create();
                    message.show();
                }
                else
                if(phone.getText().toString().length() != 10)
                {
                    AlertDialog.Builder message = new AlertDialog.Builder(context);
                    message.setMessage(R.string.invalidPhoneNumber).create();
                    message.show();
                }
                else
                    {
                    Cashier.sharedUpdateDateCateringInfo(dateToOrder);
                    Cashier.sharedUpdateContactInfo(name.getText().toString(), phone.getText().toString());
                        createOrderTextAndSend();
                /*
                try {
                    Cashier.sendCateringOrderToA(context,lisa,totalSum);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                */
                    Cashier.dialog.dismiss();
                }
            }
        });

        Cashier.dialog.show();

    }

    public void changeAmountDialog(final String entry, final int position, final boolean flag)
    {
        Log.d("TKT_cart","changeAmountDialog=======");
        chosenAmount = 1;
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Cashier.dialog.setContentView(R.layout.change_amount_dialog);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        ImageButton delete = (ImageButton)Cashier.dialog.findViewById(R.id.cateringDeleteEntryButton);
        Button cancel = (Button)Cashier.dialog.findViewById(R.id.cateringCancelCheck);
        Button finish = (Button)Cashier.dialog.findViewById(R.id.cateringCheck);
        NumberPicker picker = (NumberPicker) Cashier.dialog.findViewById(R.id.cateringPickAmount);
        TextView amountTxt = (TextView)Cashier.dialog.findViewById(R.id.amountTxt);
        if(!flag) {
            amountTxt.setText(getString(R.string.hotsQuantity));
            picker.setVisibility(View.VISIBLE);
            picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
            picker.setMinValue(1);
            picker.setMaxValue(10);
            picker.setWrapSelectorWheel(false);
            picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    chosenAmount = newVal;
                }
            });
        }
        else {
            amountTxt.setText(getString(R.string.deleteEntryCatering));
            picker.setVisibility(View.GONE);
        }



        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TKT_cart","entry: "+ entry+"\nchooseAmount: "+chosenAmount+"\npos: "+position);
                updateItemAmount(entry, chosenAmount, position);
                Cashier.dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cashier.dialog.dismiss();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Log.d("TKT_cart","delete was clicked");
                deleteEntry(entry, flag);
                Cashier.dialog.dismiss();
            }
        });

        Cashier.dialog.show();
    }

    public void deleteEntry(String entry, boolean flag)
    {
        String entryToDelete;
        if(flag)
        {//flag = true means entry == delivery || entry == rent
            entryToDelete = entry;

        }
        else {
            String[] entrySplit = entry.split(" :: ");
            Log.d("TKT_cart", "deleteEntry.entrySplit[1]: " + entrySplit[1]);
            entryToDelete = entrySplit[1];
        }
        CateringObjectInfo temp = Cashier.cateringOrder.remove(entryToDelete);
        Cashier.writeToFileCateringOrder(context);
        Cashier.displayOrderCatering(lisa, totalSum, context, temp.getPrice());
    }

    public void updateItemAmount(String entry, int newAmount, int pos)
    {
        String [] entrySplit = entry.split(" :: ");
        Log.d("TKT_cart","updateItemAmount.entrySplit[0]: "+entrySplit[0]);
        Log.d("TKT_cart","updateItemAmount.entrySplit[1]: "+entrySplit[1]);
        //entrySplit[0] = newAmount+"";
        CateringObjectInfo newOb = Cashier.cateringOrder.get(entrySplit[1]);
        newOb.setAmount(newAmount+"");
        Cashier.writeToFileCateringOrder(context);
        Cashier.displayOrderCatering(lisa, totalSum, context,0);
        //lisa.getItemAtPosition(pos);

        //String newEntry = Cashier.cateringCartGenerateString(entrySplit[0],entrySplit[1]);
        //replaceEntry(newEntry,pos);

    }
    public void replaceEntry(String newEntry)
    {

    }

    public void paymentDialog()
    {
        Log.d("TKT_catering","paymentDialog================");
        if(Cashier.checkPrefs.getBoolean(Cashier.PAYPAL_PAID_CATERING,false))
        {
            AlertDialog.Builder message = new AlertDialog.Builder(context);
            message.setMessage(R.string.alreadyPaid).create();
            message.show();
        }
        else {
            if (!totalSum.getText().toString().equals(getString(R.string.nullPayment))) {
                final String[] paymentString = {getString(R.string.paypalPayement), getString(R.string.cashPayement)};
                final boolean[] isCheckedArr = new boolean[2];
                Cashier.dialog = new Dialog(this);
                Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                Cashier.dialog.setContentView(R.layout.dialog_final_price_catering);
                Cashier.dialog.setCanceledOnTouchOutside(false);
                Button proceed = (Button) Cashier.dialog.findViewById(R.id.check);
                Button cancel = (Button) Cashier.dialog.findViewById(R.id.cancelCheck);
                final LinearLayout paypalLayout = (LinearLayout) Cashier.dialog.findViewById(R.id.paypalLinearLayout);
                final LinearLayout cashLayout = (LinearLayout) Cashier.dialog.findViewById(R.id.LinearLayoutCash);


                final TextView paypalTxt1 = (TextView) Cashier.dialog.findViewById(R.id.paypalTextView1);
                final TextView paypalTxt2 = (TextView) Cashier.dialog.findViewById(R.id.paypalTextViewPRICE);
                final TextView cashTxt1 = (TextView) Cashier.dialog.findViewById(R.id.cashTextView1);
                final TextView cashTxt2 = (TextView) Cashier.dialog.findViewById(R.id.cashTextView2);

                paypalTxt2.setText(Cashier.calculateCommission(totalSum.getText().toString()));
                cashTxt2.setText(totalSum.getText().toString());

                paypalTxt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setBackgroundPayment(paypalLayout, cashLayout, context, isCheckedArr, 0);
                    }
                });
                paypalTxt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setBackgroundPayment(paypalLayout, cashLayout, context, isCheckedArr, 0);
                    }
                });
                cashTxt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setBackgroundPayment(cashLayout, paypalLayout, context, isCheckedArr, 1);
                    }
                });
                cashTxt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setBackgroundPayment(cashLayout, paypalLayout, context, isCheckedArr, 1);
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cashier.dialog.dismiss();
                    }
                });


                proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //checkHandler(paymentString, isCheckedArr,NO_FLAG,Cashier.ONE);
                        if (isCheckedArr[0])//paypal
                        {
                            Cashier.cateringOrder.remove(paymentString[1]);
                            Cashier.writeToFileCateringOrder(context);
                            Cashier.sharedUpdatePaidAmount(paypalTxt2.getText().toString());
                            Cashier.pay(context, act, paypalTxt2.getText().toString());
                            orderNow.setVisibility(View.INVISIBLE);

                        } else if (isCheckedArr[1]) {
                            Cashier.cateringOrder.put(paymentString[1], new CateringObjectInfo(Cashier.ZERO, Cashier.ONE));
                            Cashier.sharedUpdatePaidAmount(cashTxt2.getText().toString());
                            Cashier.writeToFileCateringOrder(context);
                            orderNow.setVisibility(View.VISIBLE);
                        }


                        Cashier.displayOrderCatering(lisa, totalSum, context, 0);
                        Cashier.dialog.dismiss();
                    }
                });


                Cashier.dialog.show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Cashier.paypalRequestCode)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                Cashier.sharedUpdatePaypalPaid(true);
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirmation != null && confirmation.getProofOfPayment().getState().equals(Cashier.PAYMENT_APPROVED))
                {
                    Log.d("TKT_cart","confirmation approved!");
                    Toast.makeText(context, R.string.cateringPaidConfirm, Toast.LENGTH_SHORT).show();
                    //createOrderTextAndSend();
                    orderNow.setVisibility(View.VISIBLE);

                    }

                    else
                        Toast.makeText(context, "No order exists", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Log.d("TKT_studentOrder","confirmation is null");
                    Toast.makeText(context, "Confirmation failed", Toast.LENGTH_SHORT).show();
                }

            }
        }


    public void createOrderTextAndSend()
    {//generate orderString and send order to a
        String subject = getString(R.string.order) +" " + Cashier.checkPrefs.getString(Cashier.CATERING_CONTACT_INFO_NAME, null) + " - " + Cashier.checkPrefs.getString(Cashier.CATERING_DATE_INFO,null);
       // Log.d("TKT_cart","subject: " + subject);
        Cashier.getCateringOrderFromFile(context);
        String text = subject +" - " + Cashier.checkPrefs.getString(Cashier.CATERING_CONTACT_INFO_PHONE,null)+"\n";
        //Log.d("TKT_cart","text: " + text);
        if(!Cashier.cateringOrder.isEmpty())
        {
            for(Map.Entry<String, CateringObjectInfo> entry : Cashier.cateringOrder.entrySet())
            {
                String temp = Cashier.cateringCartGenerateString(entry.getValue().toString(), entry.getKey().toString(),context) + "\n";
                text += temp;
            }
            //if paid by paypal
            if(Cashier.checkPrefs.getBoolean(Cashier.PAYPAL_PAID_CATERING,false)) {
                subject = getString(R.string.paidMail) + subject;
                text += getString(R.string.paidInPaypal) + Cashier.checkPrefs.getString(Cashier.PAID_AMOUNT, null);
            }
            else {
                subject = getString(R.string.notPaidMail) + subject;
                text += getString(R.string.cashPayment) + Cashier.checkPrefs.getString(Cashier.PAID_AMOUNT, null);
            }

            //Log.d("TKT_cart","text: " + text);
            Log.d("TKT_cart","subject: " + subject);
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","js777755@gmail.com", null));
           // emailIntent.setType("text/plain");
            String to[] = {"js777755@gmail.com"};
            emailIntent.putExtra(Intent.EXTRA_EMAIL,to);
            emailIntent.putExtra(Intent.EXTRA_TEXT,text);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
            startActivity(Intent.createChooser(emailIntent, "שליחת הזמנה..."));


        }

    }

    public void setBackgroundPayment(LinearLayout touched,LinearLayout untouched, Context context, boolean [] isCheckArr, int pos)
    {
        //Log.d("TKT_cart", "setBackgroundPayment touchedTag: " + touched.getTag().toString());
        if(touched.getTag().toString().equals(context.getString(R.string.TAGunchecked)))
        {
            //Log.d("TKT_cart", "THIS");
            isCheckArr[pos] = true;
            isCheckArr[1-pos] = false;
            touched.setTag(context.getString(R.string.TAGchecked));
            untouched.setTag(context.getString(R.string.TAGunchecked));
            touched.setBackground(getDrawable(R.drawable.student_shape));
            untouched.setBackground(getDrawable(R.drawable.shape_contour));

        }
        else
        {
            //Log.d("TKT_cart", "THAT");
            isCheckArr[pos] = false;
            touched.setTag(context.getString(R.string.TAGunchecked));
            touched.setBackground(getDrawable(R.drawable.shape_contour));
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return true;
    }

    @Override
    protected void onResume() {
        Log.d("TKT_cart","onResume=============");
        super.onResume();
        if(Cashier.cateringOrder.containsKey(getString(R.string.cashPayement)) || Cashier.checkPrefs.getBoolean(Cashier.PAYPAL_PAID_CATERING,false))
            orderNow.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        if(id == R.id.paypal)
        {
            //open fee price
            paymentDialog();
            return true;
        }
        return false;
    }

    public String generateDateFormat(int day, int month, int year)
    {
        String dia=day+"", mes=month+"";
        if(day<10)
            dia = "0"+day;
        if(month<10)
            mes = "0"+month;
        if(day == 0)
            return mes+"/"+year%100;
        return dia+"/"+mes+"/"+year%100;
    }
}
