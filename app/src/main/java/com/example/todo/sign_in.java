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


public class sign_in extends AppCompatActivity {

    private EditText editEmail, editPass;
    private TextView signUpText, textView2;
    private Button signInBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editEmail = findViewById(R.id.emailEditSignIN);
        editPass = findViewById(R.id.passEditSignIn);
        signUpText = findViewById(R.id.signUpText);
        signInBtn = findViewById(R.id.signInBtn);
        textView2 = findViewById(R.id.textView2);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sign_in.this, sign_up.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        signInBtn.setOnClickListener(view -> {
            loginUser();
        });
    }

    private void loginUser() {
        String email = editEmail.getText().toString();
        String password = editPass.getText().toString();

        if (TextUtils.isEmpty(email)) {
            editEmail.setError("Email cannot be empty");
            editEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            editPass.setError("Email cannot be empty");
            editPass.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(sign_in.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(sign_in.this, MainActivity.class));
                    } else {
                        Toast.makeText(sign_in.this, "login error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}