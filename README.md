# Sistema de Gestão Acadêmica (Presença & Notas)
> Projeto Desktop desenvolvido nativamente no IntelliJ IDEA com JavaFX e MySQL.

Este sistema foi criado para facilitar o controle de frequência e o desempenho escolar dos alunos, integrando uma interface gráfica intuitiva com persistência de dados em tempo real.

---

## Tecnologias & Ambiente

*   **Linguagem:** Java 21 (LTS)
*   **Interface:** JavaFX
*   **Banco de Dados:** MySQL 8.0
*   **IDE:** IntelliJ IDEA (Projeto nativo `.iml`)

---

## Configuração do Banco de Dados

Siga os passos abaixo para preparar o ambiente MySQL:

1.  **Criação do Schema:**
    No terminal do MySQL ou Workbench, execute (altere para o nome que preferir do banco):
    ```sql
    CREATE DATABASE db_sistema_alunos;
    ```

2.  **Configuração de Acesso:**
    Localize a sua classe de conexão Java (src/bancodedados/consumers/ConsumerAPIJBDC) e atualize as variáveis com suas credenciais:
    *   **URL:** `jdbc:mysql://localhost:3306/db_sistema_alunos` 
    *   **Usuário:** `root` (seu usuário)
    *   **Senha:** `sua_senha_aqui`

---

## Como Executar no IntelliJ

Como este é um projeto estruturado via arquivo `.iml`, siga estes passos:

1.  **Abrir o Projeto:**
    *   No IntelliJ, vá em `File > Open` e selecione a pasta raiz do projeto.
2.  **Configurar Dependências (Libraries):**
    *   Caso as bibliotecas (JavaFX e MySQL Connector) não sejam carregadas automaticamente:
    *   Vá em `File > Project Structure > Libraries`.
    *   Certifique-se de que os arquivos JAR do **JavaFX SDK** e do **MySQL Connector** estão adicionados.
3.  **VM Options (JavaFX):**
    *   Se estiver usando JavaFX externo, lembre-se de adicionar as `--module-path` nas configurações de execução (*Run Configurations*).
4.  **Executar:**
    *   Localize a classe com o método `main` (src/gui/main/mainFx), clique com o botão direito e selecione **Run**.

---

## Funcionalidades Principais

*  **Gestão de Alunos:** Cadastro, edição e listagem.
*  **Diário de Classe:** Controle de presença por data.
*  **Avaliações:** Lançamento de notas com cálculo de média automático.
*  **Persistência:** Armazenamento seguro de todas as informações no MySQL.

---

## Organização das Pastas

*   `.idea/` & `*.iml`: Arquivos de configuração nativos do IntelliJ.
*   `src/`: Contém todo o código fonte (Classes Java).
*   `resources/`: Contém todos os arquivos fxml e imagens.
*   `bancodedados/`: Tudo relacionado a banco de dados (interfaces, entity e consumers).
*   `gui/`: Tudo relacionado a interface gráfica (alerts, controllers, fxml loaders, interfacescreenprincipal (filtros e verificações da interface), main (inciador está localizado aqui), validations(validations de campo da interface).
*   `modelos/`: modelo de dados para transitar dados internamente durante a execução (interfaces, entity e consumers).

---
Desenvolvido por 
**Gustavo Oliveira Santos |
Gutemberg Pereira Nogueira Neto |
Arthur Oliveira de Matos |
Tarsis Davi Silva Rodrigues |
Breno Alcindo Santana |** 
