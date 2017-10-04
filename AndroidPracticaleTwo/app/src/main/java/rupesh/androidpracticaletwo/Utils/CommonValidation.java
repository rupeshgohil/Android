package rupesh.androidpracticaletwo.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rupesh.androidpracticaletwo.R;

/**
 * Created by Aru on 21-09-2017.
 */

public class CommonValidation {
    public Activity mActivity;
    public CommonValidation(){

    }
    public static boolean isEditTextEmpty(Object et_username) {
        if(et_username instanceof EditText)
        {
            EditText mEditText = (EditText) et_username;
            if (mEditText.getText().toString().trim().length() > 0) {
                return false;
            }
        }else if(et_username instanceof TextView) {
            TextView mEditText = (TextView) et_username;
            if (mEditText.getText().toString().trim().length() > 0) {
                return false;
            }
        }
        return true;
    }

    public static void ShowTost(Context applicationContext, String s) {
        Toast.makeText(applicationContext,s,Toast.LENGTH_SHORT).show();
    }
    public static boolean isEmailIdInvalid(EditText editText) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(editText.getText().toString().trim());
        return !(matcher.matches());
    }

    public static void AlertDialog(final Activity context, String title, String msg) {

      /* LayoutInflater inflater = context.getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);*/
        final AlertDialog.Builder mAlerBuilder = new AlertDialog.Builder(context);
        mAlerBuilder.setCancelable(true);
        mAlerBuilder.setTitle(title);
        mAlerBuilder.setMessage(msg);
       // mAlerBuilder.setView(alertLayout);
        mAlerBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent setting = new Intent(android.provider.Settings.ACTION_SETTINGS);
                context.startActivity(setting);

            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = mAlerBuilder.create();
        alert.show();
    }
    public static void CustomDialog(final Activity context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.layout_custom_dialog);

        TextView exit = (TextView)dialog.findViewById(R.id.txt_Exit);
        TextView yes = (TextView)dialog.findViewById(R.id.txt_Ok);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setting = new Intent(android.provider.Settings.ACTION_SETTINGS);
                context.startActivity(setting);
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
