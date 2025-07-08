import { useState } from 'react';
import './Home.css';

function Home() {
    const [imagenActiva, setImagenActiva] = useState(0);

    const imagenes = [
        '/images/slider1.webp',
        '/images/slider2.webp',
        '/images/slider3.webp'
    ];

    const siguiente = () => {
        setImagenActiva((prev) => (prev + 1) % imagenes.length);
    };

    const anterior = () => {
        setImagenActiva((prev) => (prev - 1 + imagenes.length) % imagenes.length);
    };

    return (
        <>
            <section className="home-slider">
                <div
                    className="slider-track"
                    style={{ transform: `translateX(-${imagenActiva * 100}%)` }}
                >
                    {imagenes.map((src, i) => (
                        <img key={i} src={src} alt={`Slider ${i}`} className="slider-fondo" />
                    ))}
                </div>

                <button className="flecha izquierda" onClick={anterior}>&#10094;</button>
                <button className="flecha derecha" onClick={siguiente}>&#10095;</button>

                <div className="slider-contenido">
                    <h1>EL ARTESANO</h1>
                    <p>Actualmente estamos en proceso de construcción</p>
                    <a href="/productos" className="btn-grande">EXPLORAR</a>
                </div>
            </section>

            <section className="destacados">
                <h2>PRODUCTOS DESTACADOS</h2>
                <div className="cards-container">
                    <div className="card">
                        <img src="/images/producto1.webp" alt="Producto 1" />
                        <h3>Chullo andino</h3>
                        <a href="/productos" className="btn-card">Ver más</a>
                    </div>
                    <div className="card">
                        <img src="/images/producto2.webp" alt="Producto 2" />
                        <h3>Bolso de lana</h3>
                        <a href="/productos" className="btn-card">Ver más</a>
                    </div>
                    <div className="card">
                        <img src="/images/producto3.webp" alt="Producto 3" />
                        <h3>Alfarería quechua</h3>
                        <a href="/productos" className="btn-card">Ver más</a>
                    </div>
                </div>
            </section>

            <section className="sobre-nosotros">
                <div className="contenido">
                    <img src="/images/historia.webp" alt="Artesano trabajando" />
                    <div>
                        <h2>NUESTRA HISTORIA</h2>
                        <p>
                            “El Artesano” nace como un proyecto académico inspirado en las profundas raíces culturales del Perú. Esta web ficticia busca rendir homenaje a la riqueza de nuestras tradiciones andinas a través de una propuesta visual que revalora el trabajo manual, auténtico y lleno de significado.
                            <br /><br />
                            Nos imaginamos como una comunidad de artesanos andinos que, con dedicación y amor, preserva las técnicas ancestrales heredadas de generación en generación. Desde tejidos elaborados en telares tradicionales, hasta cerámicas cuidadosamente moldeadas a mano, cada pieza representaría un pedacito de historia, identidad y arte vivo.
                            <br /><br />
                            Aunque este sitio no representa una tienda real, ha sido creado con el propósito de practicar habilidades en desarrollo web, diseño visual y experiencia de usuario, siempre poniendo como eje central el respeto y admiración por el legado artesanal del Perú.
                            Aunque este sitio no representa una tienda real, ha sido creado con el propósito de practicar habilidades en desarrollo web, diseño visual y experiencia de usuario, siempre poniendo como eje central el respeto y admiración por el legado artesanal del Perú.
                            <br /><br />
                            Gracias por visitar “El Artesano”. A través de esta vitrina digital queremos transmitir, aunque sea de forma simbólica, el alma de nuestra cultura y el valor de nuestras manos creadoras.
                        </p>
                    </div>
                </div>
            </section>
        </>
    );
}

export default Home;
