# Tarea07: Crea y entrena modelos ML

## Descripción

Este proyecto crea y entrena una red neuronal usando el dataset Fashion MNIST utilizando TensorFlow y Keras. Además, incluye un modelo de regresión lineal simple para analizar la relación entre temperatura y ventas. Las gráficas generadas con matplotlib se guardan automáticamente en archivos **.png** dentro del directorio.

---

## Instalación

Usaremos anaconda para el archivo python y el cuarderno jupyter

> Primero instalamos yaml desde la terminal de anaconda por las dudas

```bash
conda install anaconda::yaml
```

>  Luego dentro de la carpeta creamos el entorno automatizado usando yaml

```bash
conda env create -f entorno.yml
conda activate train-ml-model
```

> Ejecutamos el script o desde anaconda descargar jupyter y abrir el cuaderno

```python
python entrenar-SLR.py
```

---

## Requisitos

> El archivo yaml automatiza la descarga en conda de las librerias para mejor rendimiento de tensorflow con la GPU y para el funcionando del archivo.py

```bash
conda install -c conda-forge cudatoolkit=11.2 cudnn=8.1.0
```

* **Python 3.9.23**  configurando un entorno desde anaconda

* **Tensorflow 2.10.1** `pip install "tensorflow<2.11"`

* **numpy 1.26.4** `pip install "numpy<2.0" compatible con tf`

---

## Preguntas

1. **¿Cuál es la función de la capa Flatten en el modelo de red neuronal?**
   Aplana de imágenes 2D (28x28) a un vector 1D (784), esto es importante antes de pasar a las capas densas.

2. **¿Qué descubriste sobre la relación entre temperatura y ventas en el modelo de regresión lineal?**
   Comenta sobre la correlación encontrada, el valor de R² y su interpretación.

3. **¿Qué mejoras le harías a la red neuronal para aumentar la precisión?**
   Considera incluir más capas, regularización, batch normalization o técnicas de aumento de datos.

4. **¿Cómo influye el aumento del número de épocas en la precisión del modelo de clasificación?**
   Explica el impacto de un entrenamiento más prolongado en la capacidad de generalización del modelo.

5. **¿Qué aplicaciones prácticas podrías imaginar para esta red neuronal en la industria de la moda?**
   Ejemplos como recomendación de ropa, clasificación automática de imágenes para pequeños negocios, detección de tendencias, etc.
