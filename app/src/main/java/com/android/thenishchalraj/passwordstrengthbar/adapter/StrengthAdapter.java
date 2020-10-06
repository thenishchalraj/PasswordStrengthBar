package com.android.thenishchalraj.passwordstrengthbar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.thenishchalraj.passwordstrengthbar.R;
import com.android.thenishchalraj.passwordstrengthbar.model.StrengthBarModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StrengthAdapter extends RecyclerView.Adapter<StrengthAdapter.MyViewHolder> {
    Context context;
    View view;
    List<String> list;

    public StrengthAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public StrengthAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.strength_layout, viewGroup, false);
        return new StrengthAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StrengthAdapter.MyViewHolder myViewHolder, int i) {

        setClick(myViewHolder,i);
    }

    private void setClick(MyViewHolder myViewHolder, int i) {
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivColor;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivColor = (ImageView) itemView.findViewById(R.id.ivColor);
        }
    }
}
