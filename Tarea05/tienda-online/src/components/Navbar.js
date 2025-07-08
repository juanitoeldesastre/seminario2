import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar() {
    return (
        <nav className="navbar">
            <Link to="/" className="logo-link">
                <img src="/images/logo.webp" alt="Logo Artesano" className="logo-img" />
            </Link>

            <ul className="nav-links">
                <li><Link to="/">Inicio</Link></li>
                <li><Link to="/catalog">Catálogo</Link></li>
                <li><Link to="/cart">Carrito</Link></li>
                <li><Link to="/blog">Blog</Link></li>
            </ul>
        </nav>
    );
}

export default Navbar;
