package com.rzjaffery.personaltaskmanagerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.FirebaseApp;
import com.rzjaffery.personaltaskmanagerapp.R;
import com.rzjaffery.personaltaskmanagerapp.viewmodel.AuthViewModel;

public class LoginActivity extends AppCompatActivity {
    private EditText emailField, passwordField;
    private Button loginBtn;
    private AuthViewModel authViewModel;
    private TextView linkToRegister;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            FirebaseApp.initializeApp(this); // Safe init again
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_login);

        emailField = findViewById(R.id.input_email);
        passwordField = findViewById(R.id.input_password);
        loginBtn = findViewById(R.id.btn_login);
        linkToRegister = findViewById(R.id.link_to_register);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        linkToRegister.setOnClickListener(
                v -> {
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }
        );

        loginBtn.setOnClickListener(
                v -> {
                    String email = emailField.getText().toString();
                    String password = passwordField.getText().toString();

                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(this, "Please enter the details to login", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    authViewModel.login(email, password);
                }
        );

        authViewModel.getUser().observe(this, user -> {
            startActivity(new Intent(this, TaskListActivity.class));
            finish();
        });

        authViewModel.getError().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }
}
