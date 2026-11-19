package com.example.eventlink;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.eventlink.databinding.ActivityMenuMainBinding;
import com.example.eventlink.databinding.NavHeaderMainBinding;
import com.example.eventlink.fragments.ListaLocaisFragment;
import com.google.android.material.navigation.NavigationView;

public class MenuMainActivity extends AppCompatActivity {

    private ActivityMenuMainBinding binding;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMenuMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        carregarToolbar();
        atualizarEmailCabecalho();
        carregarFragmentoInicial();

        binding.btnTerminarSessao.setOnClickListener(v -> {
            Intent intent = new Intent(MenuMainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.navEspacos) {
                    binding.toolbar.setTitle(menuItem.getTitle());
                    carregarFragmento(new ListaLocaisFragment());
                } else if (id == R.id.navPedidos) {
                    binding.toolbar.setTitle(menuItem.getTitle());
                    // Próximo passo: carregarFragmento(new ListaPedidosFragment());
                } else if (id == R.id.navFaq) {
                    enviaremail();
                    binding.toolbar.setTitle(menuItem.getTitle());
                }
                menuItem.setChecked(true);
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }

            private void enviaremail() {
                // TODO intent implicito => ver android developers action_send
            }
        });
    }

    private void carregarFragmentoInicial() {
        carregarFragmento(new ListaLocaisFragment());
        binding.navigationView.setCheckedItem(R.id.navEspacos);
        MenuItem item = binding.navigationView.getMenu().findItem(R.id.navEspacos);
        if (item != null) {
            binding.toolbar.setTitle(item.getTitle());
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(item.getTitle());
            }
        }
    }

    private void atualizarEmailCabecalho() {
        email = getIntent().getStringExtra("Email");
        View headerView;
        if (email != null) {
            if (binding.navigationView.getHeaderCount() > 0) {
                headerView = binding.navigationView.getHeaderView(0);
                NavHeaderMainBinding headerBinding = NavHeaderMainBinding.bind(headerView);
                headerBinding.tvEmail.setText(email);
            }
        }
    }

    private void carregarToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                binding.drawerLayout, binding.toolbar, R.string.menu_abrir,
                R.string.menu_fechar);
        binding.drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        drawerToggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this,
                android.R.color.white));
    }

    private void carregarFragmento(Fragment fragmento) {
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, fragmento)
                .commit();
    }
}