import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import {Navbar, Header, Footer, ProductList, Cart, ContactForm, NotFoundPage} from './components/';

function App() {
  return (
      <Router>
        <Header />
        <Navbar />
        <Routes>
          <Route path="/" element={<ProductList />} />
          <Route path="/products/:id" element={<h2>Detalle del Producto</h2>} />
          <Route path="/cart" element={<Cart />} />
          <Route path="/contact" element={<ContactForm />} />
          <Route path="*" element={<NotFoundPage />} />
        </Routes>
        <Footer />
      </Router>
  );
}

export default App;