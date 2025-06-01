const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const productosRoutes = require('./routes/productos');

app.use('/api', productosRoutes);
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

const port = 3000;
app.listen(port,() => {
    console.log(`Servidor ejecutándose en http://localhost:${port}`);
});