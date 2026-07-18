package br.ufop.projeto.modelo;

import br.ufop.projeto.excecoes.EstoqueInsuficienteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {
    private int id;
    private Date data;
    private List<Produto> produtos;
    private double valor;

    public Venda(int id, Date data, List<Produto> produtos) {
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
        double total = 0;
        for (Produto p : produtos) {
            if (p != null) {
                total += p.getPreco();
            }
        }
        this.valor = total;
    }

    public void verificaEstoque() throws EstoqueInsuficienteException {
        for (Produto p : produtos) {
            if (p != null) {
                int solicitados = 0;
                for (Produto p2 : produtos) {
                    if (p2 != null && p2.getId() == p.getId()) {
                        solicitados++;
                    }
                }
                if (p.getQuantidade() < solicitados) {
                    throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + p.getNome() + " | Disponivel: " + p.getQuantidade() + ", Solicitado: " + solicitados);
                }
            }
        }
    }

    public void atualizarEstoque() {
        for (Produto p : produtos) {
            if (p != null) {
                int novaQtd = p.getQuantidade() - 1;
                if (novaQtd < 0) {
                    novaQtd = 0;
                }
                p.setQuantidade(novaQtd);
            }
        }
    }
}
