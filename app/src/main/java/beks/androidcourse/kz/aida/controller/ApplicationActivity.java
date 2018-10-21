package beks.androidcourse.kz.aida.controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import beks.androidcourse.kz.aida.R;

public class ApplicationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applicationscreen);
    }
    public void HelpOnClick(View v){
        Intent intent = new Intent(this,PaymentsClass.class);
        startActivity(intent);
    }
    public void LookClick(View v){
        Intent intent = new Intent (this,LoginScreen.class);
        startActivity(intent);
    }
}
