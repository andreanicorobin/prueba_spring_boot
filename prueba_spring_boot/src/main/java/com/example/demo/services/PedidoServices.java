package com.example.demo.services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.demo.models.PedidoModel;
import com.example.demo.repositories.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServices {
   @Autowired
    PedidoRepository pedidoRepository;

    public ArrayList<PedidoModel> obtenerPedidos(){
        return (ArrayList<PedidoModel>) pedidoRepository.findAll();
    }

    public PedidoModel guardarPedido(PedidoModel pedido){
       
        return pedidoRepository.save(pedido);
    }

    public Optional<PedidoModel> ObtenerPorId(Long id){
        return pedidoRepository.findById(id);
    }

    public boolean eliminarPedido(Long id) {
        try {
            pedidoRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}
