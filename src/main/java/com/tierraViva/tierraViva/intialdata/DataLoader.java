package com.tierraViva.tierraViva.intialdata;

import com.tierraViva.tierraViva.models.Categoria;
import com.tierraViva.tierraViva.models.Producto;
import com.tierraViva.tierraViva.models.Usuario;
import com.tierraViva.tierraViva.repositories.CategoriaRepository;
import com.tierraViva.tierraViva.repositories.ProductoRepository;
import com.tierraViva.tierraViva.repositories.UsuariosRepository;
import com.tierraViva.tierraViva.services.UsuarioService;
import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;
    private final UsuariosRepository usuariosRepository;
    private final UsuarioService usuarioServices;

    public DataLoader(CategoriaRepository categoriaRepository, ProductoRepository productoRepository, UsuariosRepository usuariosRepository, UsuarioService usuarioServices) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
        this.usuariosRepository = usuariosRepository;
        this.usuarioServices = usuarioServices;
    }

    @Override
    public void run(String... args) throws Exception {
        // Cargamos datos inciales en la base de datos
        // Categorias, productos y usuarios

        // CATEGORIAS
        if (categoriaRepository.count() == 0) {

            Categoria vegetales = new Categoria();
            vegetales.setNombre("Vegetales");
            vegetales.setDescripcion("Productos frescos de origen vegetal");
            categoriaRepository.save(vegetales);

            Categoria frutas = new Categoria();
            frutas.setNombre("Frutas");
            frutas.setDescripcion("Frutas frescas y naturales");
            categoriaRepository.save(frutas);

            Categoria tuberculos = new Categoria();
            tuberculos.setNombre("Tubérculos");
            tuberculos.setDescripcion("Alimentos provenientes de raíces y tubérculos");
            categoriaRepository.save(tuberculos);

            Categoria categoria4 = new Categoria();
            categoria4.setNombre("Cereales");
            categoria4.setDescripcion("Cereales y derivados");
            categoriaRepository.save(categoria4);

            Categoria granos = new Categoria();
            granos.setNombre("Granos");
            granos.setDescripcion("Granos y leguminosas");
            categoriaRepository.save(granos);

            Categoria cafe = new Categoria();
            cafe.setNombre("Café");
            cafe.setDescripcion("Café en grano o molido");
            categoriaRepository.save(cafe);

            if (productoRepository.count() == 0) {

                Producto producto1 = new Producto();
                producto1.setNombre("Banano");
                producto1.setDescripcion("Banano fresco de alta calidad.");
                producto1.setFechaVencimiento(LocalDate.parse("2026-12-20"));
                producto1.setImagen("../assets/images/CatalogoProducto/banano.jpg");
                producto1.setPrecioUnitario(new BigDecimal("3200.00"));
                producto1.setStock(120);
                producto1.setUnidadDePeso("kg");
                producto1.setCategoria(frutas);
                productoRepository.save(producto1);

                Producto producto2 = new Producto();
                producto2.setNombre("Café Pergamino");
                producto2.setDescripcion("Café artesanal tostado y molido.");
                producto2.setFechaVencimiento(LocalDate.parse("2026-03-01"));
                producto2.setImagen("../assets/images/CatalogoProducto/cafe.jpg");
                producto2.setPrecioUnitario(new BigDecimal("18000.00"));
                producto2.setStock(50);
                producto2.setUnidadDePeso("libra");
                producto2.setCategoria(cafe);
                productoRepository.save(producto2);

                Producto producto3 = new Producto();
                producto3.setNombre("Cebolla Cabezona");
                producto3.setDescripcion("Cebolla cabezona fresca ideal para cocinar.");
                producto3.setFechaVencimiento(LocalDate.parse("2026-12-10"));
                producto3.setImagen("../assets/images/CatalogoProducto/cebolla_cabezona.jpg");
                producto3.setPrecioUnitario(new BigDecimal("2200.00"));
                producto3.setStock(180);
                producto3.setUnidadDePeso("kg");
                producto3.setCategoria(vegetales);
                productoRepository.save(producto3);

                Producto producto4 = new Producto();
                producto4.setNombre("Fresas");
                producto4.setDescripcion("Fresas rojas dulces y frescas.");
                producto4.setFechaVencimiento(LocalDate.parse("2026-12-08"));
                producto4.setImagen("../assets/images/CatalogoProducto/fresas.jpg");
                producto4.setPrecioUnitario(new BigDecimal("8500.00"));
                producto4.setStock(35);
                producto4.setUnidadDePeso("libra");
                producto4.setCategoria(frutas);
                productoRepository.save(producto4);

                Producto producto5 = new Producto();
                producto5.setNombre("Fríjol Rojo");
                producto5.setDescripcion("Fríjol bola roja seleccionado.");
                producto5.setFechaVencimiento(LocalDate.parse("2026-05-15"));
                producto5.setImagen("../assets/images/CatalogoProducto/frijol.jpg");
                producto5.setPrecioUnitario(new BigDecimal("4200.00"));
                producto5.setStock(90);
                producto5.setUnidadDePeso("libra");
                producto5.setCategoria(granos);
                productoRepository.save(producto5);

                Producto producto6 = new Producto();
                producto6.setNombre("Papa Pastusa");
                producto6.setDescripcion("Papa pastusa fresca ideal para cocinar.");
                producto6.setFechaVencimiento(LocalDate.parse("2026-12-15"));
                producto6.setImagen("../assets/images/CatalogoProducto/papa.jpg");
                producto6.setPrecioUnitario(new BigDecimal("1800.00"));
                producto6.setStock(250);
                producto6.setUnidadDePeso("kg");
                producto6.setCategoria(tuberculos);
                productoRepository.save(producto6);

                Producto producto7 = new Producto();
                producto7.setNombre("Papaya");
                producto7.setDescripcion("Papaya dulce lista para consumir.");
                producto7.setFechaVencimiento(LocalDate.parse("2026-12-12"));
                producto7.setImagen("../assets/images/CatalogoProducto/papaya.jpg");
                producto7.setPrecioUnitario(new BigDecimal("3500.00"));
                producto7.setStock(45);
                producto7.setUnidadDePeso("kg");
                producto7.setCategoria(frutas);
                productoRepository.save(producto7);

                Producto producto8 = new Producto();
                producto8.setNombre("Tomate Chonto");
                producto8.setDescripcion("Tomate fresco ideal para ensaladas y cocina.");
                producto8.setFechaVencimiento(LocalDate.parse("2026-12-08"));
                producto8.setImagen("../assets/images/CatalogoProducto/tomate.jpg");
                producto8.setPrecioUnitario(new BigDecimal("2700.00"));
                producto8.setStock(110);
                producto8.setUnidadDePeso("kg");
                producto8.setCategoria(vegetales);
                productoRepository.save(producto8);

                Producto producto9 = new Producto();
                producto9.setNombre("Yuca");
                producto9.setDescripcion("Yuca fresca de excelente calidad.");
                producto9.setFechaVencimiento(LocalDate.parse("2026-12-16"));
                producto9.setImagen("../assets/images/CatalogoProducto/yuca.jpg");
                producto9.setPrecioUnitario(new BigDecimal("1700.00"));
                producto9.setStock(95);
                producto9.setUnidadDePeso("kg");
                producto9.setCategoria(tuberculos);
                productoRepository.save(producto9);

                Producto producto10 = new Producto();
                producto10.setNombre("Zanahoria");
                producto10.setDescripcion("Zanahoria fresca y crujiente.");
                producto10.setFechaVencimiento(LocalDate.parse("2026-12-18"));
                producto10.setImagen("../assets/images/CatalogoProducto/zanahoria.jpg");
                producto10.setPrecioUnitario(new BigDecimal("1500.00"));
                producto10.setStock(160);
                producto10.setUnidadDePeso("kg");
                producto10.setCategoria(vegetales);
                productoRepository.save(producto10);
            }
        }

        // USUARIOS
        if(usuariosRepository.count() == 0) {
            Usuario usuario = new Usuario();
            usuario.setNombre("Usuario Administrador");
            usuario.setUsuario("Admin1234");
            usuario.setEmail("admin@correo.com");
            usuario.setNumeroCelular(3214983422L);
            usuario.setContrasena("admin");

            usuarioServices.crearUsuario(usuario);
        }
    }
}
