package com.juanitodev.tarea02.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.juanitodev.tarea02.R;
import com.juanitodev.tarea02.data.LawyerDbHelper;
import com.juanitodev.tarea02.model.Lawyer;

public class AddEditLawyerActivity extends AppCompatActivity {
    private static final int REQUEST_SELECT_IMAGE = 100;

    private LawyerDbHelper dbHelper;
    private Lawyer currentLawyer;
    private String lawyerId;
    private boolean isEditMode = false;

    private EditText nameEditText;
    private EditText specialtyEditText;
    private EditText phoneEditText;
    private EditText bioEditText;
    private ImageView avatarImageView;
    private String selectedImageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_lawyer);

        // Configurar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Inicializar vistas
        nameEditText = findViewById(R.id.edit_name);
        specialtyEditText = findViewById(R.id.edit_specialty);
        phoneEditText = findViewById(R.id.edit_phone);
        bioEditText = findViewById(R.id.edit_bio);
        avatarImageView = findViewById(R.id.edit_avatar);
        Button saveButton = findViewById(R.id.button_save);
        Button selectImageButton = findViewById(R.id.button_select_image);

        // Inicializar DB Helper
        dbHelper = new LawyerDbHelper(this);

        // Verificar si estamos en modo de edición
        lawyerId = getIntent().getStringExtra(LawyersActivity.EXTRA_LAWYER_ID);
        isEditMode = lawyerId != null;

        // Configurar título según el modo
        getSupportActionBar().setTitle(isEditMode ? "Editar Abogado" : "Nuevo Abogado");

        // Cargar datos si estamos en modo de edición
        if (isEditMode) {
            loadLawyerData();
        }

        // Configurar botón para seleccionar imagen
        selectImageButton.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_SELECT_IMAGE);
        });

        // Configurar botón de guardar
        saveButton.setOnClickListener(view -> {
            saveLawyer();
        });
    }

    private void loadLawyerData() {
        currentLawyer = dbHelper.getLawyerById(lawyerId);

        if (currentLawyer != null) {
            nameEditText.setText(currentLawyer.getName());
            specialtyEditText.setText(currentLawyer.getSpecialty());
            phoneEditText.setText(currentLawyer.getPhone());
            bioEditText.setText(currentLawyer.getBio());

            // Cargar avatar si existe
            if (currentLawyer.getAvatarUri() != null && !currentLawyer.getAvatarUri().isEmpty()) {
                selectedImageUri = currentLawyer.getAvatarUri();
                avatarImageView.setImageURI(Uri.parse(selectedImageUri));
            }
        }
    }

    private void saveLawyer() {
        // Validar campos obligatorios
        String name = nameEditText.getText().toString().trim();
        String specialty = specialtyEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String bio = bioEditText.getText().toString().trim();

        if (name.isEmpty() || specialty.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Por favor complete todos los campos obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear o actualizar abogado
        if (isEditMode) {
            // Modo edición
            currentLawyer.setName(name);
            currentLawyer.setSpecialty(specialty);
            currentLawyer.setPhone(phone);
            currentLawyer.setBio(bio);

            if (selectedImageUri != null) {
                currentLawyer.setAvatarUri(selectedImageUri);
            }

            int rowsUpdated = dbHelper.updateLawyer(currentLawyer);

            if (rowsUpdated > 0) {
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(this, "Error al actualizar datos", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Modo creación
            Lawyer newLawyer = new Lawyer();
            newLawyer.setName(name);
            newLawyer.setSpecialty(specialty);
            newLawyer.setPhone(phone);
            newLawyer.setBio(bio);

            if (selectedImageUri != null) {
                newLawyer.setAvatarUri(selectedImageUri);
            }

            long newRowId = dbHelper.addLawyer(newLawyer);

            if (newRowId > 0) {
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(this, "Error al guardar datos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                selectedImageUri = imageUri.toString();
                avatarImageView.setImageURI(imageUri);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}