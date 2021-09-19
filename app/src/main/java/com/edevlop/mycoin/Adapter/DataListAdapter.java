package com.edevlop.mycoin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.edevlop.mycoin.R;
import com.edevlop.mycoin.Database.DataModel;

import java.util.List;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.MyViewHolder> {

    private Context context;
    private List<DataModel> dataList;
    public DataListAdapter(Context context) {
        this.context = context;
    }

    public void setUserList(List<DataModel> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name.setText(this.dataList.get(position).Name);
        Glide.with(context).load(this.dataList.get(position).Url).into(holder.iv_image);
    }

    @Override
    public int getItemCount() {
        return  this.dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        ImageView iv_image;

        public MyViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            iv_image = view.findViewById(R.id.iv_image);

        }
    }
}
