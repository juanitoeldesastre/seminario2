import { Link, useNavigate } from "react-router-dom";
import './Footer.css';

function Footer() {
    const navigate = useNavigate();
    const scrollToHistoria = () => {
        navigate("/", { state: { scrollTo: "historia" } });
    };

    return (
        <footer className="footer">
            <div className="footer-container">
                <div className="footer-section">
                    <h3>Artesano</h3>
                    <p>Hecho a mano con amor desde los Andes</p>
                </div>

                <div className="footer-section">
                    <h4>Sobre Artesano</h4>
                    <ul className="footer-links">
                        <li><button onClick={scrollToHistoria} className="footer-button-link">Historia</button></li>
                        <li><Link to="/preguntas-frecuentes">Preguntas y Respuestas</Link></li>
                        <li><Link to="/ubicaciones">Ubicaciones</Link></li>
                    </ul>
                </div>

                <div className="footer-section">
                    <h4>Servicio al cliente</h4>
                    <ul className="footer-links">
                        <li><Link to="/contacto">Contáctemos</Link></li>
                        <li><Link to="/terminos-y-condiciones">Términos y Condiciones</Link></li>
                        <li><Link to="/privacidad">Política de Privacidad</Link></li>
                    </ul>
                </div>

            </div>

            <div className="footer-bottom">
                <p>Copyright &copy; 2024 El artesano | Hecho por juanitoeldesastre </p>
            </div>
        </footer>
    );
}

export default Footer;