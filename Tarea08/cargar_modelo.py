import tensorflow as tf
import numpy as np
from tensorflow.keras.preprocessing import image
# Cargar el modelo exportado de Teachable Machine
model = tf.keras.models.load_model('modelo/teachable_machine_model.h5')
# Cargar las etiquetas
with open('modelo/labels.txt') as f:
    labels = [line.strip() for line in f.readlines()]
# Proceso de prueba con una imagen
def predict_species(image_path):
    img = image.load_img(image_path, target_size=(224, 224))
    img_array = image.img_to_array(img)
    img_array = np.expand_dims(img_array, axis=0) # Aumentar dimensión para predicción
    img_array /= 255.0 # Normalizar
    prediction = model.predict(img_array)
    index = np.argmax(prediction[0])
    return labels[index]

# Ejemplo de predicción
image_path = 'large.jpeg'
print(f"La especie predicha es: {predict_species(image_path)}")