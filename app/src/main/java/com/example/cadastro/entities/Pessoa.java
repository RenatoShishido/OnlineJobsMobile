package com.example.cadastro.entities;

import com.example.cadastro.util.ItemAdapter;

import java.io.Serializable;

public class Pessoa implements Serializable, ItemAdapter {
    private int id;
    private int vagaId;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private Emprego emprego;


    public Pessoa(){
    }


    public int getId() {
        return id;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getVagaId() {
        return vagaId;
    }

    public void setVagaId(int vagaId) {
        this.vagaId = vagaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Emprego getEmprego() {return emprego; }

    public void setEmprego(Emprego emprego) { this.emprego = emprego; }




    @Override
    public String toString(){
        return "Nome: " + nome.toString()+" \nEmail: "+email.toString()+"\nCpf: "+cpf.toString()+
                "\nTelefone: "+telefone.toString() +
                "\nEmprego: "+emprego != null? emprego.getDescricao().toString():"Nenhum";
    }

    @Override
    public String getString() {
        return "Nome: " + nome.toString()+" \nEmail: "+email.toString()+"\nCpf: "+cpf.toString()+
                "\nTelefone: "+telefone.toString() +
                "\nEmprego: "+emprego != null? emprego.getDescricao().toString():"Nenhum";
    }
}
