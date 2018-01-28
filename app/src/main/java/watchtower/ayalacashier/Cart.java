package watchtower.ayalacashier;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
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
                if(entry.equals(getString(R.string.deliveryCatering)))
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

    public void changeAmountDialog(final String entry, final int position, boolean flag)
    {
        Log.d("TKT_cart","changeAmountDialog=======");
        //// TODO: 1/28/2018 finish this; handle the finish button for erasing delivery 
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
            amountTxt.setText(getString(R.string.cancelDelivery));
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
                deleteEntry(entry);
                Cashier.dialog.dismiss();
            }
        });

        Cashier.dialog.show();
    }

    public void deleteEntry(String entry)
    {
        String [] entrySplit = entry.split(" :: ");
        Log.d("TKT_cart","deleteEntry.entrySplit[1]: "+entrySplit[1]);
        CateringObjectInfo temp = Cashier.cateringOrder.remove(entrySplit[1]);
        Cashier.displayOrderCatering(lisa, totalSum, context,temp.getPrice());
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
}
