package com.juanitodev.tarea03;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.juanitodev.tarea03.api.ApiClient;
import com.juanitodev.tarea03.api.ApiService;
import com.juanitodev.tarea03.models.LoginRequest;
import com.juanitodev.tarea03.models.LoginResponse;
import com.juanitodev.tarea03.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private ProgressBar progressBar;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar vistas
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressBar);

        // Inicializar SessionManager
        sessionManager = new SessionManager(getApplicationContext());

        // Verificar si el usuario ya inició sesión
        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
            finish();
        }

        // Configurar el botón de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        // Obtener los valores de email y contraseña
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validar los campos
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Por favor ingrese su email");
            emailEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Por favor ingrese su contraseña");
            passwordEditText.requestFocus();
            return;
        }

        // Mostrar progreso
        progressBar.setVisibility(View.VISIBLE);

        // Crear la solicitud de login
        LoginRequest loginRequest = new LoginRequest(email, password);

        // Obtener la instancia de ApiService
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        // Realizar la llamada a la API
        Call<LoginResponse> call = apiService.loginUser(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();

                    if (loginResponse.isSuccess()) {
                        // Guardar el token en sesión
                        sessionManager.saveToken(loginResponse.getToken());

                        // Mostrar mensaje de éxito
                        Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                        // Navegar a la actividad de perfil
                        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Mostrar mensaje de error
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Error en la respuesta
                    Toast.makeText(LoginActivity.this, "Error en la autenticación. Inténtelo de nuevo.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                // Error de conexión
                Toast.makeText(LoginActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}