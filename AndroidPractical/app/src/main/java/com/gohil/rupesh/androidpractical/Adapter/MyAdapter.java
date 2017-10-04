package com.gohil.rupesh.androidpractical.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gohil.rupesh.androidpractical.Activity.Population;
import com.gohil.rupesh.androidpractical.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Aru on 16-07-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public List<Population> value= Collections.emptyList();
    public Context mContext;
    public LayoutInflater inflater;
    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView IdTextView;
        public TextView NameTextView;
        public TextView PhoneNumberTextView;
        public TextView SubjectTextView;
        public View view;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            /*rank = (TextView)view.findViewById(R.id.txtrank);
            population = (TextView)view.findViewById(R.id.txtpopulation);
            flag = (ImageView)view.findViewById(R.id.flag);
*/
            IdTextView = (TextView) itemView.findViewById(R.id.textView2) ;
            NameTextView = (TextView) itemView.findViewById(R.id.textView4) ;
            PhoneNumberTextView = (TextView) itemView.findViewById(R.id.textView6) ;
            SubjectTextView = (TextView) itemView.findViewById(R.id.textView8) ;
        }
    }
    public MyAdapter(Context con,List<Population> arraylist) {
        this.value = arraylist;
        this.mContext = con;
        inflater= LayoutInflater.from(mContext);
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.toolbarjson,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        //Population pop = value.get(position);
            /*holder.rank.setText(value.get(position).getRank());
            holder.population.setText(value.get(position).getPopulation());
           // Picasso.with(mContext).load(value.get(position).getFlag()).resize(70, 70).into(holder.flag);
*/

        holder.NameTextView.setText(value.get(position).getname());

        holder.IdTextView.setText(String.valueOf(value.get(position).getid()));

        holder.PhoneNumberTextView.setText(value.get(position).getphone_number());

        holder.SubjectTextView.setText(value.get(position).getSubject());
    }

    @Override
    public int getItemCount() {
        return value.size();
    }
}
