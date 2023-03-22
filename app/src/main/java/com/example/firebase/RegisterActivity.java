package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailedit, passedit,repassedit;
    private Button btnlogin,btnregis;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        emailedit = findViewById(R.id.email);
        passedit = findViewById(R.id.password);
        repassedit = findViewById(R.id.repass);
        btnlogin = findViewById(R.id.btnlogin);
        btnregis = findViewById(R.id.btnregis);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        String email, pass, repass;
        email = emailedit.getText().toString();
        pass = passedit.getText().toString();
        repass = repassedit.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Please enter pasword!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(repass)){
            Toast.makeText(this, "Please enter re-pasword!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!pass.equals(repass)){
            Toast.makeText(this, "Please enter re-password same password!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Register Successful.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);//chinh acttive nay
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "Register Failed!!!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void login() {
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
    }


}
