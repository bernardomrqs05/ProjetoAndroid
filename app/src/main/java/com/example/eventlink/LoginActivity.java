package com.example.eventlink;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventlink.databinding.ActivityLoginBinding;
import com.example.eventlink.models.SingletonGestorEventLink;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    private static final int PASS_MIN = 4;
    private static final String PREFS_NAME = "EventLinkPrefs";
    private static final String KEY_IP_SERVIDOR = "IP_SERVIDOR";
    private static final String IP_DEFAULT = "10.0.2.2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Carregar o IP guardado anteriormente e atualizar o Singleton
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String ipGuardado = prefs.getString(KEY_IP_SERVIDOR, IP_DEFAULT);
        SingletonGestorEventLink.getInstance().setIpServidor(ipGuardado);
    }

    public void onClickLogin(View view) {
        String email = binding.etEmail.getText().toString().trim();
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
        Intent intent = new Intent(this, MenuMainActivity.class);
        intent.putExtra("Email", email);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onClickConfigurarIp(View view) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String ipAtual = prefs.getString(KEY_IP_SERVIDOR, IP_DEFAULT);

        EditText inputIp = new EditText(this);
        inputIp.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_URI);
        inputIp.setHint("Ip do servidor");
        inputIp.setText(ipAtual);
        inputIp.setSelection(ipAtual.length());

        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        int padding = (int) (20 * getResources().getDisplayMetrics().density);
        container.setPadding(padding, padding / 2, padding, 0);
        container.addView(inputIp);

        new AlertDialog.Builder(this)
                .setTitle("IP do Servidor")
                .setMessage("Indicar IP do Servidor")
                .setView(container)
                .setPositiveButton("Guardar", (dialog, which) -> {
                    String novoIp = inputIp.getText().toString().trim();
                    if (novoIp.isEmpty()) {
                        Toast.makeText(this, "O IP não pode estar vazio", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    prefs.edit().putString(KEY_IP_SERVIDOR, novoIp).apply();
                    SingletonGestorEventLink.getInstance().setIpServidor(novoIp);
                    Toast.makeText(this, "Servidor definido: " + novoIp, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", null)
                .show();
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