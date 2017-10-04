package rupesh.androidpracticaletwo.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Aru on 15-09-2017.
 */

public class Utils {

    public static final String SHAREDPREFERENCE = "SHREDPREFERENCE";
    public static String BaseURL = "http://a-jak.com/api/";

    public static boolean CheckInternetConnection(Context applicationContext) {

        ConnectivityManager cm = (ConnectivityManager) applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(wifiNetwork != null && wifiNetwork.isConnected())
        {
            Log.i("wifi","Connection Avalibale");
            return true;
        }
        NetworkInfo MobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(MobileNetwork != null && MobileNetwork.isConnected())
        {
            Log.i("Mobile","Connection Avalibale");
            return true;
        }
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            Log.i("active network", "connection available");

            return true;
        } else {
           // showToast(applicationContext, "Please check Your Internet Connection");

            return false;
        }

       
    }

    private static void showToast(Context applicationContext, String msg) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show();
    }
    public static void WriteSharePrefrence(Context context, String key,
                                           String value) {
        @SuppressWarnings("static-access")
//        SharedPreferences write_Data = context.getSharedPreferences(
//                Constant.SHRED_PR.SHARE_PREF, context.MODE_PRIVATE);
//        Editor editor = write_Data.edit();
//        editor.putString(key, values);
//        editor.apply();

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String ReadSharePrefrence(Context context, String key) {
//        SharedPreferences read_data = context.getSharedPreferences(
//                Constant.SHRED_PR.SHARE_PREF, context.MODE_PRIVATE);
//
//        return read_data.getString(key, "");

        String data;
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final SharedPreferences.Editor editor = preferences.edit();
        data = preferences.getString(key, "");
        editor.commit();
        return data;
    }

    public static void clearSharedPreferenceData(Context context) {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().clear().commit();

    }
}
