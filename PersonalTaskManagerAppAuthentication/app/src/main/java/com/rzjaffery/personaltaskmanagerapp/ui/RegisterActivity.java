package com.rzjaffery.personaltaskmanagerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.rzjaffery.personaltaskmanagerapp.R;
import com.rzjaffery.personaltaskmanagerapp.viewmodel.AuthViewModel;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameField, emailField, passwordField;
    private Button regBtn;
    private AuthViewModel authViewModel;
    private TextView linkToLogin;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameField = findViewById(R.id.input_name);
        emailField = findViewById(R.id.input_email);
        passwordField = findViewById(R.id.input_password);
        regBtn = findViewById(R.id.btn_register);
        linkToLogin = findViewById(R.id.link_to_login);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        linkToLogin.setOnClickListener(
                v -> {
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
        );

        regBtn.setOnClickListener(
                v -> {
                    String name = nameField.getText().toString();
                    String email = emailField.getText().toString();
                    String password = passwordField.getText().toString();

                    if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
                        Toast.makeText(this,"Please fill in the Text",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    authViewModel.register(name,email,password);
                }
        );

        authViewModel.getUser().observe(
                this, user ->{
                    startActivity(new Intent(this, TaskListActivity.class));
                    finish();
                }
        );

        authViewModel.getError().observe(
                this,error -> {
                    Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
                }
        );

    }
}
