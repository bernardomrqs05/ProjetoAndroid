package com.example.eventlink.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eventlink.DetalhesLocalActivity;
import com.example.eventlink.R;
import com.example.eventlink.adapters.ListaLocaisAdapter;
import com.example.eventlink.databinding.FragmentListaLocaisBinding;
import com.example.eventlink.models.Local;
import com.example.eventlink.models.SingletonGestorEventLink;

import java.util.ArrayList;

public class ListaLocaisFragment extends Fragment {

    private FragmentListaLocaisBinding binding;
    private ArrayList<Local> locais;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentListaLocaisBinding.inflate(inflater, container, false);

        locais = SingletonGestorEventLink.getInstance().getLocais();
        ListaLocaisAdapter adapter = new ListaLocaisAdapter(getContext(), locais);
        binding.lvLocais.setAdapter(adapter);

        binding.lvLocais.setOnItemClickListener((parent, view, position, id) -> {
            Local localSelecionado = locais.get(position);
            Intent intent = new Intent(getContext(), DetalhesLocalActivity.class);
            intent.putExtra(DetalhesLocalActivity.EXTRA_ID_LOCAL, localSelecionado.getId());
            startActivity(intent);
        });

        return binding.getRoot();
    }
}