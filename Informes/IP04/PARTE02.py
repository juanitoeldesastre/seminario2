import cv2
import os

cascade_file = 'haarcascade_frontalface_default.xml'
if not os.path.exists(cascade_file):
    print(f"Error: No se encuentra el archivo '{cascade_file}'")
    print("Asegúrate de haberlo descargado y colocado en la misma carpeta que este script.")
    exit()

face_cascade = cv2.CascadeClassifier(cascade_file)

webcam = cv2.VideoCapture(0)

if not webcam.isOpened():
    print("Error: No se pudo abrir la cámara.")
    exit()

print("Detección de rostros iniciada...")
print("Presiona 'q' para salir.")
print("Presiona 's' para guardar una captura y salir.")

while True:
    # Leer un frame de la webcam
    status, frame = webcam.read()
    if not status:
        print("Error al leer el frame de la cámara.")
        break

    # Convertir el frame a escala de grises
    # Los clasificadores Haar funcionan mejor y más rápido en imágenes en escala de grises.
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    # Detectar rostros en la imagen en escala de grises
    # face_cascade.detectMultiScale() es la función que realiza la detección.
    #   - gray: la imagen en escala de grises.
    #   - scaleFactor: cuánto se reduce el tamaño de la imagen en cada escala. Un valor más bajo es más lento pero más preciso.
    #   - minNeighbors: cuántos "vecinos" debe tener cada rectángulo candidato para ser retenido. Ayuda a eliminar falsos positivos.
    # Devuelve una lista de rectángulos (x, y, ancho, alto) para cada rostro detectado.
    faces = face_cascade.detectMultiScale(gray, scaleFactor=1.1, minNeighbors=5, minSize=(30, 30))

    # Obtener el número total de rostros detectados
    num_faces = len(faces)

    # --- 3. DIBUJAR Y MOSTRAR RESULTADOS ---

    # Dibujar un rectángulo alrededor de cada rostro detectado
    # Y asignar un ID a cada uno
    for i, (x, y, w, h) in enumerate(faces):
        # Dibujar el rectángulo verde alrededor del rostro
        cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 255, 0), 2)
        
        # Crear el texto para el ID del rostro
        id_text = f"Rostro {i + 1}"
        
        # Poner el texto del ID encima del rectángulo
        cv2.putText(frame, id_text, (x, y - 10), cv2.FONT_HERSHEY_SIMPLEX, 0.7, (0, 255, 0), 2)

    # Mostrar el conteo total de rostros en la esquina superior izquierda
    conteo_text = f"Total de Rostros: {num_faces}"
    cv2.putText(frame, conteo_text, (10, 30), cv2.FONT_HERSHEY_SIMPLEX, 0.8, (255, 255, 0), 2)

    # Mostrar la imagen resultante en una ventana
    cv2.imshow("Deteccion de Rostros con Haar Cascade", frame)

    # --- 4. MANEJO DE TECLAS DE SALIDA Y GUARDADO ---
    key = cv2.waitKey(1) & 0xFF

    # Si se presiona 'q', salir del bucle
    if key == ord('q'):
        print("Saliendo del programa...")
        break
    
    # Si se presiona 's', guardar la imagen y salir del bucle
    if key == ord('s'):
        nombre_archivo = 'haar_resultado.jpg'
        cv2.imwrite(nombre_archivo, frame)
        print(f"¡Captura guardada como '{nombre_archivo}'!")
        break

# --- 5. LIBERAR RECURSOS ---
# Liberar la cámara y cerrar todas las ventanas de OpenCV
webcam.release()
cv2.destroyAllWindows()