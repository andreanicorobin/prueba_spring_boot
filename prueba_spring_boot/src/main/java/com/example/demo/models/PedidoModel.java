package com.example.demo.models;

import javax.persistence.*;


@Entity
@Table(name = "pedido")
public class PedidoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)

    private Long id;
    private String nombre;
    private String cedula;
    private String direccion;
    private String productos;
    private String estado_pago;
    private Double valor_pedido;
    private Integer valor_domicilio;
    private Double valor_iva;
    private String fecha_pedido;

    public void setValorPedido(Double valor_pedido){
        this.valor_pedido = valor_pedido;
    }

    public Double getValorPedido(){
        return valor_pedido;
    }

    public void setValorDomicilio(Integer valor_domicilio){
        this.valor_domicilio = valor_domicilio;
    }

    public Integer getValorDomicilio(){
        return valor_domicilio;
    }

    public void setValorIva(Double valor_iva){
        this.valor_iva = valor_iva;
    }

    public Double getValorIva(){
        return valor_iva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getEstadoPago() {
        return estado_pago;
    }

    public void setEstadoPago(String estado_pago) {
        this.estado_pago = estado_pago;
    }


    public String getFechaPedido() {
        return fecha_pedido;
    }

    public void setFechaPedido(String fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

}
