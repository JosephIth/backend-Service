package com.backEnd.Service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.backEnd.Service.repository.ProductoRepository;
import com.backEnd.Service.model.Producto;

import java.util.List;

@Service
@Transactional
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    public Producto findById(int id){
        return productoRepository.findById(id).get();
    }

    public Producto save(Producto producto){
        return productoRepository.save(producto);
    }

    public void deleteById(int id){
        productoRepository.deleteById(id);
    }

    // Llama al método nuevo con @Query
    public List<Producto> findByCategoriaId(int id_categoria){
        return productoRepository.findByCategoriaId(id_categoria);
    }
}
