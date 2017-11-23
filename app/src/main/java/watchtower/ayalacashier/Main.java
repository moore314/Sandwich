package watchtower.ayalacashier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main extends AppCompatActivity {
    //// TODO: 11/20/2017 I set both main and welcome to be launchers; don't forget to remove welcome's after main is all done 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean redirect(View v)
    {
        Intent intent = null;
        if(v.getId() == R.id.cafeteria)
            intent = new Intent(this, Welcome.class);
        if(v.getId() == R.id.catering)
        {
            //redirect to catering
            return false;
        }
        if(v.getId() == R.id.design)
        {
            //redirect to design
            return false;
        }
        //if(intent != null)
        startActivity(intent);
        return true;
    }
}
