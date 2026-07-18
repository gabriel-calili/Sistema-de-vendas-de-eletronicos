package br.ufop.projeto;

import br.ufop.projeto.modelo.*;
import br.ufop.projeto.excecoes.*;
import br.ufop.projeto.servico.*;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BancoDados banco = BancoDados.getInstancia();

    public static void main(String[] args) {
        inicializarDadosTeste();
        exibirBemVindo();
        menuPrincipal();
    }

    private static void inicializarDadosTeste() {
        banco.cadastrarProduto(new Notebook(101, "Dell Inspiron 15", "Dell", 4500.00, 10, 3, "Intel i5", 8, 256));
        banco.cadastrarProduto(new Notebook(102, "MacBook Air M2", "Apple", 9800.00, 5, 2, "Apple M2", 8, 512));
        banco.cadastrarProduto(new Notebook(103, "Lenovo ThinkPad L14", "Lenovo", 5200.00, 8, 2, "Intel i7", 16, 512));
        banco.cadastrarProduto(new Notebook(104, "HP ProBook 440", "HP", 4100.00, 12, 3, "Intel i5", 8, 256));
        banco.cadastrarProduto(new Notebook(105, "Asus Zenbook 14", "Asus", 6800.00, 6, 2, "AMD Ryzen 7", 16, 512));

        banco.cadastrarProduto(new Smartphone(201, "Galaxy S23", "Samsung", 3999.90, 8, 3, "Snapdragon Gen 2", 8, 256, 6.1));
        banco.cadastrarProduto(new Smartphone(202, "iPhone 14 Pro", "Apple", 7499.00, 4, 3, "A16 Bionic", 6, 128, 6.1));
        banco.cadastrarProduto(new Smartphone(203, "Xiaomi Redmi Note 12", "Xiaomi", 1599.00, 15, 4, "Snapdragon 685", 4, 128, 6.67));
        banco.cadastrarProduto(new Smartphone(204, "Motorola Edge 40", "Motorola", 2799.00, 10, 2, "Dimensity 8020", 8, 256, 6.55));
        banco.cadastrarProduto(new Smartphone(205, "Google Pixel 7", "Google", 4299.00, 5, 2, "Google Tensor G2", 8, 128, 6.3));

        banco.cadastrarProduto(new Mouse(301, "Logitech MX Master 3S", "Logitech", 550.00, 15, 5, 8000, false));
        banco.cadastrarProduto(new Mouse(302, "Razer DeathAdder Essential", "Razer", 180.00, 3, 4, 6400, true));
        banco.cadastrarProduto(new Mouse(303, "Corsair Harpoon PRO", "Corsair", 220.00, 20, 5, 12000, true));
        banco.cadastrarProduto(new Mouse(304, "HyperX Pulsefire Core", "HyperX", 199.90, 12, 3, 6200, true));
        banco.cadastrarProduto(new Mouse(305, "Redragon Cobra M711", "Redragon", 150.00, 18, 4, 10000, true));

        banco.cadastrarUsuario(new Gerente(1, "Carlos Gerente", "111.222.333-44"));
        banco.cadastrarUsuario(new Vendedor(2, "Ana Vendedora", "555.666.777-88"));
        banco.cadastrarUsuario(new Comprador(3, "Gabriel Comprador"));
        banco.cadastrarUsuario(new Comprador(4, "Rafael Comprador"));
    }

    private static void exibirBemVindo() {
        System.out.println("---------------------------------------------------------------------");
        System.out.println("              SISTEMA DE VENDAS DE ELETRONICOS                       ");
        System.out.println("  Disciplina: Programacao de Computadores II (CSI102)                ");
        System.out.println("  Alunos: Gabriel Calili, Rafael Fiuza                               ");
        System.out.println("---------------------------------------------------------------------");
    }

    private static void menuPrincipal() {
        while (true) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Entrar como Gerente");
            System.out.println("2. Entrar como Vendedor");
            System.out.println("3. Entrar como Comprador");
            System.out.println("4. Cadastrar Novo Usuario");
            System.out.print("0. Sair do Sistema\nEscolha: ");

            try {
                int opcao = lerOpcaoInteira();
                switch (opcao) {
                    case 1:
                        menuGerente();
                        break;
                    case 2:
                        menuVendedor();
                        break;
                    case 3:
                        menuComprador();
                        break;
                    case 4:
                        cadastrarUsuarioCLI();
                        break;
                    case 0:
                        System.out.println("\nSaindo...");
                        System.exit(0);
                    default:
                        System.out.println("Opcao invalida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void menuGerente() {
        Gerente gerente = (Gerente) buscarUsuarioPorNivel(Usuario.GERENTE, "Gerente");
        if (gerente == null) return;

        while (true) {
            System.out.println("\n--- MENU GERENTE (Nome: " + gerente.getNome() + ") ---");
            System.out.println("1. Relatorio de Estoque Geral");
            System.out.println("2. Definir Quantidade Minima de Alerta");
            System.out.println("3. Cadastrar Novo Produto");
            System.out.println("4. Historico de Transacoes");
            System.out.println("5. Relatorio de Lucro");
            System.out.print("0. Voltar\nEscolha: ");

            int opcao = lerOpcaoInteira();
            if (opcao == 0) break;

            try {
                switch (opcao) {
                    case 1:
                        gerente.gerarRelatorio(banco.getProdutos().toArray(new Produto[0]));
                        break;
                    case 2:
                        definirQuantidadeMinimaCLI(gerente);
                        break;
                    case 3:
                        cadastrarNovoProdutoCLI();
                        break;
                    case 4:
                        exibirHistoricoTransacoes();
                        break;
                    case 5:
                        gerente.gerarRelatorioLucro(banco.getVendas(), banco.getCompras());
                        break;
                    default:
                        System.out.println("Opcao invalida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void definirQuantidadeMinimaCLI(Gerente gerente) {
        System.out.println("\n-- DEFINIR ESTOQUE MINIMO --");
        listarProdutosSimplificado();
        System.out.print("Digite o ID do produto: ");
        int id = lerOpcaoInteira();
        Produto p = banco.buscarProdutoPorId(id);
        if (p == null) {
            System.out.println("Produto nao encontrado.");
            return;
        }
        System.out.print("Digite a quantidade minima: ");
        int min = lerOpcaoInteira();
        gerente.definirQntMinima(p, min);
    }

    private static void cadastrarNovoProdutoCLI() {
        System.out.println("\n-- CADASTRAR NOVO PRODUTO --");
        System.out.println("1. Notebook");
        System.out.println("2. Smartphone");
        System.out.println("3. Mouse");
        System.out.print("Escolha o tipo: ");
        int tipo = lerOpcaoInteira();

        System.out.print("ID do Produto: ");
        int id = lerOpcaoInteira();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Fabricante: ");
        String fabricante = scanner.nextLine();
        System.out.print("Preco: ");
        double preco = lerDouble();
        System.out.print("Quantidade Inicial: ");
        int qtd = lerOpcaoInteira();
        System.out.print("Quantidade Minima Alerta: ");
        int qtdMin = lerOpcaoInteira();

        switch (tipo) {
            case 1:
                System.out.print("Processador: ");
                String procN = scanner.nextLine();
                System.out.print("RAM (GB): ");
                int ramN = lerOpcaoInteira();
                System.out.print("SSD (GB): ");
                int memN = lerOpcaoInteira();
                banco.cadastrarProduto(new Notebook(id, nome, fabricante, preco, qtd, qtdMin, procN, ramN, memN));
                System.out.println("Notebook cadastrado com sucesso.");
                break;
            case 2:
                System.out.print("Processador: ");
                String procS = scanner.nextLine();
                System.out.print("RAM (GB): ");
                int ramS = lerOpcaoInteira();
                System.out.print("Armazenamento (GB): ");
                int memS = lerOpcaoInteira();
                System.out.print("Tamanho Tela (polegadas): ");
                double telaS = lerDouble();
                banco.cadastrarProduto(new Smartphone(id, nome, fabricante, preco, qtd, qtdMin, procS, ramS, memS, telaS));
                System.out.println("Smartphone cadastrado com sucesso.");
                break;
            case 3:
                System.out.print("DPI Maximo: ");
                int dpi = lerOpcaoInteira();
                System.out.print("Com fio? (true/false): ");
                boolean comFio = Boolean.parseBoolean(scanner.nextLine());
                banco.cadastrarProduto(new Mouse(id, nome, fabricante, preco, qtd, qtdMin, dpi, comFio));
                System.out.println("Mouse cadastrado com sucesso.");
                break;
            default:
                System.out.println("Tipo invalido.");
        }
    }

    private static void exibirHistoricoTransacoes() {
        System.out.println("\n--- HISTORICO DE TRANSACOES ---");
        List<Venda> vendas = banco.getVendas();
        List<Compra> compras = banco.getCompras();
        if (vendas.isEmpty() && compras.isEmpty()) {
            System.out.println("Nenhuma transacao registrada.");
            return;
        }
        for (Venda v : vendas) {
            System.out.println("ID: " + v.getId() + " | Tipo: VENDA | Data: " + v.getData() + " | Total: R$ " + v.getValor());
        }
        for (Compra c : compras) {
            System.out.println("ID: " + c.getId() + " | Tipo: COMPRA | Data: " + c.getData() + " | Total: R$ " + c.getValor());
        }
        System.out.println("--------------------------------");
    }

    private static void menuVendedor() {
        Vendedor vendedor = (Vendedor) buscarUsuarioPorNivel(Usuario.VENDEDOR, "Vendedor");
        if (vendedor == null) return;

        while (true) {
            System.out.println("\n--- MENU VENDEDOR (Nome: " + vendedor.getNome() + ") ---");
            System.out.println("1. Realizar Nova Venda");
            System.out.println("2. Consultar Estoque");
            System.out.println("3. Relatorio de Vendas");
            System.out.print("0. Voltar\nEscolha: ");

            int opcao = lerOpcaoInteira();
            if (opcao == 0) break;

            try {
                switch (opcao) {
                    case 1:
                        realizarVendaCLI(vendedor);
                        break;
                    case 2:
                        listarProdutosDetalhado();
                        break;
                    case 3:
                        vendedor.gerarRelatorioVendas(banco.getVendas());
                        break;
                    default:
                        System.out.println("Opcao invalida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void realizarVendaCLI(Vendedor vendedor) throws EstoqueInsuficienteException {
        System.out.println("\n-- NOVA VENDA --");
        List<Produto> carrinho = selecionarCarrinhoProdutos();
        if (carrinho.isEmpty()) {
            System.out.println("Venda cancelada.");
            return;
        }

        int transacaoId = banco.getCompras().size() + banco.getVendas().size() + 1;
        Venda venda = new Venda(transacaoId, new Date(), carrinho);

        vendedor.realizarVenda(venda);
        banco.getVendas().add(venda);
    }

    private static void menuComprador() {
        Comprador comprador = (Comprador) buscarUsuarioPorNivel(Usuario.COMPRADOR, "Comprador");
        if (comprador == null) return;

        while (true) {
            System.out.println("\n--- MENU COMPRADOR (Nome: " + comprador.getNome() + ") ---");
            System.out.println("1. Ver Catalogo de Produtos");
            System.out.println("2. Comprar/Repor Estoque");
            System.out.println("3. Relatorio de Compras");
            System.out.print("0. Voltar\nEscolha: ");

            int opcao = lerOpcaoInteira();
            if (opcao == 0) break;

            try {
                switch (opcao) {
                    case 1:
                        listarProdutosDetalhado();
                        break;
                    case 2:
                        realizarCompraCompradorCLI(comprador);
                        break;
                    case 3:
                        comprador.gerarRelatorioCompras(banco.getCompras());
                        break;
                    default:
                        System.out.println("Opcao invalida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void realizarCompraCompradorCLI(Comprador comprador) {
        System.out.println("\n-- COMPRA DE REPOSICAO --");
        listarProdutosSimplificado();
        System.out.print("Digite o ID do produto: ");
        int id = lerOpcaoInteira();
        Produto p = banco.buscarProdutoPorId(id);
        if (p == null) {
            System.out.println("Produto nao encontrado.");
            return;
        }
        System.out.print("Quantidade a comprar: ");
        int qtd = lerOpcaoInteira();
        if (qtd <= 0) {
            System.out.println("Erro: quantidade deve ser positiva.");
            return;
        }

        List<Produto> lote = new ArrayList<>();
        for (int i = 0; i < qtd; i++) {
            lote.add(p);
        }

        int transacaoId = banco.getCompras().size() + banco.getVendas().size() + 1;
        Compra compra = new Compra(transacaoId, new Date(), lote);

        comprador.realizarCompra(compra);
        banco.getCompras().add(compra);
    }

    private static List<Produto> selecionarCarrinhoProdutos() {
        List<Produto> carrinho = new ArrayList<>();
        while (true) {
            System.out.println("\n-- ADICIONAR AO CARRINHO --");
            listarProdutosSimplificado();
            System.out.print("Digite o ID do produto para adicionar (ou 0 para terminar): ");
            int id = lerOpcaoInteira();
            if (id == 0) break;

            Produto p = banco.buscarProdutoPorId(id);
            if (p != null) {
                carrinho.add(p);
                System.out.println("Produto adicionado: " + p.getNome());
            } else {
                System.out.println("Produto nao encontrado.");
            }
        }
        return carrinho;
    }

    private static void cadastrarUsuarioCLI() {
        System.out.println("\n-- CADASTRAR NOVO USUARIO --");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Perfil:");
        System.out.println("1. Comprador");
        System.out.println("2. Vendedor");
        System.out.println("3. Gerente");
        System.out.print("Escolha: ");
        int nivelOpcao = lerOpcaoInteira();

        int novoId = banco.getUsuarios().size() + 1;

        switch (nivelOpcao) {
            case 1:
                banco.cadastrarUsuario(new Comprador(novoId, nome));
                System.out.println("Comprador cadastrado. ID: " + novoId);
                break;
            case 2:
                System.out.print("CPF: ");
                String cpfV = scanner.nextLine();
                banco.cadastrarUsuario(new Vendedor(novoId, nome, cpfV));
                System.out.println("Vendedor cadastrado. ID: " + novoId);
                break;
            case 3:
                System.out.print("CPF: ");
                String cpfG = scanner.nextLine();
                banco.cadastrarUsuario(new Gerente(novoId, nome, cpfG));
                System.out.println("Gerente cadastrado. ID: " + novoId);
                break;
            default:
                System.out.println("Perfil invalido.");
        }
    }

    private static Usuario buscarUsuarioPorNivel(int acesso, String rotulo) {
        System.out.println("\nSelecione um " + rotulo + " cadastrado:");
        List<Usuario> filtrados = new ArrayList<>();
        for (Usuario u : banco.getUsuarios()) {
            if (u.getAcesso() == acesso) {
                filtrados.add(u);
                System.out.println(u);
            }
        }

        if (filtrados.isEmpty()) {
            System.out.println("Nenhum " + rotulo + " cadastrado.");
            return null;
        }

        System.out.print("Digite o ID: ");
        int id = lerOpcaoInteira();
        Usuario selecionado = banco.buscarUsuarioPorId(id);
        if (selecionado == null || selecionado.getAcesso() != acesso) {
            System.out.println("ID invalido ou incorreto.");
            return null;
        }
        return selecionado;
    }

    private static void listarProdutosSimplificado() {
        for (Produto p : banco.getProdutos()) {
            System.out.println("  [" + p.getId() + "] " + p.getNome() + " | Preco: R$ " + p.getPreco() + " | Estoque: " + p.getQuantidade());
        }
    }

    private static void listarProdutosDetalhado() {
        System.out.println("\n=== CATALOGO DE PRODUTOS ===");
        for (Produto p : banco.getProdutos()) {
            System.out.println("- " + p.getDescricaoCompleta() + " | Qtd: " + p.getQuantidade());
        }
        System.out.println("============================");
    }

    private static int lerOpcaoInteira() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida! Digite um numero inteiro: ");
            }
        }
    }

    private static double lerDouble() {
        while (true) {
            try {
                String input = scanner.nextLine().replace(',', '.');
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada invalida! Digite um valor real: ");
            }
        }
    }
}
