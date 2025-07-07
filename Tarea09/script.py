import numpy as np
import matplotlib.pyplot as plt
import cv2

# Generamos datos de temperatura aleatorios para simular la entrada del sensor
temperatura = np.random.normal(loc=20, scale=5, size=100)

# Visualización en gráfico
plt.plot(temperatura, label='Temperatura (°C)')
plt.xlabel('Tiempo')
plt.ylabel('Temperatura')
plt.legend()
plt.show()

# Visualización usando OpenCV
for temp in temperatura:
    alert_msg = "Alerta: Temperatura Excesiva!" if temp > 25 else "Temperatura Normal"
    print(f"Temperatura actual: {temp}°C - {alert_msg}")

import tensorflow as tf
import pandas as pd

# Crear datos de ejemplo
datos = {'Celsius': np.arange(0, 100, 10), 'Fahrenheit': np.arange(32, 212, 18)}
df = pd.DataFrame(datos)

X_train = df['Celsius']
y_train = df['Fahrenheit']

# Crear el modelo
model = tf.keras.Sequential()
model.add(tf.keras.layers.Dense(units=1, input_shape=[1]))

model.compile(optimizer='adam', loss='mean_squared_error')
model.fit(X_train, y_train, epochs=500)

# Guardar el modelo
model.save("modelo_temperatura.h5")

# Convertir a TensorFlow Lite
converter = tf.lite.TFLiteConverter.from_keras_model(model)
tflite_model = converter.convert()

# Guardar el modelo convertido
with open('modelo_temperatura.tflite', 'wb') as f:
 f.write(tflite_model)