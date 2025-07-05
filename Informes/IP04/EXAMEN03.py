import cv2
import cvlib as cv
from cvlib.object_detection import draw_bbox
from collections import Counter
import time

webcam = cv2.VideoCapture(0)

if not webcam.isOpened():
    print("Error: No se pudo abrir la cámara.")
    exit()

objetos_interes = ['person', 'cell phone', 'mouse']

print("Presiona la tecla 'q' para salir...")

while True:
   
    status, frame = webcam.read()
    if not status:
        print("Error al leer el frame de la cámara.")
        break

    bbox, label, conf = cv.detect_common_objects(frame, model='yolov4-tiny')

    indices_filtrados = [i for i, l in enumerate(label) if l in objetos_interes]

    bbox_filtrados = [bbox[i] for i in indices_filtrados]
    label_filtrados = [label[i] for i in indices_filtrados]
    conf_filtrados = [conf[i] for i in indices_filtrados]

    output_image = draw_bbox(frame, bbox_filtrados, label_filtrados, conf_filtrados)

    conteo = Counter(label_filtrados)
    
    texto_conteo = []
    for obj in objetos_interes:
        texto_conteo.append(f"{obj}: {conteo.get(obj, 0)}")

    
    display_text = " | ".join(texto_conteo)
    cv2.putText(output_image, display_text, (10, 30), cv2.FONT_HERSHEY_SIMPLEX, 0.8, (0, 255, 0), 2)

    conteo_personas = conteo.get('person', 0)
    
    if conteo_personas >= 2:
    
        timestamp = time.strftime("%Y%m%d-%H%M%S")
        nombre_archivo = f'yolov4_captura_{timestamp}.jpg'
        
        cv2.imwrite(nombre_archivo, output_image)
        
        cv2.putText(output_image, f"IMAGEN GUARDADA: {nombre_archivo}", (10, 60), cv2.FONT_HERSHEY_SIMPLEX, 0.6, (0, 0, 255), 2)

    cv2.imshow("Deteccion con Filtro - cvlib", output_image)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

webcam.release()
cv2.destroyAllWindows()