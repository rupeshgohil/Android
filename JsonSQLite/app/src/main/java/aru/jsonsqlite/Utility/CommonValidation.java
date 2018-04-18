package aru.jsonsqlite.Utility;

import android.media.MediaCodec;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Created by Aru on 24-03-2018.
 */

public class CommonValidation {
    public boolean isValidation(String editText){
        if(editText != null){
            if(editText.length() >5){
                return false;
            }
            return true;
        }else{
            return false;
        }

    }

    public boolean isValidEmail(String edt_email) {

        String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(TextUtils.isEmpty(edt_email) && edt_email.matches(emailpattern)) {
                return true;
        }else{
            return false;
        }
    }

    public boolean isValidContact(String editText) {
        if(editText != null){
            if(editText.length() >10){
                return false;
            }
            return true;
        }else{
            return false;
        }
    }
}
