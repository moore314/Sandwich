package watchtower.ayalacashier;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class CateringSalads extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catering_salads);
    }

    public void openSalad(View v)
    {
        final String s1 = getString(R.string.hugeSaladCatering1);
        final String s2 = getString(R.string.hugeSaladCatering2);
        final String s3 = getString(R.string.hugeSaladCatering3);
        final String s4 = getString(R.string.hugeSaladCatering4);
        Cashier.dialog = new Dialog(this);
        Cashier.dialog.setContentView(R.layout.dialog_catering_salad);
        Cashier.dialog.setCanceledOnTouchOutside(false);
        Button proceed = (Button)Cashier.dialog.findViewById(R.id.proceedDialog);
        final CheckBox salad1 = (CheckBox)Cashier.dialog.findViewById(R.id.hugeSalad1);
        final CheckBox salad2 = (CheckBox)Cashier.dialog.findViewById(R.id.hugeSalad2);
        final CheckBox salad3 = (CheckBox)Cashier.dialog.findViewById(R.id.hugeSalad3);
        final CheckBox salad4 = (CheckBox)Cashier.dialog.findViewById(R.id.hugeSalad4);

        if(Cashier.cateringOrder.containsKey(s1))
            salad1.setChecked(true);
        if(Cashier.cateringOrder.containsKey(s2))
            salad2.setChecked(true);
        if(Cashier.cateringOrder.containsKey(s3))
            salad3.setChecked(true);
        if(Cashier.cateringOrder.containsKey(s4))
            salad4.setChecked(true);

        TextView salad1txt = (TextView)Cashier.dialog.findViewById(R.id.hugeSalad1txt);
        TextView salad2txt = (TextView)Cashier.dialog.findViewById(R.id.hugeSalad2txt);
        TextView salad3txt = (TextView)Cashier.dialog.findViewById(R.id.hugeSalad3txt);
        TextView salad4txt = (TextView)Cashier.dialog.findViewById(R.id.hugeSalad4txt);

        salad1txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPressHandler(s1, salad1);
            }
        });
        salad2txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPressHandler(s2, salad2);
            }
        });
        salad3txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPressHandler(s3, salad3);
            }
        });
        salad4txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textPressHandler(s4, salad4);
            }
        });


        salad1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkHandler(s1, isChecked);
            }
        });
        salad2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkHandler(s2, isChecked);
            }
        });
        salad3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkHandler(s3, isChecked);
            }
        });
        salad4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkHandler(s4, isChecked);
            }
        });



        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cashier.dialog.dismiss();
            }
        });


        Cashier.dialog.show();
    }

    public void textPressHandler(String saladType, CheckBox check)
    {
        if(check.isChecked())
            check.setChecked(false);


        else
            check.setChecked(true);
        checkHandler(saladType, check.isChecked());
    }

    public void checkHandler(String saladType, boolean isChecked)
    {
        Log.d("TKT_cateringSalad","checkHandler==============");

        if(isChecked)
        {
            Cashier.cateringOrder.put(saladType, 1);
            Log.d("TKT_cateringSalad","isChecked! length: "+Cashier.cateringOrder.size());
        }
        else
            {
            Cashier.cateringOrder.remove(saladType);
                Log.d("TKT_cateringSalad","is NOT Checked! length: "+Cashier.cateringOrder.size());
        }
    }


}
