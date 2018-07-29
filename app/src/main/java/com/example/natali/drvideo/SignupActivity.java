package com.example.natali.drvideo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.UUID;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputName;
    private EditText inputPhone;

    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputPassword = (EditText) findViewById(R.id.password);
        inputEmail = (EditText) findViewById(R.id.email);
        inputName = (EditText) findViewById(R.id.name);
        inputPhone = (EditText) findViewById(R.id.phone);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            // User is logged in
            startActivity(new Intent(SignupActivity.this, MainActivity.class));

        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if(isValidForm() == false){
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    String userId = setUpUser();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.putExtra("user_id", userId);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    private boolean isValidForm(){
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String name = inputName.getText().toString().trim();
        String phone = inputPhone.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("Required.");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            inputPassword.setError("Required.");
            return false;
        }

        if (TextUtils.isEmpty(name)) {
            inputName.setError("Required.");
            return false;
        }

        if (TextUtils.isEmpty(phone)) {
            inputPhone.setError("Required.");
            return false;
        }

        if (password.length() < 6) {
            inputPassword.setError("Password too short, enter minimum 6 characters!");
            return false;
        }
        return true;
    }




    protected String setUpUser(){
        String uid = auth.getUid();
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String name = inputName.getText().toString().trim();
        String phone = inputPhone.getText().toString().trim();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setPhoneNumber(phone);
        user.setStatus(1);
        user.setId(uid);
        user.saveUser();

        return user.getId();
    }
}
