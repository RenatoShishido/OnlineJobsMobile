package com.example.onlinejobsmovies.entities;

import java.util.Objects;

public class Empregos {
    private int id;
    private String descricao;
    private int horasPorSemana;
    private double valor;

    public Empregos(){}

    public Empregos(int id, String descricao, int horasPorSemana, double valor) {
        this.id = id;
        this.descricao = descricao;
        this.horasPorSemana = horasPorSemana;
        this.valor = valor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setHorasPorSemana(int horasPorSemana) {
        this.horasPorSemana = horasPorSemana;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getHorasPorSemana() {
        return horasPorSemana;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empregos empregos = (Empregos) o;
        return id == empregos.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Empregos{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", horasPorSemana=" + horasPorSemana +
                ", valor=" + valor +
                '}';
    }
}
