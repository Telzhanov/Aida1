package beks.androidcourse.kz.aida.controller;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import beks.androidcourse.kz.aida.R;
import beks.androidcourse.kz.aida.model.Sicks;
import beks.androidcourse.kz.aida.model.User;

public class HomeFragment extends Fragment {
    @Nullable
    private DatabaseReference myref;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser user;
    private CardViewHolder sickp;
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("User");
        recyclerView = view.findViewById(R.id.recyclerSicksList);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final ArrayList<Sicks> sicksList = new ArrayList<>();
                for(DataSnapshot dsp : dataSnapshot.getChildren()){
                    Sicks sicks = dsp.getValue(Sicks.class);
                    if(sicks.getCategory().equals("help need")){
                        sicksList.add(sicks);
                    }
                    else if(sicks.getCategory().equals("donor")){
                        continue;
                    }
                }
                adapter = new RecycleViewAdapter(sicksList,getContext());
                adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        Intent intent = new Intent(getContext(),ApplicationActivity.class);
                        /*intent.putExtra("position", position);
                        intent.putExtra("model", sicksList.get(position));*/
                        Sicks sicks = new Sicks(sicksList.get(position).getId());
                        intent.putExtra(Sicks.class.getSimpleName(),sicks);
                        startActivity(intent);
                    }
                });
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                /*User newuser = dataSnapshot.getValue(User.class);
                Sicks sick = new Sicks("Hi","hel","","","","","","","");*/
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                User changedUser = dataSnapshot.getValue(User.class);
                System.out.println("The changed user : " + changedUser.getName());

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                User removedUser = dataSnapshot.getValue(User.class);
                System.out.println("The user " + removedUser.getName() + " has been deleted");

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}