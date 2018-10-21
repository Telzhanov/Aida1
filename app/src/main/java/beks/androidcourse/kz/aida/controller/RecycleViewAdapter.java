package beks.androidcourse.kz.aida.controller;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.UserInfo;

import junit.framework.Test;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import beks.androidcourse.kz.aida.R;
import beks.androidcourse.kz.aida.model.Sicks;
import beks.androidcourse.kz.aida.model.User;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    ArrayList<User> sicksList;
    Context context ;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false));
    }
    public RecycleViewAdapter(ArrayList<User>sicksList,Context context){
        this.sicksList = sicksList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView parentName = holder.itemView.findViewById(R.id.parentName);
        TextView sickName = holder.itemView.findViewById(R.id.nameSick);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       // parentName.setText(sicksList.get(position).getParentName());
        sickName.setText(sicksList.get(position).getName());
        parentName.setText(sicksList.get(position).getSurname());
        //sickName.setText(user.getUid());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ApplRegistraionActivity.class);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        Log.d("sickSize",sicksList.toString());
        return sicksList.size();


    }
}
class CardViewHolder extends RecyclerView.ViewHolder{
    public View view;
    public TextView txt;
    public CardViewHolder(View itemView) {
        super(itemView);
    }
}

