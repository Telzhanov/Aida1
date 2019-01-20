package beks.androidcourse.kz.aida.controller;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;
import beks.androidcourse.kz.aida.R;
import beks.androidcourse.kz.aida.model.Sicks;
import beks.androidcourse.kz.aida.model.User;
public class BlogViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    ArrayList<Sicks> sicksList;
    Context context ;
    View.OnClickListener mOnClickListener;
    int position;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return new CardViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false));

    }
    public BlogViewAdapter(ArrayList<Sicks>sicksList,Context context){
        this.sicksList = sicksList;
        this.context = context;
    }
    interface OnItemClickListener{
        void onClick(int position);

    }
    OnItemClickListener onItemClickListener = null;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        TextView parentName = holder.itemView.findViewById(R.id.parentName);
        TextView sickName = holder.itemView.findViewById(R.id.nameSick);
        TextView description = holder.itemView.findViewById(R.id.describe);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ProgressBar pb = holder.itemView.findViewById(R.id.progressBar2);
        parentName.setText(sicksList.get(position).getParentName() + sicksList.get(position).getParentSurname());
        sickName.setText(sicksList.get(position).getName() + " " +  sicksList.get(position).getSurname());
        /*sickName.setText("Азат Махметов");
        parentName.setText("Алия Махметова");*/
        description.setText(sicksList.get(position).getDiagnosis());
/*
        description.setText("Сердечная недостаточность .Хроническая ревматическая болезнь сердца.");
*/
        TextView sickId = holder.itemView.findViewById(R.id.sickId);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClick(position);
                }
                /*Intent intent = new Intent(context, ApplicationActivity.class);
                //intent.putExtra("pos",getItemId(p osition));
                context.startActivity(intent);*/
            }
        });


    }
    @Override
    public int getItemCount() {
        Log.d("sickSize",sicksList.toString());
        return sicksList.size();
    }

}
class CardViewHolderBlog extends RecyclerView.ViewHolder{
    public View view;
    public CardViewHolderBlog(View itemView) {
        super(itemView);
    }
}

