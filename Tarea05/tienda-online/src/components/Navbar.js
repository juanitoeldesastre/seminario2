import { Link } from 'react-router-dom';

function Navbar() {
    return (
        <nav>
            <ul>
                <li><Link to="/">Inicio</Link></li>
                <li><Link to="/catalog">Catálogo</Link></li>
                <li><Link to="/cart">Carrito</Link></li>
                <li><Link to="/blog">Blog</Link></li>
            </ul>
        </nav>
    );
}

export default Navbar;