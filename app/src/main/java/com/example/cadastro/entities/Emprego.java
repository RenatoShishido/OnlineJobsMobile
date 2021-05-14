package com.example.cadastro.entities;

import java.io.Serializable;

public class Emprego implements Serializable {
    private int id;
    private String descricao;
    private int horas;
    private double valor;

    public Emprego() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return
                "descricao: " + descricao.toString() + "\n" +
                "horas: " + horas + "\n" +
                "valor: " + valor;
    }
}
