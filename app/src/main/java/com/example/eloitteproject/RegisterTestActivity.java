package com.example.eloitteproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterTestActivity extends AppCompatActivity {
    EditText fullName, email, password;
    Button registerBtn, goToLogin;
    boolean valid = true;
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_test);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        fullName = findViewById(R.id.et_fullname);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_pass);
        registerBtn = findViewById(R.id.btn_signup);
        goToLogin = findViewById(R.id.btn_login);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(fullName);
                checkField(email);
                checkField(password);

                if (valid) {
                    // start the user registration process
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText()
                            .toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegisterTestActivity.this, "Account Created",
                                    Toast.LENGTH_SHORT).show();
                            DocumentReference df = mStore.collection("Users").document(user.getUid());
                            Map<String, Object> userInfo = new HashMap<>();
                            userInfo.put("FullName", fullName.getText().toString());
                            userInfo.put("UserEmail", email.getText().toString());

                            //specify if the user is admin
                            userInfo.put("isUser", "1"); //identifies normal users as type 1

                            df.set(userInfo);

                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            finish(); //stops user from going back to register activity
                        }
                    }).addOnFailureListener((new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterTestActivity.this, "Failed to Create Account",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }));
                }
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginTestActivity.class));
            }
        });
    }
    public boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()) {
            textField.setError("Error");
            valid = false;
        } else {
            valid = true;

        }return valid;
    }
}



