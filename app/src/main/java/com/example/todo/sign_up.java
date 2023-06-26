package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class sign_up extends AppCompatActivity {


    private EditText editEmail , editPass;
    private TextView signInText;
    private Button signUpBtn;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editEmail = findViewById(R.id.editEmailSignUp);
        editPass = findViewById(R.id.editPassSignUp);
        signInText = findViewById(R.id.signInText);
        signUpBtn = findViewById(R.id.signUpBtn);

        mAuth = FirebaseAuth.getInstance();

        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(sign_up.this, sign_in.class);
                startActivity(intent);
            }
        });
        signUpBtn.setOnClickListener(View ->{
            createUser();
        });
    }

    private void createUser() {
        String email = editEmail.getText().toString();
        String password = editPass.getText().toString();

        if(TextUtils.isEmpty(email)){
            editEmail.setError("Email cannot be empty");
            editEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            editPass.setError("Email cannot be empty");
            editPass.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(sign_up.this, "User registers successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(sign_up.this, sign_in.class));
                    }else{
                        Toast.makeText(sign_up.this, "registers error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}