# Tarea 08: Exporta e integra modelos de Machine Learning

## Descripción

Esta tarea consiste en desarrollar un modelo de Machine Learning para clasificación de imágenes de aves peruanas, utilizando la herramienta accesible Teachable Machine de Google. Posteriormente, se exporta el modelo tanto para uso con python como para integración en la web con TensorFlow.js.

### Dataset

Se usó el siguiente dataset de Kaggle:

> https://www.kaggle.com/datasets/vinjamuripavan/bird-species

Se seleccionaron 5 especies de aves peruanas. Las imágenes fueron organizadas en carpetas nombradas con el nombre científico de cada especie:

| Nombre común           | Nombre científico       |
| ---------------------- | ----------------------- |
| Guacamaya Roja         | *Ara macao*             |
| Gaviotín Monja         | *Larosterna inca*       |
| Gallito de Roca Andino | *Rupicola peruvianus*   |
| Guácharo               | *Steatornis caripensis* |
| Avefría Andina         | *Vanellus resplendens*  |

---

### Entrenamiento
El modelo fue entrenado usando **Teachable Machine** (https://teachablemachine.withgoogle.com/).  
Se subieron las imágenes por clase, se configuró la red neuronal y se entrenó el modelo.

> ⚠️ Aquí puedes agregar una captura de pantalla de la interfaz de Teachable Machine o el enlace del proyecto si es público.

### Validación
Se verificó la precisión utilizando imágenes adicionales de las mismas especies desde el sitio web:  
> https://www.inaturalist.org

### Exportación
Se exportó el modelo en formato **.h5** para su carga local con python.

### Requisitos

> Crer un entorno usando anaconda con la misma version de python vas a ejecutar el script.py

```bash
conda install -c conda-forge cudatoolkit=11.2 cudnn=8.1.0
```

* **Python 3.9.23**  configurando un entorno desde anaconda

* **Tensorflow 2.10.0** `pip install tensorflow==2.10`

* **numpy 1.23.5** `pip install numpy==1.23.5`