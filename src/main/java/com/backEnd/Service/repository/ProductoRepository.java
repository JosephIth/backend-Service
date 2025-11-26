package com.backEnd.Service.repository;

import com.backEnd.Service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Consulta explícita para evitar ambigüedades en nombres de propiedades anidadas
    @Query("SELECT p FROM Producto p WHERE p.categoria.id_categoria = :idCategoria")
    List<Producto> findByCategoriaId(@Param("idCategoria") int idCategoria);

    // Otros métodos del repo (si los hay)...
}
