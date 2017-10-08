package com.zeowls.ajmanded;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zeowls.ajmanded.screens.dashboard.DashBoardActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = findViewById(R.id.login_btn);
        EditText username = findViewById(R.id.username_input);
        EditText password = findViewById(R.id.password_input);
        login.setOnClickListener(view -> {
            if (username.getText().toString().isEmpty())
                Toast.makeText(this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
            else if (password.getText().toString().isEmpty())
                Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_SHORT).show();
            else {
                MyApplication.get(LoginActivity.this).addUser(username.getText().toString(), password.getText().toString());
                startActivity(new Intent(LoginActivity.this, DashBoardActivity.class));
                finish();
            }
        });
    }
}
