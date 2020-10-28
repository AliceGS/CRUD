# CRUD

A aplicação desenvolvida é um cadastro de atividades, onde o usuário pode cadastrar afazeres, editar ou excluir.
É uma aplicação JSF (item A) e está conectado ao MySQL (item B). Os componentes utilizados são do Primefaces (item G).

Para executar a aplicação é necessário criar a tabela 'afazeres' em seu banco de dados com os seguintes comandos:

CREATE DATABASE mydb;
USE mydb;
CREATE TABLE afazeres(
  afazeres_id INT,
  afazeres_nome VARCHAR (30)
  PRIMARY KEY (afazeres_id);
  
Após a tabela criada, modifique a linha 16 da classe DB_connection para que fique com sua porta, usuário e senha.

connection=DriverManager.getConnection("jdbc:mysql://localhost:SUA_PORTA/mydb?useTimezone=true&serverTimezone=UTC","SEU_USUARIO","SUA_SENHA");
