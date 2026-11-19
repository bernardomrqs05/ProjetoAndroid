package com.example.eventlink.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.eventlink.databinding.ItemListaLocalBinding;
import com.example.eventlink.models.Local;

import java.util.ArrayList;
import java.util.Locale;

public class ListaLocaisAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Local> locais;

    public ListaLocaisAdapter(Context context, ArrayList<Local> locais) {
        this.context = context;
        this.locais = locais;
    }

    @Override
    public int getCount() {
        return locais.size();
    }

    @Override
    public Object getItem(int position) {
        return locais.get(position);
    }

    @Override
    public long getItemId(int position) {
        return locais.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemListaLocalBinding binding;
        if (convertView == null) {
            if (inflater == null) {
                inflater = LayoutInflater.from(context);
            }
            binding = ItemListaLocalBinding.inflate(inflater, parent, false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (ItemListaLocalBinding) convertView.getTag();
        }

        Local local = locais.get(position);
        binding.ivCapaLocal.setImageResource(local.getImagemResId());
        binding.tvNomeLocal.setText(local.getNome());
        binding.tvCidadeLocal.setText(local.getCidade() + " • " + local.getMorada());
        binding.tvCapacidadeLocal.setText("Até " + local.getCapacidadeTotal() + " pessoas");
        binding.tvPrecoLocal.setText(String.format(Locale.getDefault(), "%.2f €", local.getPrecoAluguerBase()));

        return convertView;
    }
}