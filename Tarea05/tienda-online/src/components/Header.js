import './Header.css';

function Header() {
    return (
        <header className="header">
            <div className="header-left">
                <div className="contact-item">
                    <a
                        href="https://wa.me/51999888777"
                        target="_blank"
                        rel="noopener noreferrer"
                        className="contact-link"
                    >
                        <img src="/icons/whatsapp.svg" alt="WhatsApp" className="contact-icon" />
                        <span>+51999888777</span>
                    </a>
                </div>
                <div className="contact-item">
                    <a
                        href="mailto:info@artesano.pe"
                        className="contact-link"
                    >
                        <img src="/icons/gmail.svg" alt="Correo" className="contact-icon" />
                        <span>info@artesano.pe</span>
                    </a>
                </div>
            </div>

            <div className="header-center">
                <p className="description">- Hecho a mano con amor desde los Andes -</p>
            </div>

            <div className="header-right">
                <div className="social-container">
                    <span className="follow-text">Síguenos:</span>
                    <a href="https://facebook.com" target="_blank" rel="noreferrer">
                        <img src="/icons/facebook.svg" alt="Facebook" className="icon" />
                    </a>
                    <a href="https://instagram.com" target="_blank" rel="noreferrer">
                        <img src="/icons/instagram.svg" alt="Instagram" className="icon" />
                    </a>
                    <a href="https://pinterest.com" target="_blank" rel="noreferrer">
                        <img src="/icons/pinterest.svg" alt="Pinterest" className="icon" />
                    </a>
                </div>
            </div>
        </header>
    );
}

export default Header;
