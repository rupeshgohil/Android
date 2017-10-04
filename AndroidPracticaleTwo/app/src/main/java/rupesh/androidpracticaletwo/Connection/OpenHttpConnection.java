package rupesh.androidpracticaletwo.Connection;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import rupesh.androidpracticaletwo.Interface.Listner;

/**
 * Created by Aru on 23-09-2017.
 */

public class OpenHttpConnection extends AsyncTask<Void,Void,Void> {
    public Context mContext;
    public String strurl;
    private  Listner mListner;
    private BufferedReader br = null;
    StringBuilder sb = new StringBuilder();
    String line;
    public OpenHttpConnection(Context applicationContext, String strUrl, Listner listner) {
        this.mContext = applicationContext;
        this.strurl = strUrl;
        this.mListner = listner;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(Void... params) {
        URL url = null;
        try
        {
            url = new URL(strurl);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setReadTimeout(10000);
            urlconnection.setConnectTimeout(15000);
            urlconnection.setDoOutput(false);
            urlconnection.setRequestMethod("POST");
            int status = urlconnection.getResponseCode();
            Log.i("STATUS",String.valueOf(status));
            try{
                InputStream in;
                in = new BufferedInputStream(urlconnection.getInputStream());
                br = new BufferedReader(new InputStreamReader(in));
                while ((line = br.readLine()) != null){
                    sb.append(line);
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }finally {
                urlconnection.disconnect();
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        if(sb.toString() != null){
            Log.i("REGISTER_RESPONSE",sb.toString());
        }
        super.onPostExecute(aVoid);
    }
}
