# FinCo Login App


Una aplicación móvil para Android que permite a los empleados de FinCo acceder de forma segura a su perfil y datos esenciales desde cualquier ubicación.

![FinCo App Screenshot](https://raw.githubusercontent.com/juanitoeldesastre/seminario2/main/Tarea03/img/login.PNG)

## Características

- Autenticación segura de usuarios mediante API RESTful
- Almacenamiento seguro de tokens JWT
- Gestión de sesiones de usuario
- Interfaz de usuario intuitiva y profesional
- Acceso a perfil de usuario
- Arquitectura modular y escalable

## Requisitos técnicos
- Android Studio
- API 24 ("Nourgat"; Android 7.0)
- Dependencias: 
  - Retrofit 2 
  - GSON
  - OkHttp3

## Estructura del proyecto

```
com.example.fincologinapp/
├── api/
│   ├── ApiClient.java
│   └── ApiService.java
├── models/
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   └── User.java
├── utils/
│   └── SessionManager.java
├── MainActivity.java
├── LoginActivity.java
└── ProfileActivity.java
```


### Credenciales de prueba
- **Analista**: empleado1@finco.com / password123
- **Gerente**: empleado2@finco.com / secure456
- **Administrador**: admin@finco.com / admin789

### Uso

1. Al iniciar la aplicación, se muestra una pantalla de splash.
2. Si no hay una sesión activa, se redirige a la pantalla de login.
3. El usuario introduce sus credenciales y se autentica.
4. Una vez autenticado, el usuario es redirigido a la pantalla de perfil.
5. El usuario puede cerrar sesión desde el menú en la pantalla de perfil.



## Publicación en Google Play

Para publicar la aplicación en Google Play, sigue estos pasos:

1. **Crear una cuenta de desarrollador**:
   - Regístrate en [Google Play Console](https://play.google.com/console/about/)
   - Paga la cuota única de $25 USD

2. **Preparar APK/AAB**:
   - En Android Studio: Build > Generate Signed Bundle/APK
   - Elige Android App Bundle (AAB) para optimizar la instalación
   - Firma la aplicación con una clave de firma

3. **Configurar la ficha de Play Store**:
   - Título y descripción
   - Capturas de pantalla
   - Gráficos promocionales
   - Política de privacidad

4. **Publicar**:
   - Sube el AAB firmado
   - Elige países de distribución
   - Configura el precio (gratuito o de pago)
   - Envía para revisión

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## ESTUDIANTE : Juan Piero Vincha Loza