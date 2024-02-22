package com.pruebaptm.danielpineros.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import com.pruebaptm.danielpineros.entities.Producto;
import com.pruebaptm.danielpineros.repository.ProductoRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodosProductos() {
        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto productoDetails) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));

        producto.setNombre(productoDetails.getNombre());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setCantidadEnStock(productoDetails.getCantidadEnStock());

        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));

        productoRepository.delete(producto);
    }

    public double calcularValorTotalInventario() {
        List<Producto> productos = productoRepository.findAll();
        double valorTotal = 0;
        for (Producto producto : productos) {
            valorTotal += producto.getPrecio() * producto.getCantidadEnStock();
        }
        return valorTotal;
    }

    public List<List<Producto>> generarCombinaciones(double valorObjetivo) {
        List<Producto> productos = productoRepository.findAll();
        List<List<Producto>> combinaciones = new ArrayList<>();
        generarCombinacionesUtil(productos, valorObjetivo, 0, new ArrayList<>(), combinaciones);


        List<List<Producto>> combinacionesFiltradas = combinaciones.stream()
                .filter(combination -> combination.size() >= 2 && combination.size() <= 3)
                .filter(combination -> calcularPrecioTotal(combination) <= valorObjetivo)
                .sorted(Comparator.comparingDouble(this::calcularPrecioTotal).reversed())
                .limit(5)
                .collect(Collectors.toList());

        return combinacionesFiltradas;
    }

    private void generarCombinacionesUtil(List<Producto> productos, double valorObjetivo, int indiceActual, List<Producto> combinacionActual, List<List<Producto>> combinaciones) {
        double sumaPrecios = calcularPrecioTotal(combinacionActual);

        if (sumaPrecios <= valorObjetivo) {
            combinaciones.add(new ArrayList<>(combinacionActual));
        }

        if (sumaPrecios < valorObjetivo) {
            for (int i = indiceActual; i < productos.size(); i++) {
                combinacionActual.add(productos.get(i));
                generarCombinacionesUtil(productos, valorObjetivo, i + 1, combinacionActual, combinaciones);
                combinacionActual.remove(combinacionActual.size() - 1);
            }
        }
    }
    private double calcularPrecioTotal(List<Producto> productos) {
        return productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
    }

}
