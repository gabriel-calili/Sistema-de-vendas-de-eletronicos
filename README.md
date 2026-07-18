# Sistema de Vendas para Eletrônicos

Este é o projeto de curso desenvolvido para a disciplina de **Programação de Computadores II (CSI102)** do Instituto de Ciências Exatas e Aplicadas da **Universidade Federal de Ouro Preto (UFOP)**.

O sistema é uma aplicação desenvolvida em **Java** voltada para o gerenciamento de fluxo comercial e controle de estoque de uma loja especializada em eletrônicos. Ele roda diretamente no terminal (CLI) e foi construído sem banco de dados físico (mantendo estado em memória com coleções dinâmicas genéricas) e sem interface gráfica, priorizando a aplicação rigorosa dos conceitos de Programação Orientada a Objetos (POO).

---

## 🛠️ Conceitos de POO Aplicados

O sistema implementa integralmente os seguintes tópicos da ementa da disciplina:

1. **Modularização e Encapsulamento**: Divisão clara do código em pacotes lógicos (`modelo`, `servico`, `excecoes`, `padroes`) e atributos privados acessados exclusivamente através de getters/setters validados.
2. **Reuso de Código**: Implementado através de relacionamentos de herança de classes abstratas (`Usuario` e `Produto`) e de composição/agregação (Classes `Compra` e `Venda` agregando produtos).
3. **Polimorfismo de Subtipagem**: 
   - A classe abstrata `Produto` declara o método abstrato `getDescricaoCompleta()`, implementado dinamicamente pelas subclasses `Notebook`, `Smartphone` e `Mouse`.
   - Iteração sobre listas de tipos genéricos (`Produto` e `Usuario`) chamando métodos dinâmicos baseados nas instâncias reais em tempo de execução.
4. **Tratamento de Exceções**: Utilização de exceções customizadas do domínio (`EstoqueInsuficienteException`, `AcessoNegadoException` e `ValorInvalidoException`) para interrupção de fluxos inválidos de negócio e tratamento centralizado com blocos `try-catch`.
5. **Programação por Contrato**: Verificação ativa de pré-condições em construtores e setters (valores negativos, strings vazias ou nulas) disparando exceções de negócio caso as condições sejam violadas.
6. **Polimorfismo Paramétrico (Generics)**: Uso amplo de listas dinâmicas tipadas (`List<Produto>`, `List<Usuario>` e `List<Transacao>`) no lugar de arrays estáticos para melhor flexibilidade e segurança de tipos.
7. **Padrões de Projeto (Design Patterns)**:
   - **Singleton**: Aplicado em `GerenciadorEstoque` para fornecer um único ponto global de acesso ao estado do estoque e usuários.
   - **Factory Method**: Aplicado em `ProdutoFactory` e `UsuarioFactory` para isolar e simplificar a instanciação dos tipos concretos das hierarquias.
   - **Adapter**: Aplicado via `PaymentAdapter` para adaptar uma classe legada de pagamento (`LegacyPaymentProcessor`) para a nova interface de serviço (`PagamentoService`) exigida pelo sistema principal.

---

## 📂 Estrutura de Diretórios do Projeto

```text
projeto-poo/
├── src/
│   └── br/
│       └── ufop/
│           └── projeto/
│               ├── excecoes/
│               │   ├── AcessoNegadoException.java
│               │   ├── EstoqueInsuficienteException.java
│               │   └── ValorInvalidoException.java
│               ├── modelo/
│               │   ├── Cliente.java
│               │   ├── Compra.java
│               │   ├── Gerente.java
│               │   ├── Mouse.java
│               │   ├── NivelAcesso.java
│               │   ├── Notebook.java
│               │   ├── Produto.java
│               │   ├── Smartphone.java
│               │   ├── Transacao.java
│               │   ├── Usuario.java
│               │   └── Venda.java
│               ├── padroes/
│               │   ├── LegacyPaymentProcessor.java
│               │   ├── PagamentoService.java
│               │   ├── PaymentAdapter.java
│               │   ├── ProdutoFactory.java
│               │   └── UsuarioFactory.java
│               ├── servico/
│               │   └── GerenciadorEstoque.java
│               └── Main.java
├── bin/                       # Diretório gerado após a compilação
└── README.md
```

---

## 🚀 Como Executar o Projeto pelo Terminal

### Pré-requisitos
- Ter o **JDK (Java Development Kit)** versão 8 ou superior instalado e configurado nas variáveis de ambiente do sistema.

### Passo 1: Navegar até a pasta raiz
Abra o seu terminal (Prompt de Comando ou PowerShell no Windows, Terminal no Linux/macOS) e navegue até a pasta do projeto:
```bash
cd "/caminho/para/Projeto prog 2"
```

### Passo 2: Compilar o código-fonte
Compile todas as classes Java para a pasta de binários `bin`:
```bash
# No Windows (PowerShell) ou Linux/macOS:
javac -d bin src/br/ufop/projeto/excecoes/*.java src/br/ufop/projeto/modelo/*.java src/br/ufop/projeto/padroes/*.java src/br/ufop/projeto/servico/*.java src/br/ufop/projeto/*.java
```

### Passo 3: Executar a Aplicação
Inicie a aplicação executando a classe principal `Main`:
```bash
java -cp bin br.ufop.projeto.Main
```

---

## 💻 Como Importar e Executar em uma IDE

Se preferir utilizar um ambiente de desenvolvimento integrado (IDE), siga as instruções para o seu editor de preferência:

### 1. IntelliJ IDEA
1. Abra o IntelliJ IDEA.
2. Selecione **File > Open** e selecione a pasta raiz `Projeto prog 2`.
3. A IDE detectará a pasta de fontes `src` automaticamente. Caso não detecte, clique com o botão direito na pasta `src`, selecione **Mark Directory as > Sources Root**.
4. Configure o JDK nas configurações de projeto (**File > Project Structure > Project > SDK**) para a versão 8 ou superior.
5. Localize o arquivo `src/br/ufop/projeto/Main.java`, clique com o botão direito sobre ele e selecione **Run 'Main.main()'**.

### 2. Eclipse IDE
1. Abra o Eclipse.
2. Vá em **File > Import > General > Existing Projects into Workspace** (ou crie um novo **Java Project** e importe a pasta `src` para ele).
3. Selecione o diretório raiz `Projeto prog 2`.
4. Garanta que o JRE associado ao projeto é compatível.
5. Clique com o botão direito em `Main.java` na aba *Package Explorer* e selecione **Run As > Java Application**.

### 3. VS Code (Visual Studio Code)
1. Instale a extensão **Extension Pack for Java** fornecida pela Microsoft.
2. Abra a pasta `Projeto prog 2` no VS Code.
3. A IDE inicializará as ferramentas Java automaticamente.
4. Abra o arquivo `src/br/ufop/projeto/Main.java`.
5. Clique no botão **Run** ou **Debug** que aparece logo acima do método `public static void main`.
