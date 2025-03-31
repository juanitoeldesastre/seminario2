# Tarea01 – HT-02

(app.png)

## Descripción
Esta aplicación permite a los visitantes registrar su entrada y salida de manera sencilla y efectiva. Diseñada para mejorar la eficiencia y seguridad en el control de accesos, ofrece una interfaz intuitiva y un sistema de gestión completo para las visitas diarias.

## Requisitos técnicos

- Android Studio
- Versión mínima de Android: API 2 ("Nourgat"; Android 7.0)
- Dependencias:
  - Material Design Components
  - Gson para serialización/deserialización de datos
  - AndroidX

## Estructura del proyecto

- **MainActivity.java**: Actividad principal que gestiona la UI y lógica de la aplicación
- **Visita.java**: Modelo de datos para las visitas
- **VisitasAdapter.java**: Adaptador personalizado para mostrar las visitas en la lista
- **activity_main.xml**: Layout principal de la aplicación
- **item_visita.xml**: Layout para cada elemento de la lista de visitas
- **colors.xml**: Definición de colores de la aplicación
- **strings.xml**: Cadenas de texto utilizadas en la aplicación
- **styles.xml**: Estilos y temas de la aplicación

## Uso

1. Ingresa los datos del visitante (nombre, empresa, propósito)
2. Presiona el botón "Registrar Visita"
3. Para registrar la salida, toca el registro correspondiente en la lista
4. Selecciona "Registrar salida" en el menú de opciones

## Licencia

Este proyecto está bajo la Licencia MIT - consulta el archivo LICENSE para más detalles.

## Juan Piero Vincha Loza