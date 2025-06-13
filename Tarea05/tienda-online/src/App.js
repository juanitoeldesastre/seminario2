import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Navbar, Header, Footer, ProductList, Cart, ContactForm, NotFoundPage } from './components/';
import './App.css';

function App() {
    return (
        <div className="app">
            <Router>
                <Header />
                <Navbar />
                <main className="main-content">
                    <Routes>
                        <Route path="/" element={<ProductList />} />
                        <Route path="/products/:id" element={<h2>Detalle del Producto</h2>} />
                        <Route path="/cart" element={<Cart />} />
                        <Route path="/contacto" element={<ContactForm />} />
                        <Route path="*" element={<NotFoundPage />} />
                    </Routes>
                </main>
                <Footer />
            </Router>
        </div>
    );
}

export default App;
