package com.example.eventlink.models;

import java.io.Serializable;

public class Local implements Serializable {
    private int id;
    private String nome;
    private String descricao;
    private int imagemResId; // apenas para teste (depois trocar por String fotoCapaUrl)
    private String morada;
    private String cidade;
    private String codigoPostal;
    private int capacidadeTotal;
    private double precoAluguerBase;

    public Local(int id, String nome, String descricao, int imagemResId, String morada,
                 String cidade, String codigoPostal, int capacidadeTotal, double precoAluguerBase) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.imagemResId = imagemResId;
        this.morada = morada;
        this.cidade = cidade;
        this.codigoPostal = codigoPostal;
        this.capacidadeTotal = capacidadeTotal;
        this.precoAluguerBase = precoAluguerBase;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public int getImagemResId() { return imagemResId; }
    public void setImagemResId(int imagemResId) { this.imagemResId = imagemResId; }

    public String getMorada() { return morada; }
    public void setMorada(String morada) { this.morada = morada; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }

    public int getCapacidadeTotal() { return capacidadeTotal; }
    public void setCapacidadeTotal(int capacidadeTotal) { this.capacidadeTotal = capacidadeTotal; }

    public double getPrecoAluguerBase() { return precoAluguerBase; }
    public void setPrecoAluguerBase(double precoAluguerBase) { this.precoAluguerBase = precoAluguerBase; }
}