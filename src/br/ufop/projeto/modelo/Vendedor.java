package br.ufop.projeto.modelo;

import br.ufop.projeto.excecoes.EstoqueInsuficienteException;
import java.util.List;

public class Vendedor extends Usuario {
    private String cpf;

    public Vendedor(int id, String nome, String cpf) {
        super(id, nome, Usuario.VENDEDOR);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void realizarVenda(Venda venda) throws EstoqueInsuficienteException {
        venda.verificaEstoque();
        venda.atualizarEstoque();
        System.out.println("Venda realizada pelo vendedor " + getNome());
    }

    public void gerarRelatorioVendas(List<Venda> vendas) {
        System.out.println("\n--- RELATORIO DE VENDAS ---");
        double total = 0;
        for (Venda v : vendas) {
            System.out.println("ID: " + v.getId() + " | Data: " + v.getData() + " | Valor: R$ " + v.getValor());
            total += v.getValor();
        }
        System.out.println("Total faturado em vendas: R$ " + total);
        System.out.println("---------------------------\n");
    }

    @Override
    public String toString() {
        return super.toString() + " (CPF: " + cpf + ")";
    }
}
