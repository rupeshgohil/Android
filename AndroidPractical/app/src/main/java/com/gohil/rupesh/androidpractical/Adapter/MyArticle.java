package com.gohil.rupesh.androidpractical.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gohil.rupesh.androidpractical.Activity.Article;
import com.gohil.rupesh.androidpractical.Activity.jsonActivity;
import com.gohil.rupesh.androidpractical.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Aru on 27-07-2017.
 */

public class MyArticle extends RecyclerView.Adapter<MyArticle.ViewHolder> {
    public Context mContext;
    public List<Article> mList;
    public LayoutInflater mInflater;
    public Bitmap Imagebitmap;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView source;
        public TextView author;
        public TextView title;
        public TextView desc;
        public TextView url;
        public ImageView urlToImage;
        public TextView publishedAt;
        public View mView;
        public ProgressBar mProgressBar;
        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            //source = (TextView)itemView.findViewById(R.id.txtsource);
            author = (TextView)itemView.findViewById(R.id.txtauthor);
            title = (TextView)itemView.findViewById(R.id.txttitle);
            desc = (TextView)itemView.findViewById(R.id.txtdescription);
            urlToImage = (ImageView)itemView.findViewById(R.id.imgurltoimage);
            url = (TextView)itemView.findViewById(R.id.txturl);
            publishedAt = (TextView)itemView.findViewById(R.id.txtpublishedAt);
            mProgressBar = (ProgressBar)itemView.findViewById(R.id.progressBar1);
        }
    }
    public MyArticle(jsonActivity jsonActivity, List<Article> arraylist) {
        this.mContext = jsonActivity;
        this.mList = arraylist;
       // mInflater = LayoutInflater.from(mActivity);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        View v = mInflater.inflate(R.layout.jsonwithgson_item_row,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        //holder.source.setText(mList.get(position).getSource());
        holder.author.setText(mList.get(position).getAuthor());
        holder.title.setText(mList.get(position).getTitle());
        holder.desc.setText(mList.get(position).getDescription());
        holder.url.setText(mList.get(position).getUrl());
        holder.publishedAt.setText(mList.get(position).getPublishedAt());
       // holder.source.setText(mList.get(position).getSource());
        //Picasso.with(mContext).load(mList.get(position).getUrlToImage()).into(holder.urlToImage);
        holder.mProgressBar.setVisibility(View.VISIBLE);
        Picasso.with(mContext).load(mList.get(position).getUrlToImage())
                .into(holder.urlToImage, new com.squareup.picasso.Callback() {
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
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


}
