package beks.androidcourse.kz.aida.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import beks.androidcourse.kz.aida.R;

public class ProfileRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> mData;
    private LayoutInflater mInflater;
    private Context context;
    // data is passed into the constructor
    ProfileRecyclerAdapter(Context context, List<String> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_card, parent, false);
        return new MenuViewHolder(view);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.menuItem);
        textView.setText(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,LoginScreen.class);
                Activity activity = (Activity) context;
                activity.startActivity(intent);
            }
        });

    }

    // total number of rows
    public int getItemCount() {
       return mData.size();
    }

}
    // stores and recycles views as they are scrolled off screen

 class MenuViewHolder extends RecyclerView.ViewHolder {
    public MenuViewHolder(View itemView) {
            super(itemView);
        }
    }
