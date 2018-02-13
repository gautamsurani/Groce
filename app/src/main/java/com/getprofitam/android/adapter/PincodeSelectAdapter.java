package com.getprofitam.android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getprofitam.android.R;
import com.getprofitam.android.model.PincodelistModel;

import java.util.ArrayList;


public class PincodeSelectAdapter extends RecyclerView.Adapter<PincodeSelectAdapter.ViewHolder> {

    Context context;
    ArrayList<PincodelistModel> listData;
    LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;

    public PincodeSelectAdapter(Context context, ArrayList<PincodelistModel> bean) {

        this.listData = bean;
        this.context = context;
        this.inflater = (LayoutInflater.from(context));

    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view, int i);
    }
    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_arepincode, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final PincodelistModel bean = listData.get(position);
        holder.productSearchtitle.setText(bean.getName());

        holder.productitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position, view, 1);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productSearchtitle;
        LinearLayout productitems;

        public ViewHolder(View v) {
            super(v);

            productSearchtitle = (TextView) v.findViewById(R.id.productSearchtitle);

            productitems = (LinearLayout) v.findViewById(R.id.productitems);
        }
    }


}







