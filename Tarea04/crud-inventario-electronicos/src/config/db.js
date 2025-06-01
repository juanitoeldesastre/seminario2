const mysql = require('mysql');
const connection = mysql.createConnection({
    host: 'localhost',
    user: 'gestor_inventario',
    password: 'ElectroDB_123',
    database: 'inventario_tienda'
});

connection.connect(err => {
    if (err) throw err;
    console.log("Conectado a la base de datos MySQL");
});

module.exports = connection;