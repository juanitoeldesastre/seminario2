import React from 'react';
import './Privacidad.css';

function Privacidad() {
    return (
        <div className="privacidad-container">
            <h1>Política de Privacidad</h1>
            <p>
                En <strong>“El Artesano”</strong>, nos tomamos la privacidad de los usuarios con seriedad, incluso en un entorno simulado. Esta página web ha sido creada únicamente con fines educativos como parte de un ejercicio de desarrollo con React.
            </p>

            <p>
                Si bien no recolectamos datos personales reales, esta política busca imitar las condiciones y estructura que tendría una tienda online auténtica que respeta la privacidad de sus visitantes.
            </p>

            <h2>1. Información que recopilamos</h2>
            <p>
                Este sitio no recopila automáticamente ningún tipo de información personal. No se utilizan formularios reales de contacto, registro o suscripción que almacenen tus datos en bases de datos o servidores.
            </p>
            <p>
                Sin embargo, existen campos visibles como el formulario de suscripción, diseñados únicamente para demostrar diseño visual y experiencia de usuario. La información que escribas en ellos no se guarda ni se transmite.
            </p>

            <h2>2. Uso simulado de la información</h2>
            <p>
                En una tienda real, los datos podrían utilizarse para enviar boletines informativos, promociones, o responder consultas. En este caso, todos los datos simulados se pierden al recargar la página.
            </p>
            <p>
                No se implementa almacenamiento en bases de datos, ni se integra ningún backend. Tampoco se usan APIs externas o bibliotecas de seguimiento como Google Analytics.
            </p>
            <p>
                Todos los elementos que aparentan capturar datos están diseñados únicamente para ilustrar cómo funcionaría una aplicación real de comercio electrónico.
            </p>

            <h2>3. Cookies</h2>
            <p>
                Este sitio no utiliza cookies propias ni de terceros. No hay almacenamiento local, sesiones ni rastreo del comportamiento del usuario.
            </p>
            <p>
                En una versión futura, como parte del aprendizaje, podrían implementarse cookies educativas o almacenamiento `localStorage` con consentimiento previo (simulado).
            </p>

            <h2>4. Protección de la privacidad</h2>
            <p>
                Aunque no se procesan datos personales, el sitio ha sido desarrollado teniendo en cuenta buenas prácticas de seguridad y estructura limpia en el código.
            </p>
            <p>
                Se evita el uso de formularios inseguros, se valida el input a nivel visual, y no se expone información sensible ni vulnerabilidades comunes.
            </p>

            <h2>5. Enlaces externos</h2>
            <p>
                Todos los enlaces del sitio dirigen a secciones internas o están inactivos. No existen redirecciones a sitios de terceros ni plataformas externas.
            </p>
            <p>
                En una tienda real, sería importante notificar al usuario sobre redirecciones externas, especialmente si implican pagos, pasarelas de cobro o servicios externos.
            </p>

            <h2>6. Derechos del usuario</h2>
            <p>
                En un entorno real, como visitante o cliente, tienes derecho a acceder, modificar o eliminar tu información personal.
            </p>
            <p>
                Aunque esta web no recopila datos, simulamos una experiencia donde se reconoce la importancia de estos derechos. Esto incluye también el derecho a retirar el consentimiento o limitar el uso de tus datos.
            </p>
            <p>
                En este sitio, todos estos elementos son visuales y educativos, pero en un proyecto real serían de cumplimiento obligatorio.
            </p>

            <h2>7. Cambios en esta política</h2>
            <p>
                Esta política puede ser modificada en cualquier momento como parte de ejercicios de mejora continua del diseño, contenido o código.
            </p>
            <p>
                La fecha de actualización se mostrará en la parte superior de esta página cada vez que se realice una modificación sustancial, por motivos de aprendizaje.
            </p>

            <h2>8. Contacto</h2>
            <p>
                Si tienes dudas sobre esta política simulada, puedes usar el formulario de contacto ficticio de este sitio.
            </p>
            <p>
                Alternativamente, puedes enviar un correo electrónico a <em>contacto@elartesano.pe</em>, una dirección ficticia pensada solo para mostrar cómo se vería un canal de soporte real.
            </p>

            <p>
                Agradecemos tu interés por este proyecto. Aunque no es una tienda verdadera, ha sido diseñada con respeto por el arte, la tradición y la cultura andina del Perú.
            </p>
            <p>
                Gracias por visitar “El Artesano”.
            </p>
        </div>
    );
}

export default Privacidad;