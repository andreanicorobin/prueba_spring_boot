package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Optional;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  

import com.example.demo.models.PedidoModel;
import com.example.demo.services.PedidoServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pedido")
public class PedidoController {
   @Autowired
    PedidoServices pedidoService;

    @GetMapping()
    public ArrayList<PedidoModel> obtenerPedidos() {
        return pedidoService.obtenerPedidos();
    }

    /* Este método funciona tanto para crear como para modificar el pedido, dependiendo si 
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
}*/
    @PostMapping()
    public PedidoModel guardarPedido(@RequestBody PedidoModel pedido){
        double valor_pedido = pedido.getValorPedido();
        double valor_iva;
        int valor_domicilio;
        if(valor_pedido >= 70000 && valor_pedido < 100000){
            valor_iva = (valor_pedido * 0.19);
            valor_domicilio = 3000;
            pedido.setValorIva(valor_iva);
            pedido.setValorDomicilio(valor_domicilio);
            pedido.setValorPedido(valor_pedido + valor_iva);
        } else if(valor_pedido > 100000){
            valor_iva = (valor_pedido * 0.19);
            valor_domicilio = 0;
            pedido.setValorIva(valor_iva);
            pedido.setValorDomicilio(valor_domicilio);
            pedido.setValorPedido(valor_pedido + valor_iva);
        }else{
            valor_iva = (valor_pedido * 0.19);
            valor_domicilio = 3000;
            pedido.setValorIva(valor_iva);
            pedido.setValorDomicilio(valor_domicilio);
            pedido.setValorPedido(valor_pedido);
        }
        return this.pedidoService.guardarPedido(pedido);
    }

    @GetMapping( path ="/{id}")
    public Optional<PedidoModel> obtenerPedidoPorId(@PathVariable("id") Long id) {
        return this.pedidoService.ObtenerPorId(id);
    }

    /* Este método elimina el pedido bajo las condiciones dadas abajo.
    Ruta de servicio para eliminar: 
    Método DELETE
    http://localhost:8080/pedido/{id}*/

    @DeleteMapping( path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) throws ParseException{
        Optional<PedidoModel> pedido = obtenerPedidoPorId(id);
        String fecha = pedido.get().getFechaPedido();
        double valorpedido = pedido.get().getValorPedido();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");  
        LocalDateTime now = LocalDateTime.now();  
        String fechahoy = dtf.format(now);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date1 = formatter.parse(fecha);
        Date date2 = formatter.parse(fechahoy);

        long diff = date2.getTime() - date1.getTime(); 
        long diffHours = diff / (60 * 60 * 1000);

        String mensaje;

        if(diffHours <= 12){
            boolean ok = this.pedidoService.eliminarPedido(id);
            mensaje = "Se eliminó correctamente";
        }else{
            boolean ok = this.pedidoService.eliminarPedido(id);
            mensaje = "Se cancela el pedido pero hay que pagar el valor de $" + (valorpedido * 0.1);
        }
        return mensaje;

    }
}
