package beks.androidcourse.kz.aida.controller;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import beks.androidcourse.kz.aida.R;
import beks.androidcourse.kz.aida.model.User;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView signin;
    private TextView register;
    private FirebaseAuth firebaseAuth;
    private TextView forgotpassword;
    private DatabaseReference reference;
    private FirebaseDatabase database;
    private String safe;
    private ProgressDialog progressDialog;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        setContentView(R.layout.login_screen);
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("User");
        firebaseAuth = FirebaseAuth.getInstance();
        editTextEmail = (EditText) findViewById(R.id.editTextmail);
        editTextPassword = (EditText) findViewById(R.id.editTextpass);
        signin = (TextView) findViewById(R.id.textViewlog);
        register  = (TextView) findViewById(R.id.register);
        forgotpassword = (TextView) findViewById(R.id.forgotpasswordtext);
        progressBar = findViewById(R.id.wait);
        signin.setOnClickListener(this);
        forgotpassword.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            reference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for(DataSnapshot dsp:dataSnapshot.getChildren()){
                                        User sicks = dsp.getValue(User.class);
                                        if(sicks.getId().equals(FirebaseAuth.getInstance().getUid())){
                                            if(sicks.getCategory().equals("donor")) {
                                                startActivity(new Intent(getApplicationContext(),ApplicationScreenActivity.class));
                                                progressBar.setVisibility(ProgressBar.INVISIBLE);
                                                finish();
                                            }
                                            if(sicks.getCategory().equals("help need")) {
                                                startActivity(new Intent(getApplicationContext(),SicksApplication.class));
                                                progressBar.setVisibility(ProgressBar.INVISIBLE);
                                                finish();
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.d("onError","onError");
                                }
                            });
                        }
                        else{
                            Log.d( "signInWithEmail:failure", task.getException().toString());
                        }
                    }
                });


//        if(firebaseAuth.getCurrentUser() != null){
//            reference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    for(DataSnapshot dsp:dataSnapshot.getChildren()){
//                        User sicks = dsp.getValue(User.class);
//                        if(sicks.getId().equals(FirebaseAuth.getInstance().getUid())){
//                            if(sicks.getCategory().equals("donor")) {
//                                startActivity(new Intent(getApplicationContext(),ApplicationScreenActivity.class));
//                                finish();
//                            }
//                            if(sicks.getCategory().equals("help need")) {
//                                startActivity(new Intent(getApplicationContext(),ApplRegistraionActivity.class));
//                                finish();
//                            }
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    Log.d("onError","onError");
//                }
//            });
//        }


    }

    @Override
    public void onClick(View view) {
        if(view == signin){
            progressBar.setVisibility(ProgressBar.VISIBLE);
            userLogin();
        }
        if(view == register){
            finish();
            startActivity(new Intent(this, RegisterScreen.class));
        }
        if(view == forgotpassword){
            String email = editTextEmail.getText().toString().trim();
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                return;
            }
            firebaseAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginScreen.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginScreen.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }


        }
}
