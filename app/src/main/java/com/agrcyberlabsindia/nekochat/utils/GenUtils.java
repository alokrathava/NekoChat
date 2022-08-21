package com.agrcyberlabsindia.nekochat.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

public class GenUtils {

    private static final String TAG = "GenUtils";

    public static boolean isConnectedToInternet(@NonNull Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void IntentActivity(Context context, Class<?> cls) {
        context.startActivity(new Intent(context, cls));
    }

    public static void IntentActivity(Context context, Class<?> cls, String key, String value) {
        Intent intent = new Intent(context, cls);
        intent.putExtra(key, value);
        context.startActivity(intent);
    }

    public static void snackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public static void snackBarActionbar(View view, String message, String action, View.OnClickListener listener) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction(action, listener).show();
    }

    /*Bottom Navigation Bar Click*/
    public static void bottomNavBarClick(Context context, int postionId) {
        switch (postionId) {
            case 0: // Todo: Add Home Activity When Menu is created
                IntentActivity(context, HomeActivity.class);
                break;
            case 1: // Todo: Add Message Activity When Menu is created
                IntentActivity(context, MessageActivity.class);
                break;
            case 2: // Todo: Add Profile Activity When Menu is created
                IntentActivity(context, ProfileActivity.class);
                break;
        }

    }

    public static void IsNull(Context context, String message) {
        if (message == null) {
            showToast(context, "Null");
        }
    }

    public static void IsEmpty(Context context, String message) {
        if (message.isEmpty()) {
            showToast(context, "Empty");
        }
    }

    public static boolean isNumber(String message) {
        try {
            Integer.parseInt(message);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isEmail(String message) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(message).matches();
    }

    public static boolean isPhone(String message) {
        return android.util.Patterns.PHONE.matcher(message).matches();
    }

    public static boolean isValidPassword(String message) {
        if (message.length() < 8) {
            return false;
        } else if (!message.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            return false;
        } else if (message.contains(" ")) {
            return false;
        } else if (message.contains("\n")) {
            return false;
        } else {
            return true;
        }
    }
}
