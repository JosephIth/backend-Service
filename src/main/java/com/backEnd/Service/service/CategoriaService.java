package com.backEnd.Service.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.backEnd.Service.repository.CategoriaRepository;
import com.backEnd.Service.model.Categoria;
import java.util.List;
import jakarta.transaction.Transactional;

@Service
@Transactional

public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllcategorias(){
        return categoriaRepository.findAll();
    }

    public Categoria findById(int id) {
        return categoriaRepository.findById(id).get();
    }

    public Categoria save(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public void deleteById(int id){
        categoriaRepository.deleteById(id);
    }
}
