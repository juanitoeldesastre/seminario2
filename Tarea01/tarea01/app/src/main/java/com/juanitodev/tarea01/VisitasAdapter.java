package com.juanitodev.tarea01;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class VisitasAdapter extends ArrayAdapter<Visita> {
    private Context context;
    private ArrayList<Visita> visitas;

    public VisitasAdapter(Context context, ArrayList<Visita> visitas) {
        super(context, 0, visitas);
        this.context = context;
        this.visitas = visitas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.item_visita, parent, false);
        }

        Visita visita = visitas.get(position);

        TextView tvNombre = listItem.findViewById(R.id.tv_nombre);
        TextView tvEmpresa = listItem.findViewById(R.id.tv_empresa);
        TextView tvProposito = listItem.findViewById(R.id.tv_proposito);
        TextView tvHoraEntrada = listItem.findViewById(R.id.tv_hora_entrada);
        TextView tvHoraSalida = listItem.findViewById(R.id.tv_hora_salida);
        TextView tvEstado = listItem.findViewById(R.id.tv_estado);

        tvNombre.setText(visita.getNombre());
        tvEmpresa.setText("Empresa: " + visita.getEmpresa());
        tvProposito.setText("Propósito: " + visita.getProposito());
        tvHoraEntrada.setText("Entrada: " + visita.getHoraEntrada());

        if (visita.getHoraSalida() != null) {
            tvHoraSalida.setText("Salida: " + visita.getHoraSalida());
            tvHoraSalida.setVisibility(View.VISIBLE);
            tvEstado.setText("FINALIZADA");
            tvEstado.setTextColor(Color.GRAY);
        } else {
            tvHoraSalida.setVisibility(View.GONE);
            tvEstado.setText("ACTIVA");
            tvEstado.setTextColor(Color.GREEN);
        }

        return listItem;
    }
}