package watchtower.ayalacashier;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ItemScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_screen);
    }

    public void drinks(View v)
    {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.drinks_dialog_layout);
        dialog.show();
    }
}
