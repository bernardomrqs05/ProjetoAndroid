package com.example.eventlink;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eventlink.databinding.ActivityDetalhesLocalBinding;
import com.example.eventlink.models.Local;
import com.example.eventlink.models.SingletonGestorEventLink;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.Locale;

public class DetalhesLocalActivity extends AppCompatActivity {

    public static final String EXTRA_ID_LOCAL = "ID_LOCAL";
    private ActivityDetalhesLocalBinding binding;
    private Local local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityDetalhesLocalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        configurarToolbar();
        carregarDadosLocal();
        carregarServicosExtra();

        binding.btnReservar.setOnClickListener(v -> {
            if (local != null) {
                Toast.makeText(this, "A iniciar reserva para: " + local.getNome(), Toast.LENGTH_SHORT).show();
                // Próximo passo: abrir CriarEventoActivity passando local.getId()
            }
        });
    }

    private void configurarToolbar() {
        setSupportActionBar(binding.toolbarDetalhes);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("EventLink");
        }
        if (binding.toolbarDetalhes.getNavigationIcon() != null) {
            binding.toolbarDetalhes.getNavigationIcon().setTint(
                    ContextCompat.getColor(this, android.R.color.white));
        }
        binding.toolbarDetalhes.setNavigationOnClickListener(v -> finish());
    }

    private void carregarDadosLocal() {
        int idLocal = getIntent().getIntExtra(EXTRA_ID_LOCAL, -1);
        local = SingletonGestorEventLink.getInstance().getLocal(idLocal);

        if (local == null) {
            Toast.makeText(this, "Erro ao carregar o espaço", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        binding.ivDetalheCapa.setImageResource(local.getImagemResId());
        binding.tvDetalheNome.setText(local.getNome());
        binding.tvDetalheLocalizacao.setText(local.getCidade() + " - " + local.getMorada() + " (" + local.getCodigoPostal() + ")");
        binding.tvDetalheAvaliacao.setText(String.format(Locale.getDefault(), "Avaliação (%.1f)", local.getAvaliacao()));
        binding.tvDetalheCapacidade.setText("Capacidade máxima: " + local.getCapacidadeTotal());
        binding.tvDetalhePrecoDia.setText(String.format(Locale.getDefault(), "Desde %.0f€ / Dia +", local.getPrecoAluguerBase()));
        binding.tvDetalhePrecoPessoa.setText("25€ / Pessoa");
        binding.tvDetalheDescricao.setText(local.getDescricao());
    }

    private void carregarServicosExtra() {
        ArrayList<String> extras = SingletonGestorEventLink.getInstance().getServicosExtraFicticios();
        binding.cgServicosExtra.removeAllViews();

        for (String extra : extras) {
            Chip chip = new Chip(this);
            chip.setText(extra);
            chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#E6E6E6")));
            chip.setTextColor(Color.BLACK);
            chip.setChipStrokeWidth(0f);
            chip.setChipCornerRadius(50f);
            binding.cgServicosExtra.addView(chip);
        }
    }
}