package br.ufop.projeto.servico;

import br.ufop.projeto.modelo.Produto;
import br.ufop.projeto.modelo.Usuario;
import br.ufop.projeto.modelo.Compra;
import br.ufop.projeto.modelo.Venda;
import java.util.ArrayList;
import java.util.List;

public class BancoDados {
    private static BancoDados instancia;

    private List<Produto> produtos;
    private List<Usuario> usuarios;
    private List<Venda> vendas;
    private List<Compra> compras;

    private BancoDados() {
        produtos = new ArrayList<>();
        usuarios = new ArrayList<>();
        vendas = new ArrayList<>();
        compras = new ArrayList<>();
    }

    public static BancoDados getInstancia() {
        if (instancia == null) {
            instancia = new BancoDados();
        }
        return instancia;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void cadastrarProduto(Produto p) {
        produtos.add(p);
    }

    public void cadastrarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public Produto buscarProdutoPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }
}
