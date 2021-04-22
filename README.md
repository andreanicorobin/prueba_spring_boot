# prueba_spring_boot
Prueba_Spring_Boot

El método POST funciona tanto para crear como para modificar el pedido, dependiendo si 
    en el cuerpo del JSON. Si se envía el id se modifica o actualiza, de lo contrario crea uno
    nuevo.
    Método POST:
    Ruta de servicio para crear y modificar:
        http://localhost:8080/pedido
    Ejemplo bodyJSON cuando se crea:
{       "nombre" : "Andres",
        "cedula": "1541231",
        "direccion": "carrera",
        "productos": "Arroz,papa",
        "estadoPago": "cancelado",
        "fechaPedido": "18/04/2021 08:35",
        "valorPedido": 150000,
        "valorIva": null,
        "valorDomicilio": null
    Ejemplo bodyJSON cuando se modifica:
{       "id" : 2,
        "nombre" : "Andres",
        "cedula": "1541231",
        "direccion": "carrera",
        "productos": "Zapatos Adidas",
        "estadoPago": "cancelado",
        "fechaPedido": "18/04/2021 08:35",
        "valorPedido": 200000,
        "valorIva": null,
        s"valorDomicilio": null
}

Este método DELETE elimina el pedido bajo las condiciones dadas abajo.
    Ruta de servicio para eliminar: 
    Método DELETE
    http://localhost:8080/pedido/{id}
    
En la base de Datos de MYSQL, ya hay datos creados que fueron utilizados para probar el código. 
