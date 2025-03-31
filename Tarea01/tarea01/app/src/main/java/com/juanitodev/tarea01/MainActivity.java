package com.juanitodev.tarea01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Visita> visitas;
    private EditText editNombre, editEmpresa, editProposito;
    private VisitasAdapter adapter;
    private ListView listVisitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tarea01");

        // Inicializar elementos de UI
        editNombre = findViewById(R.id.edit_nombre);
        editEmpresa = findViewById(R.id.edit_empresa);
        editProposito = findViewById(R.id.edit_proposito);
        Button btnRegistrar = findViewById(R.id.btn_registrar);
        listVisitas = findViewById(R.id.list_visitas);

        // Configuración de lista
        visitas = new ArrayList<>();
        adapter = new VisitasAdapter(this, visitas);
        listVisitas.setAdapter(adapter);

        // Listener para botón registrar
        btnRegistrar.setOnClickListener(v -> registrarVisita());

        // Listener para items de la lista
        listVisitas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarOpcionesVisita(position);
            }
        });

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
            mostrarAlerta("Error", "Por favor, complete todos los campos.");

            // Resaltar campos vacíos
            if (nombre.isEmpty()) {
                editNombre.setError("Campo obligatorio");
            }
            if (empresa.isEmpty()) {
                editEmpresa.setError("Campo obligatorio");
            }
            if (proposito.isEmpty()) {
                editProposito.setError("Campo obligatorio");
            }
        } else {
            // Crear nueva visita con fecha y hora actual
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            String fechaHora = sdf.format(new Date());

            Visita nuevaVisita = new Visita(
                    nombre,
                    empresa,
                    proposito,
                    fechaHora,
                    null
            );

            visitas.add(nuevaVisita);
            adapter.notifyDataSetChanged();

            Snackbar.make(findViewById(android.R.id.content),
                            "Visita registrada con éxito",
                            Snackbar.LENGTH_LONG)
                    .setAction("Deshacer", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            visitas.remove(visitas.size() - 1);
                            adapter.notifyDataSetChanged();
                        }
                    })
                    .setActionTextColor(Color.YELLOW)
                    .show();

            limpiarCampos();
        }
    }

    private void mostrarOpcionesVisita(final int position) {
        final Visita visita = visitas.get(position);
        String[] opciones = {"Registrar salida", "Eliminar registro"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Opciones para " + visita.getNombre());
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // Registrar salida
                        registrarSalida(position);
                        break;
                    case 1: // Eliminar registro
                        eliminarVisita(position);
                        break;
                }
            }
        });
        builder.show();
    }

    private void registrarSalida(int position) {
        Visita visita = visitas.get(position);
        if (visita.getHoraSalida() == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            String horaSalida = sdf.format(new Date());
            visita.setHoraSalida(horaSalida);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Salida registrada para " + visita.getNombre(), Toast.LENGTH_SHORT).show();
            guardarVisitas();
        } else {
            Toast.makeText(this, "Esta visita ya ha sido finalizada", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarVisita(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmar eliminación");
        builder.setMessage("¿Está seguro que desea eliminar este registro?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                visitas.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Registro eliminado", Toast.LENGTH_SHORT).show();
                guardarVisitas();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    private void limpiarCampos() {
        editNombre.setText("");
        editEmpresa.setText("");
        editProposito.setText("");
        editNombre.requestFocus();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        new AlertDialog.Builder(this)
                .setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("Aceptar", null)
                .show();
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
        Type type = new TypeToken<ArrayList<Visita>>() {}.getType();
        ArrayList<Visita> listaGuardada = gson.fromJson(json, type);

        if (listaGuardada != null) {
            visitas.clear();
            visitas.addAll(listaGuardada);
            adapter.notifyDataSetChanged();
        }
    }
}