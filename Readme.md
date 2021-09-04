# Cadastro de aluno em Hogwarts

## Adicionando um aluno em Hogwarts

Crie uma aplicação SpringBoot com banco de dados sqlite3 ou h2 com as seguintes funcionalidades.

Criar um aluno somente com: 
* id
* nome
* id_house

Fluxo de inserção (Post)
* O usuário deverá preencher somente o nome do aluno.
* O sistema ao receber a requisição, consulta o endpoint: (GET) https://api-harrypotter.herokuapp.com/sortinghat

Fluxo de consulta e listagem
* Ao consultar um aluno ou listagem dos alunos com id da casa do aluno deve-se ser consultado o endpoint: (GET) https://api-harrypotter.herokuapp.com/house/:id, onde o :id refere-se ao identificador informado pelo chapéu seletor

Obs: Para ver todas as casas de Hogwarts https://api-harrypotter.herokuapp.com/houses