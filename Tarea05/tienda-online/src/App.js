import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Home, Navbar, Header, Footer, ProductList, Cart, ContactForm, NotFoundPage, TermsOfService, ScrollToTop, PrivacyPolicy } from './components/';
import './App.css';

function App() {
    return (
        <div className="app">
            <Router>
                <ScrollToTop />
                <Header />
                <Navbar />
                <main className="main-content">
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/products/:id" element={<h2>Detalle del Producto</h2>} />
                        <Route path="/cart" element={<Cart />} />
                        <Route path="/contacto" element={<ContactForm />} />
                        <Route path="/privacidad" element={<PrivacyPolicy />} />
                        <Route path="/terminos-y-condiciones" element={<TermsOfService />} />
                        <Route path="*" element={<NotFoundPage />} />
                    </Routes>
                </main>
                <Footer />
            </Router>
        </div>
    );
}

export default App;
