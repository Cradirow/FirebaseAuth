package com.example.user.firebaseauthdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserName;
    private Button buttonLogout;

    private DatabaseReference databaseReference;
    private EditText resetPassword, resetPasswordRe;
    private Button buttonSave;

    public static final String TAG = "HELPLIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference().child("UserInfo");

        resetPassword = (EditText) findViewById(R.id.resetPassword);
        resetPasswordRe = (EditText)findViewById(R.id.resetPasswordRe);
        buttonSave = (Button)findViewById(R.id.buttonSave);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewUserName = (TextView) findViewById(R.id.textViewUserName);
        textViewUserName.setText(" Welcome " + user.getDisplayName());
        buttonLogout = (Button)findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
    }

    private void saveUserInformation(){
        String password = resetPassword.getText().toString().trim();
        String passwordRe = resetPasswordRe.getText().toString().trim();

        if(password == passwordRe){
            FirebaseUser user = firebaseAuth.getCurrentUser();
            user.updatePassword(password)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Log.d(TAG, "user password updated.");
                            }
                        }
                    });
        }

        Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        if(view == buttonSave){
            saveUserInformation();
        }
    }
}
