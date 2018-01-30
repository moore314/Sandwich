package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

public class Cart extends AppCompatActivity {

    ListView lisa;
    TextView totalSum;
    int chosenAmount = 1;//to be used in numnerPicker
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //// TODO: 1/11/2018 consider not using listView but a view similar to studentOrder
        //adapter = new ArrayAdapter<String>(this, R.layout.list_item,R.id.item_text,data_array);

        context = this;
        totalSum = (TextView)findViewById(R.id.altogetherTextCatering);
        lisa = (ListView)findViewById(R.id.orderListCatering);
        Cashier.displayOrderCatering(lisa, totalSum, context,0);
        lisa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                String entry = lisa.getItemAtPosition(position).toString();
                if(entry.equals(getString(R.string.deliveryCatering)) || entry.equals(getString(R.string.rentThePlace)))
                {
                    changeAmountDialog(entry, position, true);
                }
                else {
                    Log.d("TKT_cart", "onCreate.entry: " + entry);
                    changeAmountDialog(entry, position, false);
                }
                return true;
            }
        });


    }

    public void changeAmountDialog(final String entry, final int position, final boolean flag)
    {
        Log.d("TKT_cart","changeAmountDialog=======");
        chosenAmount = 1;
        Cashier.dialog = new Dialog(this);
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
        final String [] paymentString = {getString(R.string.paypalPayement), getString(R.string.cashPayement)};
        final boolean [] isCheckedArr = new boolean[2];
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        Cashier.dialog.setContentView(R.layout.dialog_final_price);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.check);
        Button cancel = (Button)Cashier.dialog.findViewById(R.id.cancelCheck);
        final LinearLayout paypalLayout = (LinearLayout)Cashier.dialog.findViewById(R.id.paypalLinearLayout);
        final LinearLayout cashLayout = (LinearLayout)Cashier.dialog.findViewById(R.id.LinearLayoutCash);

        final TextView paypalTxt1 = (TextView)Cashier.dialog.findViewById(R.id.paypalTextView1);
        final TextView paypalTxt2 = (TextView)Cashier.dialog.findViewById(R.id.paypalTextView2);
        final TextView cashTxt1 = (TextView)Cashier.dialog.findViewById(R.id.cashTextView1);
        final TextView cashTxt2 = (TextView)Cashier.dialog.findViewById(R.id.cashTextView2);

        paypalTxt2.setText(Cashier.calculateCommission(totalSum.getText().toString()));
        cashTxt2.setText(totalSum.getText().toString());


        /*
        if(Cashier.cateringOrder.containsKey(paymentString[1]))
            setBackgroundPayment(cashLayout,context,isCheckedArr,1);
        */




        paypalTxt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundPayment(paypalLayout,cashLayout,context,isCheckedArr,0);
            }
        });
        paypalTxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundPayment(paypalLayout,cashLayout, context,isCheckedArr,0);
            }
        });
        cashTxt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundPayment(cashLayout,paypalLayout,context,isCheckedArr,1);
            }
        });
        cashTxt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundPayment(cashLayout,paypalLayout,context,isCheckedArr,1);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cashier.dialog.dismiss();
            }
        });


        /*
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkHandler(paymentString, isCheckedArr,NO_FLAG,Cashier.ONE);
                Cashier.dialog.dismiss();
            }
        });
        */


        Cashier.dialog.show();
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

}
