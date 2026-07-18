# Sistema de Vendas para Eletrônicos

Este é o projeto de curso desenvolvido para a disciplina de **Programação de Computadores II (CSI102)** do Instituto de Ciências Exatas e Aplicadas da **Universidade Federal de Ouro Preto (UFOP)**.

**Aluno**: Gabriel Biciate

O sistema é uma aplicação desenvolvida em **Java** voltada para o gerenciamento de fluxo comercial e controle de estoque de uma loja de eletrônicos. A aplicação roda diretamente no terminal (CLI) e simula uma persistência de dados em memória por meio de coleções dinâmicas genéricas, sem interface gráfica ou banco de dados físico, priorizando a aplicação prática dos conceitos de Programação Orientada a Objetos (POO).

---

## 🛠️ Conceitos de POO Aplicados

O sistema implementa de forma rigorosa os seguintes tópicos da disciplina de POO:

1. **Modularização e Encapsulamento**: Divisão lógica do código em pacotes bem definidos (`modelo`, `servico`, `excecoes`). Os atributos das classes são privados (`private`) e acessados/modificados por meio de métodos públicos getter/setter com validações apropriadas.
2. **Reuso de Código por Herança**: 
   - A classe abstrata `Usuario` é herdada por `Gerente`, `Vendedor` e `Comprador`.
   - A classe abstrata `Produto` é herdada por `Notebook`, `Smartphone` e `Mouse`.
3. **Polimorfismo de Subtipagem (Dinâmico)**:
   - A classe abstrata `Produto` declara o método abstrato `getDescricaoCompleta()`, implementado de forma específica pelas subclasses `Notebook`, `Smartphone` e `Mouse`.
   - Na listagem de produtos, o sistema itera sobre uma lista genérica do tipo `Produto` e invoca `getDescricaoCompleta()` em tempo de execução, delegando o comportamento correto para cada subclasse.
4. **Sobrecarga de Métodos (Polimorfismo Estático)**:
   - Implementado na classe `Gerente`, que possui o método sobrecarregado `definirQntMinima`:
     - `definirQntMinima(Produto[] produtos)`: atualiza o estoque mínimo padrão de uma lista de produtos.
     - `definirQntMinima(Produto produto, int qntMinima)`: define um estoque mínimo específico para um único produto.
5. **Tratamento de Exceções**:
   - Criação e uso da exceção personalizada `EstoqueInsuficienteException` (exceção checada) para sinalizar falhas em transações de venda cujo estoque solicitado do produto não é suficiente.
   - Uso de exceções padrão do Java (`IllegalArgumentException`) para validação de contratos no construtor de classes, como na classe `Compra`.
6. **Polimorfismo Paramétrico (Generics & Collections)**:
   - Uso intensivo de coleções dinâmicas e tipadas (`List<Produto>`, `List<Usuario>`, `List<Venda>` e `List<Compra>`) em vez de vetores estáticos, garantindo segurança de tipos e flexibilidade operacional.
7. **Padrões de Projeto (Design Patterns)**:
   - **Singleton**: Aplicado na classe `BancoDados` na camada de serviços. Ela garante que haja uma única instância contendo as listas centrais de produtos, usuários e transações (vendas e compras) em memória durante toda a execução da aplicação.

---

## 📂 Estrutura de Diretórios do Projeto

```text
projeto-poo/
├── src/
│   └── br/
│       └── ufop/
│           └── projeto/
│               ├── excecoes/
│               │   └── EstoqueInsuficienteException.java
│               ├── modelo/
│               │   ├── Compra.java
│               │   ├── Comprador.java
│               │   ├── Gerente.java
│               │   ├── Mouse.java
│               │   ├── Notebook.java
│               │   ├── Produto.java
│               │   ├── Smartphone.java
│               │   ├── Usuario.java
│               │   ├── Venda.java
│               │   └── Vendedor.java
│               ├── servico/
│               │   └── BancoDados.java
│               └── Main.java
└── README.md
```

---

## 🚀 Como Executar o Projeto pelo Terminal

### Pré-requisitos
- Ter o **JDK (Java Development Kit)** versão 8 ou superior instalado e configurado nas variáveis de ambiente do sistema (`java` e `javac` acessíveis pelo console).

### Passo 1: Navegar até a pasta raiz do projeto
Abra o seu terminal (Prompt de Comando/PowerShell no Windows, Terminal no Linux/macOS) e navegue até a pasta do projeto:
```bash
cd "/caminho/para/Projeto prog 2"
```

### Passo 2: Compilar o código-fonte
Compile todas as classes Java gerando os binários na pasta `bin`:
```bash
# No Windows (PowerShell) ou Linux/macOS:
javac -d bin src/br/ufop/projeto/excecoes/*.java src/br/ufop/projeto/modelo/*.java src/br/ufop/projeto/servico/*.java src/br/ufop/projeto/Main.java
```

### Passo 3: Executar a Aplicação
Inicie a aplicação executando a classe principal `Main`:
```bash
java -cp bin br.ufop.projeto.Main
```

---

## 💻 Como Importar e Executar em uma IDE

### 1. IntelliJ IDEA
1. Abra o IntelliJ IDEA.
2. Vá em **File > Open** e selecione a pasta raiz `Projeto prog 2`.
3. A IDE costuma detectar a pasta de fontes `src` automaticamente. Caso contrário, clique com o botão direito na pasta `src`, selecione **Mark Directory as > Sources Root**.
4. Configure o JDK do projeto em **File > Project Structure > Project > SDK** (versão 8 ou superior).
5. Abra o arquivo `src/br/ufop/projeto/Main.java`, clique com o botão direito e selecione **Run 'Main.main()'**.

### 2. Eclipse IDE
1. Abra o Eclipse.
2. Vá em **File > Import > General > Existing Projects into Workspace** e selecione o diretório do projeto.
3. Certifique-se de que a pasta `src` está configurada como pasta de build path.
4. Clique com o botão direito em `Main.java` no Package Explorer e escolha **Run As > Java Application**.

### 3. VS Code (Visual Studio Code)
1. Instale o pacote de extensões **Extension Pack for Java** da Microsoft.
2. Abra a pasta `Projeto prog 2` no VS Code.
3. Abra o arquivo `src/br/ufop/projeto/Main.java`.
4. Clique na opção **Run** ou **Debug** que aparece acima do método `main`.
