package br.ufop.projeto.modelo;

public abstract class Usuario {
    public static final int COMPRADOR = 1;
    public static final int VENDEDOR = 2;
    public static final int GERENTE = 3;

    private int id;
    private String nome;
    private int acesso;

    public Usuario(int id, String nome, int acesso) {
        this.id = id;
        this.nome = nome;
        this.acesso = acesso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAcesso() {
        return acesso;
    }

    public void setAcesso(int acesso) {
        this.acesso = acesso;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario other = (Usuario) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        String tipo = "Comprador";
        if (acesso == VENDEDOR) {
            tipo = "Vendedor";
        } else if (acesso == GERENTE) {
            tipo = "Gerente";
        }
        return tipo + " - ID: " + id + " - Nome: " + nome;
    }
}
