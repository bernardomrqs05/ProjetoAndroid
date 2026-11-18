package com.example.eventlink;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventlink.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private static final int PASS_MIN = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void onClickLogin(View view) {
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        if (!isEmailValido(email)) {
            binding.etEmail.setError("Email invalido");
            return;
        }
        if (!isPasswordValida(password)) {
            binding.etPassword.setError("Password invalida");
            return;
        }
        Toast.makeText(this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MenuMainActivity.class);
        intent.putExtra("Email",email);
        startActivity(intent);
        finish();
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public boolean isEmailValido(String email) {
        if (email == null)
            return false;
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPasswordValida(String password) {
        if (password == null)
            return false;
        return password.length() >= PASS_MIN;
    }


}