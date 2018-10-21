package beks.androidcourse.kz.aida.controller;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import beks.androidcourse.kz.aida.R;
import beks.androidcourse.kz.aida.model.User;

public class RegisterScreen extends AppCompatActivity implements View.OnClickListener  {
    private EditText editTextName;
    private EditText editTextSur;
    private EditText editTextMail;
    private EditText editTextPass;
    private EditText editTextRep;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance() ;
    private ProgressDialog progressDialog;
    private TextView help;
    private TextView donor;
    private TextView reg;
    private TextView test;
    private int truth = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
                firebaseAuth = FirebaseAuth.getInstance();
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextSur = (EditText) findViewById(R.id.editTextSur);
        editTextMail = (EditText) findViewById(R.id.editTextmail);
        editTextPass = (EditText) findViewById(R.id.editTextpass1);
        editTextRep=(EditText) findViewById(R.id.editTextpass2);
        progressDialog = new ProgressDialog(this);
        help = (TextView) findViewById(R.id.textViewHelp);
        donor = (TextView) findViewById(R.id.textViewDonor);
        reg=(TextView)findViewById(R.id.textViewReg) ;
        test = (TextView) findViewById(R.id.textViewReg);
        help.setOnClickListener(this);
        donor.setOnClickListener(this);
    }
    private void registerUser(){
        final String email = editTextMail.getText().toString();
        final String password  = editTextPass.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Registering,please Wait...");
        progressDialog.show();
        /*if (truth == 1){
            help.setTextColor(getColor(R.color.green));
            donor.setTextColor(getColor(R.color.appl));
        }
        if (truth == 2){
            donor.setTextColor(getColor(R.color.green));
            help.setTextColor(getColor(R.color.appl));
        }*/
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d("creating user","here");
                            User user = new User();
                            user.setEmail(email);
                            user.setId(firebaseAuth.getCurrentUser().getUid());
                            user.setPassword(password);
                            user.setName(editTextName.getText().toString());
                            user.setSurname(editTextSur.getText().toString());
                            if (donor.getCurrentTextColor() == getColor(R.color.green) && help.getCurrentTextColor() == getColor(R.color.appl)){
                                user.setCategory("donor");
                            }
                            if (help.getCurrentTextColor() == getColor(R.color.green) && donor.getCurrentTextColor() == getColor(R.color.appl)){
                                user.setCategory("help need");
                            }

                            database.getReference("User")
                                    .child(firebaseAuth.getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });
                            finish();
                            if(firebaseAuth.getCurrentUser() != null){
                                finish();
                                if(user.getCategory().trim() == "help") {
                                    startActivity(new Intent(getApplicationContext(), ApplicationScreenActivity.class));
                                }
                                if(user.getCategory().trim() == "donor"){
                                    startActivity(new Intent(getApplicationContext(),PaymentsClass.class));
                                }
                            }
                        }
                        else{
                            Log.d("sdf",task.getException().getMessage());
                            Toast.makeText(RegisterScreen.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    @Override
    public void onClick(View view) {
        if(view == help){
            truth = 1;
            help.setTextColor(getColor(R.color.green));
            donor.setTextColor(getColor(R.color.appl));
        }
        if(view == donor){
            truth = 2;
            donor.setTextColor(getColor(R.color.green));
            help.setTextColor(getColor(R.color.appl));

        }
        if(view == reg) {
            registerUser();
        }
    }
}
