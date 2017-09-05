package watchtower.ayalacashier;

import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Moore on 9/5/2017.
 */

public class Cashier {

    protected static SharedPreferences checkPrefs;
    public static SharedPreferences.Editor progressEdit;
    //====== sharedPref ======
    public static final String EMPLOYEE_NAME = "employeeName";
    public static final String PASSWORD = "sandwich";
    public static final String CHECK_PREFS = "cashier";


    public static void updateEmployee(String employeeName)
    {
        Log.d("TKT_cashier","updateEmployee");
        progressEdit = checkPrefs.edit();
        progressEdit.putString(EMPLOYEE_NAME, employeeName);
        progressEdit.commit();
    }




}
