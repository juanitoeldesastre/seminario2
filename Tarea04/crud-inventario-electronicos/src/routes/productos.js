const express = require('express');
const router = express.Router();
const productosController = require('../controllers/productosController');

router.get('/productos', productosController.listarProductos);
router.post('/productos', productosController.crearProducto);
router.get('/productos/:id', productosController.obtenerProductoPorId);
router.put('/productos/:id', productosController.actualizarProducto);
router.delete('/productos/:id', productosController.eliminarProducto);

module.exports = router;