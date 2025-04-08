package com.juanitodev.tarea03;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.juanitodev.tarea03.utils.SessionManager;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2000; // 2 segundos
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar SessionManager
        sessionManager = new SessionManager(this);

        // Handler para retrasar la navegación
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Verificar si el usuario está logueado
                if (sessionManager.isLoggedIn()) {
                    // Si está logueado, ir a la pantalla de perfil
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                } else {
                    // Si no está logueado, ir a la pantalla de login
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}