package com.example.trabalhopontodevenda.model;

public class Venda {

    private static Venda instance;
    private String pedido;
    private double valor;
    private double valorPago;

    public Venda() {
    }

    public Venda(String pedido, double valor, double valorPago, String metodoPagamento) {
        this.pedido = pedido;
        this.valor = valor;
        this.valorPago = valorPago;
    }

    public static Venda getInstance() {
        if (instance == null) {
            instance = new Venda();
        }
        return instance;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

}
