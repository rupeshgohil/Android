package androidtest.androidtestwork.Activity;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

import androidtest.androidtestwork.Interface.ResponceStatusListener;
import androidtest.androidtestwork.Utilitiy.Utils;

/**
 * Created by Aru on 07-04-2018.
 */

public class OpenHttpConnectionSoap extends AsyncTask<Void,String,String>{
    public Context mContext;
    public HashMap<String,String> map = new HashMap<>();
    ResponceStatusListener mResponceStatusListener;
    private static final String TAG = "login response";
    public OpenHttpConnectionSoap(Context mContext, ResponceStatusListener responceStatusListener) {
        this.mContext = mContext;
        this.mResponceStatusListener = responceStatusListener;
        Log.e("call","call");
    }

    @Override
    protected String doInBackground(Void... params) {
        Log.e("call","call");
        SoapObject soap_obj = new SoapObject(Utils.NAMESPACESOAP,Utils.METHODSOAP);
        soap_obj.addProperty("eid", "213");
        soap_obj.addProperty("user_name", "rupeshgohel007@gmail.com");
        soap_obj.addProperty("pass", "123456");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(soap_obj);
        try{
            Log.e("call2","call2");
            HttpTransportSE httpTransportSE = new  HttpTransportSE(Utils.BASEURLSOAP,90000);
            httpTransportSE.call(Utils.ACTIONSOAP,envelope);
            SoapPrimitive soapPrimitive = (SoapPrimitive)envelope.getResponse();
            String response = soapPrimitive.toString();
            Log.e("BaseResponce",response);
            JSONObject jobj = new JSONObject(response);
           // mResponceStatusListener.OnSucess(jobj);
        } catch (NumberFormatException e) {
            Log.e(TAG, "NumberFormatException", e);
        } catch (IOException e) {
            Log.e(TAG, "IOException", e);
        } catch (XmlPullParserException e) {
            Log.e(TAG, "XmlPullParserException", e);
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e(TAG, "ArrayIndexOutOfBoundsException", e);
        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
        }
        return null;
    }
}
