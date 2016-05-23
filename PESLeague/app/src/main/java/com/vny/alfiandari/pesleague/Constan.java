package com.vny.alfiandari.pesleague;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by DELL on 5/18/2016.
 */
public class Constan {

    public static void print(String print){
        android.util.Log.e("print", ">>"+(print == null ? "" : print));
    }

    public static void showDialogInfo(Context context, String message) {
        showDialog(context, "Info", message, android.R.drawable.ic_dialog_info);
    }

    public static void showDialog(Context context, String title, String message, int iconId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.setIcon(iconId);
        builder.show();
    }

}
