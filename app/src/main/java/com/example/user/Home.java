package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Home extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference budgetRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth=FirebaseAuth.getInstance();
        budgetRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
        budgetRef.child("birthday").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(Home.this, "aaaa", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Home.this, String.valueOf(task.getResult().getValue()), Toast.LENGTH_SHORT).show();
                }
            }
        });
        budgetRef.child("phone").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(Home.this, "aaaa", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Home.this, String.valueOf(task.getResult().getValue()), Toast.LENGTH_SHORT).show();
                }
            }
        });
//        budgetRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String a="aaaaa";
//                if(snapshot.exists())
//                {
//                    for(DataSnapshot ds:snapshot.getChildren()){
//                        Map<String,Object> map=(Map<String, Object>) ds.getValue();
//                        Object total=map.get("birthday");
//                        a=total.toString();
//                    }
//                    Toast.makeText(Home.this, "aaaaaaaaa", Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(Home.this, "bbbbb", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        OnClickReadData();

    }

//    private void OnClickReadData(){
//        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference=firebaseDatabase.getReference();
//        databaseReference.child("Users").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//               User user=snapshot.getValue(User.class);
//               a.setText(user.toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }



}