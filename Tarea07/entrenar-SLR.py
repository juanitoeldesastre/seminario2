# Importamos las bibliotecas necesarias
import numpy as np
import matplotlib.pyplot as plt
from tensorflow import keras
from keras.datasets import fashion_mnist
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split

# Cargar el dataset Fashion MNIST
(X_train, y_train), (X_test, y_test) = fashion_mnist.load_data()

# Explorar el dataset
labels = ["T-shirt/top", "Trouser", "Pullover", "Dress", "Coat", "Sandal", "Shirt", "Sneaker",
"Bag", "Ankle boot"]

plt.figure(figsize=(14, 8))
for i in range(20):
 plt.subplot(4, 5, i+1)
 plt.imshow(X_train[i], cmap="binary")
 plt.title(labels[y_train[i]])
 plt.axis("off")
plt.savefig("fmnist_clases.png") 
plt.show()

# Normalizar datos
X_train, X_test = X_train / 255.0, X_test / 255.0

# Definimos la arquitectura de la red neuronal
model = keras.Sequential([
    keras.layers.Flatten(input_shape=(28, 28)),
    keras.layers.Dense(128, activation='relu'),
    keras.layers.Dense(10, activation='softmax')
])

# Compilar el modelo
model.compile(
    optimizer='adam',
    loss='sparse_categorical_crossentropy',
    metrics=['accuracy']
)

# Entrenar el modelo
history=model.fit(X_train, y_train, epochs=10, validation_data=(X_test, y_test))

# Evaluar el modelo
test_loss, test_acc = model.evaluate(X_test, y_test)
print(f"\nPrecisión en el conjunto de prueba: {test_acc:.4f}")

# Datos de ejemplo (ficticios)
temperatura = np.array([15, 16, 18, 20, 21, 23, 25, 27, 30, 32])
ventas = np.array([500, 520, 560, 580, 600, 640, 680, 700, 760, 800])

# Dividimos los datos en conjunto de entrenamiento y prueba
X_train, X_test, y_train, y_test = train_test_split(temperatura.reshape(-1, 1), ventas,
test_size=0.2, random_state=42)

# Creamos y entrenamos el modelo de regresión lineal
model = LinearRegression()
model.fit(X_train, y_train)

# Predicciones y gráfica de resultados
plt.scatter(temperatura, ventas, color="blue", label="Datos reales")
plt.plot(temperatura, model.predict(temperatura.reshape(-1, 1)), color="red", label="Línea de regresión")
plt.xlabel("Temperatura (°C)")
plt.ylabel("Ventas")
plt.legend()
plt.savefig("regresion_lineal.png")
plt.show()

# Evaluación del modelo
r_sq = model.score(X_test, y_test)
print('Coeficiente de determinación:', r_sq)