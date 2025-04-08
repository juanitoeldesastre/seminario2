package com.juanitodev.tarea03;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.juanitodev.tarea03.utils.SessionManager;

public class ProfileActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Inicializar vistas
        welcomeTextView = findViewById(R.id.welcomeTextView);

        // Inicializar SessionManager
        sessionManager = new SessionManager(getApplicationContext());

        // Verificar si el usuario está logueado
        if (!sessionManager.isLoggedIn()) {
            // Redirigir al login si no está logueado
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
        }

        // Mostrar token (en una aplicación real, deberías obtener y mostrar datos del usuario)
        String token = sessionManager.getToken();
        welcomeTextView.setText("¡Bienvenido a FinCo!\nHas iniciado sesión correctamente.\n\nToken: " + token);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            // Cerrar sesión
            sessionManager.logout();

            // Redirigir al login
            startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}