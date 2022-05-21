package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class register extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private Button registerUser;
    private EditText name,phone,email,password,birthday;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dangky:
                register();
                break;
        }
    }

    private void register(){
        String username=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        String fullName=name.getText().toString().trim();
        String date=birthday.getText().toString().trim();
        String phonenumber=phone.getText().toString().trim();
        if(fullName.isEmpty()){
            name.setError(("Ful name is required !"));
            name.requestFocus();
            return;
        }
        if(date.isEmpty()){
            birthday.setError(("Age is required !"));
            birthday.requestFocus();
            return;
        }
        if(phonenumber.isEmpty()){
            phone.setError(("Phone number is required !"));
            phone.requestFocus();
            return;
        }
        if(username.isEmpty()){
            email.setError(("Username is required !"));
            email.requestFocus();
            return;
        }
        if(pass.isEmpty()){
            password.setError(("Password is required !"));
            password.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
            email.setError("Please provide valid email");
            email.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(username,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    User user=new User(fullName,date,username,phonenumber);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(register.this, "sajddddddddddddddddddddddddddd", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(register.this, "sajddddddddddddddddddddddddddd", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth=FirebaseAuth.getInstance();
        registerUser=findViewById(R.id.dangky);
        registerUser.setOnClickListener(this);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phonenumber);
        email=findViewById(R.id.username);
        password=findViewById(R.id.password);
        birthday=findViewById(R.id.date);
    }

}