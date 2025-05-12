package com.example.fruteria;

public class Fruta {
    private int id;
    private String nombre;
    private double precioKg;
    private double stockKg;

    public Fruta(int id, String nombre, double precioKg, double stockKg) {
        this.id = id;
        this.nombre = nombre;
        this.precioKg = precioKg;
        this.stockKg = stockKg;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioKg() {
        return precioKg;
    }

    public void setPrecioKg(double precioKg) {
        this.precioKg = precioKg;
    }

    public double getStockKg() {
        return stockKg;
    }

    public void setStockKg(double stockKg) {
        this.stockKg = stockKg;
    }
}
