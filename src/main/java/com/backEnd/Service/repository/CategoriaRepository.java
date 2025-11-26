package com.backEnd.Service.repository;

import com.backEnd.Service.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
        List<Categoria> findAll();
    }
