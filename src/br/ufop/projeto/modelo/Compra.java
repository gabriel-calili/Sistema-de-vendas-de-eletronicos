package br.ufop.projeto.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra {
    private int id;
    private Date data;
    private List<Produto> produtos;
    private double valor;

    public Compra(int id, Date data, List<Produto> produtos) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID da compra deve ser um inteiro positivo.");
        }
        if (data == null) {
            throw new IllegalArgumentException("A data da compra não pode ser nula.");
        }
        if (produtos == null || produtos.isEmpty()) {
            throw new IllegalArgumentException("A lista de produtos da compra não pode ser nula ou vazia.");
        }
        this.id = id;
        this.data = data;
        this.produtos = new ArrayList<>(produtos);
        calculaValor();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Produto> getProdutos() {
        return new ArrayList<>(produtos);
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = new ArrayList<>(produtos);
        calculaValor();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void calculaValor() {
        double total = 0.0;
        for (Produto p : produtos) {
            if (p != null) {
                total += p.getPreco();
            }
        }
        this.valor = total;
    }

    public void atualizarEstoque() {
        for (Produto p : produtos) {
            if (p != null) {
                p.setQuantidade(p.getQuantidade() + 1);
            }
        }
    }
}
