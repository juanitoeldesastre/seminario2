import { useState } from 'react';
import './ContactForm.css';

function ContactForm() {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        phone: '',
        message: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(formData);
        alert(`Gracias por contactarnos, ${formData.name}!`);
    };

    return (
        <form className="contact-form" onSubmit={handleSubmit}>
            <span style={{ textAlign: 'center' }}>Contáctanos</span>

            <label htmlFor="name">Nombre</label>
            <input
                type="text"
                name="name"
                id="name"
                placeholder="Ingrese su nombre completo"
                onChange={handleChange}
                required
                autoComplete="name"
            />

            <label htmlFor="email">Correo</label>
            <input
                type="email"
                name="email"
                id="email"
                placeholder="Ingrese su correo electrónico"
                onChange={handleChange}
                required
                autoComplete="email"
            />

            <label htmlFor="phone">Teléfono</label>
            <input
                type="tel"
                name="phone"
                id="phone"
                pattern="^\+51\s?[0-9]{9}$"
                placeholder="Ingrese su número de teléfono"
                onChange={handleChange}
                autoComplete="tel"
            />

            <label htmlFor="message">Mensaje</label>
            <textarea
                name="message"
                id="message"
                placeholder="Ingrese su mensaje de consulta"
                onChange={handleChange}
                required
                autoComplete="off"
            />

            <button type="submit">Enviar</button>
        </form>
    );
}

export default ContactForm;
