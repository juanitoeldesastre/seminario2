import { useState, useEffect } from 'react';
import { useLocation, Link } from 'react-router-dom';
import './Home.css';

function Home() {
    const location = useLocation();

    useEffect(() => {
        if (location.state?.scrollTo === "historia") {
            const el = document.getElementById("historia");
            if (el) {
                setTimeout(() => {
                    el.scrollIntoView({ behavior: "smooth" });
                }, 100); // espera a que Home se monte
            }
        }
    }, [location]);

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
                <div className="contenido">
                    <div className="destacados-header">
                        <h2>NUESTROS PRODUCTOS DESTACADOS</h2>
                        <Link to="/productos" className="btn-ver-todo">Ver más</Link>
                    </div>

                    <p className="destacados-descripcion">
                        Nuestros productos provienen de marcas independientes, diseñadores peruanos, artistas reconocidos.
                        Son hechos con los mejores materiales, con procesos de fabricación a menudo manuales.
                        Explora nuestra tienda online y encuentra el regalo perfecto, para ti o tus seres queridos.
                    </p>

                    <div className="cards-container">
                        <Link to="/productos" className="card">
                            <img src="/images/producto1.webp" alt="Producto 1" />
                            <h3>Chullo andino</h3>
                        </Link>
                        <Link to="/productos" className="card">
                            <img src="/images/producto2.webp" alt="Producto 2" />
                            <h3>Bolso de lana</h3>
                        </Link>
                        <Link to="/productos" className="card">
                            <img src="/images/producto3.webp" alt="Producto 3" />
                            <h3>Vasija quechua</h3>
                        </Link>
                    </div>
                </div>
            </section>


            <section className="sobre-nosotros" id="historia">
                <div className="contenido">
                    <img src="/images/historia1.webp" alt="Artesano trabajando" />
                    <div>
                        <h2>NUESTRA HISTORIA</h2>
                        <p>
                            “El Artesano” nace como un proyecto académico inspirado en las profundas raíces culturales del Perú. Esta web ficticia busca rendir homenaje a la riqueza de nuestras tradiciones andinas a través de una propuesta visual que revalora el trabajo manual, auténtico y lleno de significado.
                            <br /><br />
                            Nos imaginamos como una comunidad de artesanos andinos que, con dedicación y amor, preserva las técnicas ancestrales heredadas de generación en generación. Desde tejidos elaborados en telares tradicionales, hasta cerámicas cuidadosamente moldeadas a mano, cada pieza representaría un pedacito de historia, identidad y arte vivo.
                            <br /><br />
                            Aunque este sitio no representa una tienda real, ha sido creado con el propósito de practicar habilidades en desarrollo web, diseño visual y experiencia de usuario, siempre poniendo como eje central el respeto y admiración por el legado artesanal del Perú.
                            Más allá del ejercicio académico, este proyecto busca sensibilizar y generar aprecio por el inmenso valor cultural de nuestras expresiones artesanales a traves de una experiencia de compra online aunque por el momento simulada para fines educativos.
                            <br /><br />
                            Gracias por visitar “El Artesano”. A través de esta vitrina digital queremos transmitir, aunque sea de forma simbólica, el alma de nuestra cultura y el valor de nuestras manos creadoras.
                        </p>
                    </div>

                    <div>
                        <h2>UN LEGADO EN LOS ANDES</h2>
                        <p>
                            En las alturas del Valle Sagrado de los Incas, en el pequeño pueblo de Chinchero, nació El Artesano, más que un taller, un legado familiar que ha sobrevivido al tiempo, las modas y las distancias.
                            <br /><br />
                            Todo comenzó en 1952, cuando Don Julián Huamán, un joven tejedor que aprendió el arte de la lana de alpaca de su abuela, decidió abrir un pequeño puesto en el mercado de su comunidad. Sin electricidad, sin máquinas, pero con un telar hecho de madera nativa
                            y un corazón lleno de tradición, Don Julián comenzó a tejer más que mantas: tejía historias, símbolos y sueños.
                            Con el paso de los años, su esposa Doña Carmela, ceramista autodidacta, comenzó a moldear figuras inspiradas en los antiguos huacos mochicas y las formas andinas de los apus y la pachamama.
                            <br /><br />
                            Hoy, El Artesano es dirigido por su nieta Ariana, quien con orgullo ha llevado estas tradiciones al mundo digital. Sin perder las técnicas ancestrales, Ariana trabaja con familias de artesanos en Cusco, Ayacucho y Puno. Cada pieza que
                            encuentras en nuestra tienda —ya sea un poncho, una faja o una vasija— ha sido hecha a mano, usando tintes naturales, barro local y, sobre todo, siglos de historia viva.
                            <br /><br />
                            El Artesano no es solo una tienda. Es un puente entre el pasado y el futuro. Es el reflejo de una cultura que resiste, que se reinventa y que sigue contando su historia a través de sus manos. (Toda esta historia fue inventada, por lo que no es real)
                        </p>
                    </div>
                    <img src="/images/historia2.webp" alt="Valle Sagrado en los andes" />
                </div>
            </section>
        </>
    );
}

export default Home;
