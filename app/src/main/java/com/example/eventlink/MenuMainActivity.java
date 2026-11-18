package com.example.eventlink;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.eventlink.R;
import com.example.eventlink.databinding.ActivityMenuMainBinding;
import com.example.eventlink.databinding.NavHeaderMainBinding;
import com.google.android.material.navigation.NavigationView;


public class MenuMainActivity extends AppCompatActivity {

    private ActivityMenuMainBinding binding;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_menu_main);

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

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.navEspacos) {
                    binding.toolbar.setTitle(menuItem.getTitle());
                    //carregarFragmento(new ListaLivrosFragment());
                    //Toast.makeText(MenuMainActivity.this, "Menu Estático", Toast.LENGTH_SHORT).show();
                } else{
                    if(id == R.id.navPedidos) {
                        binding.toolbar.setTitle(menuItem.getTitle());
                        //carregarFragmento(new GrelhaLivrosFragment());

                        //Toast.makeText(MenuMainActivity.this, "Menu Dinamico", Toast.LENGTH_SHORT).show();
                    }
                    else if(id == R.id.navFaq){
                        //criar metodo enviaremail(); com o intent
                        enviaremail();
                        binding.toolbar.setTitle(menuItem.getTitle());
                        //Toast.makeText(MenuMainActivity.this, "Menu Email", Toast.LENGTH_SHORT).show();
                    }
                }
                menuItem.setChecked(true);
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }

            private void enviaremail(){
                //TODO intent implicito => ver android developers action_send
            }
        });
    }

    private void carregarFragmentoInicial() {
        //selecionar o item + alterar o titulo + carregar o fragmento (EstáticoFragment)
        //carregarFragmento(new ListaLivrosFragment());
        binding.navigationView.setCheckedItem(R.id.navEspacos);
        //TODO: não está a tualizar o título da toolbar
        MenuItem item = binding.navigationView.getMenu().findItem(R.id.navEspacos);
        getSupportActionBar().setTitle(item.getTitle());
        //binding.toolbar.setTitle(item.getTitle());
    }

    private void atualizarEmailCabecalho() {
        //email recebido no intent da LoginActivity
        email = getIntent().getStringExtra("Email");
        View headerView;
        if (email != null) {
            if (binding.navigationView.getHeaderCount() > 0) {
                //tenho de aceder ao navigationView para depois conseguir carregar o cabeçalho/header
                headerView = binding.navigationView.getHeaderView(0);
                //a classe binding gerada para o layout nav_header_main => NavHeaderMainBinding
                NavHeaderMainBinding headerBinding = NavHeaderMainBinding.bind(headerView);
                // a partir do headerBinding consigam aceder aos componentes nesse layout
                headerBinding.tvEmail.setText(email);
            }
        }
    }

    private void carregarToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                binding.drawerLayout, binding.toolbar,R.string.menu_abrir,
                R.string.menu_fechar);
        binding.drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        drawerToggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this
                , android.R.color.white));
    }

    private void carregarFragmento(Fragment fragmento){
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer,fragmento)
                .commit();
    }


}

