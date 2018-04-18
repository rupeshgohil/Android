package aru.jsonsqlite.Adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import aru.jsonsqlite.Interface.RecyclerviewClickListener;
import aru.jsonsqlite.Model.Articale;
import aru.jsonsqlite.R;

/**
 * Created by Aru on 02-04-2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    ArrayList<Articale> arrayarticale = new ArrayList<>();
    public Context mContext;
    View itemview;
    private RecyclerviewClickListener mListener;
    public RecyclerViewAdapter(ArrayList<Articale> articaleArrayList, Context mContext, RecyclerviewClickListener recyclerviewClickListener) {
        this.arrayarticale = articaleArrayList;
        this.mContext = mContext;
        this.mListener = recyclerviewClickListener;
        Log.e("arrayofarticale",""+articaleArrayList.toString());
    }
     public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_prof;
        public ProgressBar mProgressBar;
        public TextView title,aithor,publishedat,description;

        public MyViewHolder(View itemView) {
            super(itemView);

            img_prof = (ImageView)itemView.findViewById(R.id.profile_image);
            title = (TextView)itemView.findViewById(R.id.txt_title);
            aithor = (TextView)itemView.findViewById(R.id.txt_author);
            publishedat = (TextView)itemView.findViewById(R.id.txt_desc);
            description = (TextView)itemView.findViewById(R.id.txt_published);
            mProgressBar = (ProgressBar)itemView.findViewById(R.id.progressBar1);

        }

        public void bind(final MyViewHolder holder, int position, final Articale articale, final RecyclerviewClickListener mListener) {

            holder.title.setText(articale.getTitle());
            holder.aithor.setText(articale.getAuthor());
            holder.description.setText(articale.getDescription());
            holder.publishedat.setText(articale.getPublishedAt());
            Picasso.with(mContext).load(articale.getUrlToimage())
                    .into(holder.img_prof, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            if (holder.mProgressBar != null) {
                                holder.mProgressBar.setVisibility(View.GONE);
                            }
                        }
                        @Override
                        public void onError() {

                        }
                    });
            int a = position % 2;
            if(a == 1){
                final Animation slide_right = AnimationUtils.loadAnimation(mContext,R.anim.slide_right);
                itemview.setAnimation(slide_right);
            }else{
                final Animation slide_right = AnimationUtils.loadAnimation(mContext,R.anim.slide_left);
                itemview.setAnimation(slide_right);
            }
            holder.img_prof.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View view = inflater.inflate(R.layout.popupwindow_loginactivity_signup,null);
                    PopupWindow mPopupWindow = new PopupWindow(view,400,400);
                    ImageView imageView = (ImageView)view.findViewById(R.id.img_pic_profile);
                    TextView tv = (TextView)view.findViewById(R.id.txt_title_popup);
                    Picasso.with(mContext).load(articale.getUrlToimage()).into(imageView);
                    tv.setText(articale.getAuthor());
                    mPopupWindow.setOutsideTouchable(true);
                    mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
                    mPopupWindow.setFocusable(false);
                    mPopupWindow.update();
                    mPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                }
            });
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.Onclick(articale);
                }
            });
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_articleitem_row,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.bind(holder,position,arrayarticale.get(position),mListener);
    }

    @Override
    public int getItemCount() {
        return arrayarticale.size();
    }


}
