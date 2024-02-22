package com.pruebaptm.danielpineros.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadEnStock;

    public Producto() {
    }

    public double calcularValorTotal() {
        return precio * cantidadEnStock;
    }

    public static List<List<Producto>> generarCombinaciones(List<Producto> productos, double valorObjetivo) {
        List<List<Producto>> combinaciones = new ArrayList<>();

        for (int i = 0; i < productos.size(); i++) {
            for (int j = i + 1; j < productos.size(); j++) {
                double suma = productos.get(i).getPrecio() + productos.get(j).getPrecio();
                if (suma <= valorObjetivo) {
                    combinaciones.add(Arrays.asList(productos.get(i), productos.get(j)));
                }
                for (int k = j + 1; k < productos.size(); k++) {
                    suma = productos.get(i).getPrecio() + productos.get(j).getPrecio() + productos.get(k).getPrecio();
                    if (suma <= valorObjetivo) {
                        combinaciones.add(Arrays.asList(productos.get(i), productos.get(j), productos.get(k)));
                    }
                }
            }
        }
        combinaciones.sort((c1, c2) -> {
            double suma1 = c1.stream().mapToDouble(Producto::getPrecio).sum();
            double suma2 = c2.stream().mapToDouble(Producto::getPrecio).sum();
            return Double.compare(suma2, suma1);
        });

        return combinaciones.subList(0, Math.min(combinaciones.size(), 5));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }
}
