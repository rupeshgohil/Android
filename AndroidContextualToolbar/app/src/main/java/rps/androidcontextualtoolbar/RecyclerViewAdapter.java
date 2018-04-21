package rps.androidcontextualtoolbar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    public Context mContext;
    public ClickAdapterListener mClickAdapterListener;
    public ArrayList<datamodal> mArray;
    public View view;
    public int CurrentSelectedItem = -1;
    public SparseBooleanArray booleanArray;
    public RecyclerViewAdapter(MainActivity mainActivity, ArrayList<datamodal> arrayList,ClickAdapterListener mListener) {
        this.mArray = arrayList;
        this.mContext = mainActivity;
        this.mClickAdapterListener = mListener;
        booleanArray = new SparseBooleanArray();
    }

    public void TonggleSelection(int pos) {
        CurrentSelectedItem = pos;
        if(booleanArray.get(pos,false)){
            booleanArray.delete(pos);
        }else{
            booleanArray.put(pos,true);
        }
        notifyItemChanged(pos);
    }

    public int SelectedItemCount() {
        return booleanArray.size();
    }

    public void SelectAll() {
        for(int i=0; i<getItemCount();i++){
            booleanArray.put(i,true);
        }
        notifyDataSetChanged();
    }

    public List<Integer> getSelectedItem() {
        List<Integer> list = new ArrayList<>(booleanArray.size());
        for(int i = 0; i<booleanArray.size(); i++){
            list.add(booleanArray.keyAt(i));
        }
        return list;
    }

    public void removeData(Integer integer) {
        mArray.remove(integer);
        Log.e("arrayfterdetelerecord",mArray.toString());
        resetCurrentIndex();
    }
    public void resetCurrentIndex(){
        CurrentSelectedItem = -1;
    }

    public void updateData(Integer integer) {
        mArray.get(integer).setaBoolean(true);
        resetCurrentIndex();
    }

    public void clearSelections() {
        booleanArray.clear();
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public RelativeLayout mRelativeLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.textView);
            mRelativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        String mod = mArray.get(position).getItem();
        holder.tv.setText(mod);
        holder.itemView.setActivated(booleanArray.get(position,false));
        ApplyClickEvents(holder,position);
    }
    public void ApplyClickEvents(MyViewHolder holder,final int pos){
            holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickAdapterListener.OnRowClicked(pos);
                }
            });
        holder.mRelativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickAdapterListener.onRowLongClicked(pos);
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return mArray.size();
    }
}
