package com.juanitodev.tarea01_beta;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> visitas;
    private EditText editNombre, editEmpresa, editProposito;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar elementos de UI
        editNombre = findViewById(R.id.edit_nombre);
        editEmpresa = findViewById(R.id.edit_empresa);
        editProposito = findViewById(R.id.edit_proposito);
        Button btnRegistrar = findViewById(R.id.btn_registrar);
        ListView listVisitas = findViewById(R.id.list_visitas);

        // Configuración de lista
        visitas = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, visitas);
        listVisitas.setAdapter(adapter);

        // Listener para botón registrar
        btnRegistrar.setOnClickListener(v -> registrarVisita());

        // Cargar visitas guardadas
        cargarVisitas();
    }

    @Override
    protected void onPause() {
        super.onPause();
        guardarVisitas();
    }

    private void registrarVisita() {
        String nombre = editNombre.getText().toString().trim();
        String empresa = editEmpresa.getText().toString().trim();
        String proposito = editProposito.getText().toString().trim();

        if (nombre.isEmpty() || empresa.isEmpty() || proposito.isEmpty()) {
            mostrarAlerta("Por favor, complete todos los campos.");
        } else {
            String visita = nombre + " - " + empresa + " - " + proposito;
            visitas.add(visita);
            adapter.notifyDataSetChanged();
            mostrarAlerta("Visita registrada con éxito");
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        editNombre.setText("");
        editEmpresa.setText("");
        editProposito.setText("");
    }

    private void mostrarAlerta(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    private void guardarVisitas() {
        SharedPreferences prefs = getSharedPreferences("VisitasApp", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(visitas);
        if (visitas.isEmpty()) {
            editor.remove("visitas_list");
        } else {
            editor.putString("visitas_list", json);
        }
        editor.apply();
    }

    private void cargarVisitas() {
        SharedPreferences prefs = getSharedPreferences("VisitasApp", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString("visitas_list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> listaGuardada = gson.fromJson(json, type);

        if (listaGuardada != null) {
            visitas.addAll(listaGuardada);
            adapter.notifyDataSetChanged();
        }
    }
}