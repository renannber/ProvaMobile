
# Cadastro de Produtos - Android

## Descrição

Aplicativo Android desenvolvido no Android Studio utilizando Java e Room Database, com o objetivo de realizar o cadastro e a listagem de produtos.

O sistema permite inserir informações como nome, código, preço e quantidade, armazenando os dados localmente no dispositivo.

---

## Funcionalidades

* Cadastro de produtos
* Armazenamento local com Room Database
* Listagem de produtos cadastrados
* Navegação entre telas (Cadastro e Relatório)
* Interface simples e funcional

---

## Tecnologias utilizadas

* Java
* Android Studio
* Room Database
* RecyclerView

---

## Estrutura do Aplicativo

### Tela de Cadastro

* Inserção de:

  * Nome
  * Código
  * Preço
  * Quantidade
* Botão para salvar produto
* Botão para visualizar relatório

### Tela de Relatório

* Exibição da lista de produtos
* Mostra:

  * Nome
  * Código
  * Preço
* Botão para voltar à tela de cadastro

---

## Banco de Dados

O aplicativo utiliza o Room Database com:

* Entity: Produto
* DAO: ProdutoDao
* Database: ProdutoDataBase

---

## Como executar o projeto

1. Clone o repositório:

```bash
git clone https://github.com/renannber/ProvaMobile.git
```

2. Abra o projeto no Android Studio

3. Execute em um emulador ou dispositivo físico

---

## Observações

* Projeto desenvolvido para fins acadêmicos
* Utiliza allowMainThreadQueries() para simplificação
* Pode ser melhorado com validações e interface mais avançada

---

## Autor

Renan Bernardes

