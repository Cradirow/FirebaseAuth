package com.example.user.firebaseauthdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button buttonRegister;

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;
    public static final int REQUEST_LOCATION_CODE = 99;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        progressDialog = new ProgressDialog(MainActivity.this);
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        checkLocationPermission();

        if (firebaseAuth.getCurrentUser() != null)
        {
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), Main2Activity.class));

        }

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);

        buttonRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);
    }

    private void registerUser()
    {
        final String name = editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(name))
        {
            Toast.makeText(this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this, "이메일을 입력하세요요", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        if (TextUtils.isEmpty(password))
        {
            //password is empty
            Toast.makeText(this, "패스워드를 입력하세요", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        //if validation are ok
        //we will first show a progressbar

        progressDialog.setTitle("로딩중...");
        progressDialog.setMessage("등록 중입니다");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    finish();
                    DatabaseReference newPost = mDatabase.push();
                    newPost.child("name").setValue(name);
                    newPost.child("email").setValue(email);
                    startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "등록할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view)
    {
        if (view == buttonRegister)
        {
            registerUser();
        }

        if (view == textViewSignIn)
        {
            //will open login activity here
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


    public boolean checkLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_CODE);
            }
            return false;
        }
        else
        {
            return true;
        }

    }

}
