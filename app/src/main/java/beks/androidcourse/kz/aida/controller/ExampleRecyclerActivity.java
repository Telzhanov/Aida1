package beks.androidcourse.kz.aida.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import beks.androidcourse.kz.aida.R;
import beks.androidcourse.kz.aida.model.Sicks;

public class ExampleRecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_recycler);
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setAdapter(new RecycleViewAdapter(new ArrayList<Sicks>()));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
