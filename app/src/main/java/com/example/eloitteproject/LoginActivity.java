package com.example.eloitteproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    EditText fullName, email, password;
    Button loginBtn, goToRegister;
    boolean valid = true;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_btn);
        goToRegister = findViewById(R.id.go_to_register);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkField(email);
                checkField(password);
                Log.d("TAG", "onClick " + email.getText().toString());

                if(valid){
                    mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText()
                            .toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(LoginActivity.this, "Logged in Successfully",
                                    Toast.LENGTH_SHORT).show();
                            checkUserAccessLevel(authResult.getUser().getUid());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference df = fStore.collection("Users").document(uid);
        //extract the data from the document
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess: " + documentSnapshot.getData());
                //identify the user access level

                if(documentSnapshot.getString("isTeacher") != null) {
                    //user is admin

                    startActivity(new Intent(getApplicationContext(), TeacherHomeActivity.class));
                    finish();
                }

                if(documentSnapshot.getString("isStudent") != null) {
                    startActivity(new Intent(getApplicationContext(), StudentHomeActivity.class));
                    finish();
                }
            }
        });
    }

    public boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()) {
            textField.setError("Error");
            valid = false;
        } else {
            valid = true;

        }
        return valid;
    }
    //check to see if any user has already logged in or not
    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            DocumentReference df = FirebaseFirestore.getInstance().collection("Users")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid());
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(documentSnapshot.getString("isTeacher") != null) {
                        startActivity((new Intent(getApplicationContext(), TeacherHomeActivity.class)));
                        finish();
                    }

                    if(documentSnapshot.getString("isStudent") != null) {
                        startActivity((new Intent(getApplicationContext(), StudentHomeActivity.class)));
                        finish();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();

                }
            });
        }
    }
}