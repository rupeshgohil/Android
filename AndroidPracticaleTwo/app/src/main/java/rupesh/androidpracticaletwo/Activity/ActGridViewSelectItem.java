package rupesh.androidpracticaletwo.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rupesh.androidpracticaletwo.R;

/**
 * Created by Aru on 18-11-2017.
 */

public class ActGridViewSelectItem extends AppCompatActivity{

    @BindView(R.id.btnUpload)
    Button btnUpload;
    @BindView(R.id.noImage)
    TextView txt_noImage;
    @BindView(R.id.btnPicture) Button btnPicture;
    GridView gv;
    private ProgressDialog dialog;
    MultipartEntity entity;
    int count = 0;
    public ArrayList<String> map = new ArrayList<String>();
    Bundle b;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_gridviewselectitem);
        ButterKnife.bind(this);
        gv = (GridView) findViewById(R.id.gridview);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        b = getIntent().getExtras();
        gv.setAdapter(new ImageAdapter(this));

        if (b != null) {
            ArrayList<String> ImgData = b.getStringArrayList("IMAGE");
            for (int i = 0; i < ImgData.size(); i++) {
                map.add(ImgData.get(i).toString());
            }
        } else {
            txt_noImage.setVisibility(View.VISIBLE);
        }
    }
    @OnClick({R.id.btnPicture,R.id.btnUpload})
    public void btnpicture(View v) {
        switch (v.getId())
        {
            case R.id.btnPicture:
                SelectPicture();
                break;
            case R.id.btnUpload:
                UploadOnServer();
                break;
        }
    }
    public void SelectPicture(){
        Intent i3 = new Intent(ActGridViewSelectItem.this, UploadActivity.class);
        startActivity(i3);
    }
    public void UploadOnServer(){
        new ImageUploadTask()
                .execute(count + "", "pk" + count + ".jpg");
    }
    class ImageUploadTask extends AsyncTask<String, Void, String> {

        String sResponse = null;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            dialog = ProgressDialog.show(ActGridViewSelectItem.this, "Uploading",
                    "Please wait...", true);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {

                String url = "your webservice url";
                int i = Integer.parseInt(params[0]);
                Bitmap bitmap = decodeFile(map.get(i));
                //HttpClient httpClient = new DefaultHttpClient();
               // HttpContext localContext = new BasicHttpContext();
               // HttpPost httpPost = new HttpPost(url);
                //entity = new MultipartEntity();

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                byte[] data = bos.toByteArray();

                //entity.addPart("user_id", new StringBody("199"));
                //entity.addPart("club_id", new StringBody("10"));
               // entity.addPart("club_image", new ByteArrayBody(data,"image/jpeg", params[1]));

               // httpPost.setEntity(entity);
               // HttpResponse response = httpClient.execute(httpPost,localContext);
                //sResponse = EntityUtils.getContentCharSet(response.getEntity());

                System.out.println("sResponse : " + sResponse);
            } catch (Exception e) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Log.e(e.getClass().getName(), e.getMessage(), e);

            }
            return sResponse;
        }

        @Override
        protected void onPostExecute(String sResponse) {
            try {
                if (dialog.isShowing())
                    dialog.dismiss();

                if (sResponse != null) {
                    Toast.makeText(getApplicationContext(),
                            sResponse + " Photo uploaded successfully",
                            Toast.LENGTH_SHORT).show();
                    count++;
                    if (count < map.size()) {
                        new ImageUploadTask().execute(count + "", "hm" + count
                                + ".jpg");
                    }
                }

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(),
                        Toast.LENGTH_LONG).show();
                Log.e(e.getClass().getName(), e.getMessage(), e);
            }

        }
    }

    public Bitmap decodeFile(String filePath) {
        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, o);
        // The new size we want to scale to
        final int REQUIRED_SIZE = 1024;
        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, o2);
        return bitmap;
    }

    private class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return map.size();
        }

        public Object getItem(int position) {
            return null;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) { // if it's not recycled, initialize some
                // attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85,
                        Gravity.CENTER));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setPadding(1, 1, 1, 1);

            } else {
                imageView = (ImageView) convertView;
            }

            imageView
                    .setImageBitmap(BitmapFactory.decodeFile(map.get(position)));
            return imageView;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        ActGridViewSelectItem.this.finish();
    }
}
