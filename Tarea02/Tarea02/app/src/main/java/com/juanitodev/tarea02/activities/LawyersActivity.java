package com.juanitodev.tarea02.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.juanitodev.tarea02.R;
import com.juanitodev.tarea02.adapters.LawyerAdapter;
import com.juanitodev.tarea02.data.LawyerDbHelper;
import com.juanitodev.tarea02.model.Lawyer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class LawyersActivity extends AppCompatActivity {
    public static final String EXTRA_LAWYER_ID = "extra_lawyer_id";
    private static final int REQUEST_ADD_LAWYER = 1;
    private static final int REQUEST_EDIT_LAWYER = 2;

    private LawyerDbHelper dbHelper;
    private LawyerAdapter adapter;
    private List<Lawyer> lawyersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyers);

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Inicializar DB Helper
        dbHelper = new LawyerDbHelper(this);

        // Configurar FAB para agregar abogado
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddEditLawyerActivity.class);
            startActivityForResult(intent, REQUEST_ADD_LAWYER);
        });

        // Cargar lista de abogados
        loadLawyersList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Recargar la lista al volver a la actividad
        loadLawyersList();
    }

    private void loadLawyersList() {
        // Obtener abogados de la base de datos
        lawyersList = dbHelper.getAllLawyers();

        // Configurar adaptador
        adapter = new LawyerAdapter(this, lawyersList);
        ListView listView = findViewById(R.id.lawyers_list);
        listView.setAdapter(adapter);

        // Configurar clic en elementos de la lista
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Lawyer selectedLawyer = lawyersList.get(position);
            Intent intent = new Intent(LawyersActivity.this, LawyerDetailActivity.class);
            intent.putExtra(EXTRA_LAWYER_ID, selectedLawyer.getId());
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_ADD_LAWYER) {
                Toast.makeText(this, "Abogado agregado correctamente", Toast.LENGTH_SHORT).show();
                loadLawyersList();
            } else if (requestCode == REQUEST_EDIT_LAWYER) {
                Toast.makeText(this, "Datos del abogado actualizados", Toast.LENGTH_SHORT).show();
                loadLawyersList();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Toast.makeText(this, "Sistema de Gestión de Abogados v1.0", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}