package com.backEnd.Service;

import com.backEnd.Service.model.Categoria;
import com.backEnd.Service.model.Producto;
import com.backEnd.Service.model.Usuario;
import com.backEnd.Service.repository.CategoriaRepository;
import com.backEnd.Service.repository.ProductoRepository;
import com.backEnd.Service.repository.UsuarioRepository;
import com.backEnd.Service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void run(String... args) throws Exception {
        if (categoriaRepository.count() == 0) {
            Categoria c1 = new Categoria(1, "Instrumento");
            Categoria c2 = new Categoria(2, "Album");
            Categoria c3 = new Categoria(3, "Accesorio");
            categoriaRepository.saveAll(List.of(c1, c2, c3));
        }

        if (productoRepository.count() == 0) {
            Producto p1 = new Producto();
            p1.setId_producto(1);
            p1.setNombre_producto("Piano de cola");
            p1.setImg_url("piano_de_cola");
            p1.setStock(6);
            p1.setPrecio(30000.0);
            p1.setCategoria(categoriaRepository.findById(1).get());
            productoRepository.save(p1);
        }

        if (usuarioRepository.count() == 0) {
            Usuario u = new Usuario();
            u.setCorreo_usuario("test@local.test");
            u.setNombre_usuario("Test User");
            u.setImg_url("");
            u.setIsAdmin(false);
            u.setPassword("test123");
            usuarioService.save(u);
        }
    }
}
