package com.example.trabalhopontodevenda.model;

public class ValorTotal {
    private static ValorTotal instance;
    private double valorTotal;

    private ValorTotal() {
    }

    public static ValorTotal getInstance() {
        if (instance == null) {
            instance = new ValorTotal();
        }
        return instance;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
