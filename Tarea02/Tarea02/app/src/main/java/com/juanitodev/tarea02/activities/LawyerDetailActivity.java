package com.juanitodev.tarea02.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.juanitodev.tarea02.R;
import com.juanitodev.tarea02.data.LawyerDbHelper;
import com.juanitodev.tarea02.model.Lawyer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LawyerDetailActivity extends AppCompatActivity {
    private static final int REQUEST_EDIT_LAWYER = 1;

    private LawyerDbHelper dbHelper;
    private Lawyer lawyer;
    private String lawyerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyer_detail);

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inicializar DB Helper
        dbHelper = new LawyerDbHelper(this);

        // Obtener ID del abogado de la intent
        lawyerId = getIntent().getStringExtra(LawyersActivity.EXTRA_LAWYER_ID);

        // FAB para editar abogado
        FloatingActionButton fabEdit = findViewById(R.id.fab_edit);
        fabEdit.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddEditLawyerActivity.class);
            intent.putExtra(LawyersActivity.EXTRA_LAWYER_ID, lawyerId);
            startActivityForResult(intent, REQUEST_EDIT_LAWYER);
        });

        // FAB para llamar al abogado
        FloatingActionButton fabCall = findViewById(R.id.fab_call);
        fabCall.setOnClickListener(view -> {
            if (lawyer != null) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + lawyer.getPhone()));
                startActivity(intent);
            }
        });

        // Cargar datos del abogado
        loadLawyerDetails();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Recargar detalles al volver a la actividad
        loadLawyerDetails();
    }

    private void loadLawyerDetails() {
        if (lawyerId != null) {
            lawyer = dbHelper.getLawyerById(lawyerId);

            if (lawyer != null) {
                // Establecer título
                getSupportActionBar().setTitle(lawyer.getName());

                // Poblar vistas con datos
                TextView nameTextView = findViewById(R.id.detail_name);
                TextView specialtyTextView = findViewById(R.id.detail_specialty);
                TextView phoneTextView = findViewById(R.id.detail_phone);
                TextView bioTextView = findViewById(R.id.detail_bio);
                ImageView avatarImageView = findViewById(R.id.detail_avatar);

                nameTextView.setText(lawyer.getName());
                specialtyTextView.setText(lawyer.getSpecialty());
                phoneTextView.setText(lawyer.getPhone());

                if (lawyer.getBio() != null && !lawyer.getBio().isEmpty()) {
                    bioTextView.setText(lawyer.getBio());
                } else {
                    bioTextView.setText("Sin biografía disponible");
                }

                // Cargar avatar si existe
                if (lawyer.getAvatarUri() != null && !lawyer.getAvatarUri().isEmpty()) {
                    avatarImageView.setImageURI(Uri.parse(lawyer.getAvatarUri()));
                } else {
                    // Usar avatar predeterminado
                    avatarImageView.setImageResource(R.drawable.default_avatar);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_EDIT_LAWYER) {
            Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
            loadLawyerDetails();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        } else if (id == R.id.action_delete) {
            showDeleteConfirmationDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar abogado");
        builder.setMessage("¿Está seguro de que desea eliminar a este abogado? Esta acción no se puede deshacer.");
        builder.setPositiveButton("Eliminar", (dialog, which) -> {
            deleteLawyer();
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    private void deleteLawyer() {
        if (lawyerId != null) {
            int rowsDeleted = dbHelper.deleteLawyer(lawyerId);

            if (rowsDeleted > 0) {
                Toast.makeText(this, "Abogado eliminado correctamente", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Error al eliminar abogado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}