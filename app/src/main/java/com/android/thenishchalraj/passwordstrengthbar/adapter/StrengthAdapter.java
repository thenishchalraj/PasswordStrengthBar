package com.android.thenishchalraj.passwordstrengthbar.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.thenishchalraj.passwordstrength.StrengthBarModel2;
import com.android.thenishchalraj.passwordstrengthbar.R;
import com.android.thenishchalraj.passwordstrengthbar.StrengthBarModel;

import java.util.List;


public class StrengthAdapter extends RecyclerView.Adapter<StrengthAdapter.MyViewHolder> {
    Context context;
    View view;
    //List<String> list;
    List<StrengthBarModel2> list;
    ChangeColor  changeColor;

    public StrengthAdapter(Context context, List<StrengthBarModel2> list) {
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

    private void setClick(final MyViewHolder myViewHolder, final int i) {
        myViewHolder.ivColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColor.setColor(myViewHolder.ivColor,i);
            }
        });
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

    public interface ChangeColor{

        void setColor(ImageView ivColor, int i);
    }

    public void setColor(ChangeColor changeColor)
    {
        this.changeColor=changeColor;
    }
}
