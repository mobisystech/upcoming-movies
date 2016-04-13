package assignment.mobisys.com.assignment.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;

import assignment.mobisys.com.assignment.R;


/**
 * Created by priyank on 1/8/16.
 */
public class DialogUtil {

    public static void showErrorDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public static void showDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog alertDialog;
        builder.setMessage(message);
        builder.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.show();
    }

    public static void showDialogWithOkAndCancel(Context context, String title, String message, DialogInterface.OnClickListener okListner, DialogInterface.OnClickListener cancelListner) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Yes", okListner);

        builder.setNegativeButton("No", cancelListner);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
