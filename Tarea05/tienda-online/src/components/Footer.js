import { Link } from "react-router-dom";

function Footer() {
    return (
        <footer>
            <p>© 2025 Tienda de Artesanías. Todos los derechos reservados.</p>
            <nav>
                <ul>
                    <li>
                        <Link to="/terms">Términos y Condiciones</Link></li>
                    <li><Link to="/contact">Contacto</Link>
                    </li>
                </ul>
            </nav>
        </footer>
    );
}

export default Footer;
