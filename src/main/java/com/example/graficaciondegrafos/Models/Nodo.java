package com.example.graficaciondegrafos.Models;

public class Nodo implements Cloneable{
    private String name;
    private  String padre;
    private Double fn;
    private Double hn;
    private Double gn;


    public Nodo(String name, String padre, Double fn,Double gn,Double hn) {
        this.name = name;
        this.padre = padre;
        this.fn = fn;
        this.hn = hn;
        this.gn = gn;
    }

    @Override
    public Nodo clone() throws CloneNotSupportedException {
        return (Nodo) super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public Double getFn() {
        return fn;
    }

    public void setFn(Double fn) {
        this.fn = fn;
    }

    public Double getHn() {
        return hn;
    }

    public void setHn(Double hn) {
        this.hn = hn;
    }

    public Double getGn() {
        return gn;
    }

    public void setGn(Double gn) {
        this.gn = gn;
    }
}
