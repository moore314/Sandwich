package watchtower.ayalacashier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class StudentOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_order);
    }

    public void setTime(View v)
    {
        Log.d("TKT_studentOrder","setTime");
    }

    public void sandwiches(View v)
    {
        Log.d("TKT_studentOrder","sandwiches");
    }

    public void salads(View v)
    {
        Log.d("TKT_studentOrder","salads");
    }

    public void hots(View v)
    {
        Log.d("TKT_studentOrder","hots");
    }

    public void fruitMeusli(View v)
    {
        Log.d("TKT_studentOrder","fruitMeusli");
    }
}
