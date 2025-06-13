import { useState } from 'react';
import './ContactForm.css';

function ContactForm() {
    const [formData, setFormData] = useState({ name: '', surname: '' ,email: '', phone: '', message: '' });
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };
    const handleSubmit = (e) => {
        e.preventDefault();
        alert(`Gracias por contactarnos, ${formData.name}!`);
    };
    return (
        <form className="contact-form" onSubmit={handleSubmit}>
            <span>Contáctanos</span>
            <input type="text" name="name" placeholder="Nombre" onChange={handleChange}
            />
            <input type="email" name="email" placeholder="Email" onChange={handleChange}
            />
            <input type="number" name="phone" placeholder="Telefono" onChange={handleChange}
            />
            <textarea name="message" placeholder="Mensaje" onChange={handleChange}
            />
            <button type="submit">Enviar</button>
        </form>
    );
}
export default ContactForm;