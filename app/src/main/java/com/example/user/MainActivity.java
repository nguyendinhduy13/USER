package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private EditText email,password;
    private Button loginBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register =(TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
        loginBtn=findViewById(R.id.loginBtn);
        email=findViewById(R.id.username);
        password=findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=mAuth.getCurrentUser();
                if(user!=null){
                    Intent intent=new Intent(MainActivity.this,Home.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailString =email.getText().toString();
                String passwordString=password.getText().toString();

                if(TextUtils.isEmpty(emailString)){
                    email.setError("Email is required");
                }
                if(TextUtils.isEmpty(passwordString)){
                    password.setError("Password is required");
                }
                else
                {

                    mAuth.signInWithEmailAndPassword(emailString,passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent=new Intent(MainActivity.this,Home.class);
                                startActivity(intent);
                                finish();

                            }
                            else {
                                Toast.makeText(MainActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                startActivity(new Intent(this,register.class));
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
        FirebaseUser currentUser = mAuth.getCurrentUser();;

    }

}