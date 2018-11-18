package roman.boichenko.ivrit;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class SharedPref {
    static Context context;

    public SharedPref(Context context) {
        this.context = context;
    }

    public static void savePreferencesBoolean(String key, Boolean value) {
        Toast.makeText(context, key + " " + value, Toast.LENGTH_LONG).show();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value).apply();
    }

    /**
     * если нет key вернет false
     */
    public static Boolean getPreferencesBoolean(String keyValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(keyValue, false);
    }


    public static void savePreferenceString(String key, String value) {
        Toast.makeText(context, key + " " + value, Toast.LENGTH_LONG).show();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value).apply();
    }

    /**
     * если нет key вернет пустое значение  ""
     */
    public static String getPreferencesString(String keyValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(keyValue, "");
    }


    public static void removeAllSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
    }


}
