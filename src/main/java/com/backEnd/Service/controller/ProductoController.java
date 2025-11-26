package com.backEnd.Service.controller;

import com.backEnd.Service.model.Producto;
import com.backEnd.Service.model.Categoria;
import com.backEnd.Service.service.ProductoService;
import com.backEnd.Service.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable int id) {
        try {
            Producto producto = productoService.findById(id);
            return ResponseEntity.ok(producto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/categoria/{id_categoria}")
    public ResponseEntity<List<Producto>> getByCategoria(@PathVariable int id_categoria) {
        try {
            List<Producto> productos = productoService.findByCategoriaId(id_categoria);
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Producto producto) {
        try {
            if (producto.getCategoria() == null) {
                return ResponseEntity.badRequest().body("La categoria es requerida");
            }
            // validar que la categoria exista
            int idCat = producto.getCategoria().getId_categoria();
            Categoria cat = categoriaService.findById(idCat);
            producto.setCategoria(cat);
            Producto saved = productoService.save(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Categoria no encontrada");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Producto producto) {
        try {
            Producto existing = productoService.findById(id);
            if (producto.getCategoria() != null) {
                // validar categoria
                int idCat = producto.getCategoria().getId_categoria();
                Categoria cat = categoriaService.findById(idCat);
                producto.setCategoria(cat);
            } else {
                // mantener la categoria existente si no viene en el body
                producto.setCategoria(existing.getCategoria());
            }
            producto.setId_producto(id);
            Producto updated = productoService.save(producto);
            return ResponseEntity.ok(updated);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        try {
            productoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}