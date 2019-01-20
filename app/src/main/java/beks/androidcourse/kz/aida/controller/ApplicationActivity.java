package beks.androidcourse.kz.aida.controller;

import android.app.Application;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import beks.androidcourse.kz.aida.R;
import beks.androidcourse.kz.aida.model.Applications;
import beks.androidcourse.kz.aida.model.Sicks;

public class ApplicationActivity extends AppCompatActivity {
    ArrayList<Sicks> sicksList;
    TextView sickapplname;
    TextView sickapplage;
    TextView helpedNumber;
    TextView moneyCollected;
    TextView moneyTarget;
    TextView diagnosisName;
    TextView historyTextIll;
    TextView guardianParent;
    TextView guardian;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applicationscreen);
        ProgressBar pb = findViewById(R.id.progressBar3);
        pb.setProgress(65);
        /*Bundle b = new Bundle();
        b = getIntent().getExtras();
        int position = b.getInt("position");*/
        //sickapplname.setText();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("User");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final ArrayList<Sicks> sicksList = new ArrayList<>();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Sicks sicks = dsp.getValue(Sicks.class);
                    if (sicks.getCategory().equals("help need")) {
                        sicksList.add(sicks);
                    } else if (sicks.getCategory().equals("donor")) {
                        continue;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        sickapplname = findViewById(R.id.sickapplname);
        sickapplage = findViewById(R.id.sickapplage);
        helpedNumber = findViewById(R.id.helpedNumber);
        moneyCollected = findViewById(R.id.moneyCollected);
        moneyTarget = findViewById(R.id.moneyTarget);
        diagnosisName = findViewById(R.id.diagnosisName);
        historyTextIll = findViewById(R.id.historyTextIll);
        guardianParent = findViewById(R.id.guardianParent);
        guardian = findViewById(R.id.guardian);
        Bundle info = getIntent().getExtras();
        final Sicks sicks1;
        if(info!=null){
            sicks1 = info.getParcelable(Sicks.class.getSimpleName());
            reference = database.getReference().child("User");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot dsp: dataSnapshot.getChildren()){
                        Sicks sicks = dsp.getValue(Sicks.class);
                        if(sicks.getId().equals(sicks1.getId())){
                            sickapplname.setText(sicks.getName() + " " + sicks.getSurname());
                             moneyTarget.setText(sicks.getNeedMoney());
                            diagnosisName.setText(sicks.getDiagnosis());
                            guardian.setText(sicks.getParentName() + " " + sicks.getParentSurname());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        reference = database.getReference().child("Applications");
        final Sicks sicks2;
        sicks2 = info.getParcelable(Sicks.class.getSimpleName());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dsp: dataSnapshot.getChildren()){
                    Applications applications = dsp.getValue(Applications.class);
                    if(applications.getId().equals(sicks2.getId())){
                        diagnosisName.setText(applications.getDiagnosis());
                        guardianParent.setText(applications.getParentName() + " " + applications.getSurname());
                        guardian.setText(applications.getRelative());
                        moneyTarget.setText(applications.getNeedMoney());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void HelpOnClick(View v) {
        Intent intent = new Intent(this, PaymentsClass.class);
        startActivity(intent);
    }

    public void LookClick(View v) {
        Intent intent = new Intent(this, BlogView.class);
        startActivity(intent);
    }
}
