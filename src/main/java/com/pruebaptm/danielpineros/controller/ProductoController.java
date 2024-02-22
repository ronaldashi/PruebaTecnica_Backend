package com.pruebaptm.danielpineros.controller;

import com.pruebaptm.danielpineros.entities.Producto;
import com.pruebaptm.danielpineros.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> obtenerTodosProductos() {
        return productoService.obtenerTodosProductos();
    }

    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable(value = "id") Long id, @RequestBody Producto productoDetails) {
        return productoService.actualizarProducto(id, productoDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable(value = "id") Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/calcular-valor-total")
    public double calcularValorTotal() {
        return productoService.calcularValorTotalInventario();
    }

    @GetMapping("/generar-combinaciones")
    public List<List<Producto>> generarCombinaciones(@RequestParam double valorObjetivo) {
        return productoService.generarCombinaciones(valorObjetivo);
    }
}
