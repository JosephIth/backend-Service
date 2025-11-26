package com.backEnd.Service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    
    @Id
    @Column(columnDefinition = "INT(3)")
    private int id_producto;           

    @Column(length = 30, nullable = false)
    private String nombre_producto;

    @Column(length = 50, nullable = false)
    private String img_url;

    @Column(columnDefinition = "INT(3)", nullable = false)
    private int stock;

    @Column(nullable = false)
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria", nullable = false)
    private Categoria categoria;
    
}
