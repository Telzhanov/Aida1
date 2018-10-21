package beks.androidcourse.kz.aida.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import beks.androidcourse.kz.aida.R;
import beks.androidcourse.kz.aida.model.Sicks;
import beks.androidcourse.kz.aida.model.User;

public class ApplRegistraionActivity extends AppCompatActivity {
    private FirebaseDatabase database=FirebaseDatabase.getInstance() ;
    private EditText sickName;
    private EditText sickSur;
    private EditText parentName;
    private EditText parentSur;
    private EditText relation;
    private EditText diagnosis;
    private EditText needSum;
    private FirebaseUser user ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applicationregistration);
        sickName = findViewById(R.id.editTextSick2);
        sickSur = findViewById(R.id.editTextSickSur2);
        parentName = findViewById(R.id.editTextPar2);
        parentSur = findViewById(R.id.editTextParSur2);
        relation = findViewById(R.id.editTextRel2);
        diagnosis = findViewById(R.id.editDiagnosis2);
        needSum = findViewById(R.id.editTextSum2);
        user = FirebaseAuth.getInstance().getCurrentUser();
        Button button = findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("here","here");
                Sicks sicks = new Sicks();
                sicks.setId(user.getUid());
                sicks.setName(sickName.getText().toString());
                sicks.setSurname(sickSur.getText().toString());
                sicks.setParentName(parentName.getText().toString());
                sicks.setParentSurname(parentSur.getText().toString());
                sicks.setRelative(relation.getText().toString());
                sicks.setNeedMoney(needSum.getText().toString());
                sicks.setDiagnosis(diagnosis.getText().toString());
                DatabaseReference mref = database.getReference("Applications");
                String sickId = mref.push().getKey();
                mref.child(sickId).setValue(sicks);
                mref
                        .child(sickId)
                        .setValue(sicks).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(getApplicationContext(),ApplicationScreenActivity.class);
                        startActivity(intent);
                    }
                    public void onFailure(Task<Void> task){
                        Intent intent = new Intent(getApplicationContext(),LoginScreen.class);
                        startActivity(intent);
                    }
                });
            }
        });

    }
}
