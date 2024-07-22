<h1 align="center">
Teste para vaga de Back-end Java no Magazord.com.br
</h1>

API para gerenciar receitas (CRUD) que faz parte do desafio backend em java da Magazord, uma API para uma plataforma de receitas.

## Tecnologias
- Spring Boot
- Spring Dev Tools
- Spring Doc OpenAPI
- MongoDB
- Regex

## Como Executar

### Localmente
1 - Clonar repositório git

```
 https://github.com/gVieiraX/MagazordChallenge
```

2- Instale as dependências com Maven


## API Endpoints

A API poderá ser acessada em [localhost:8080](http://localhost:8080/recipes). O Swagger poderá ser visualizado em [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Os campos id e commentId são gerados de forma automática.

Para fazer as requisições HTTP abaixo, foi utilizado o Postman:

- POST /recipes - Registar uma nova receita

Request Body
```
{
  "title": "Bolo de chocolate",
  "description": "Bolo de chocolate caseiro",
  "ingredients": [
    "ovo",
    "chocolate"
  ]
}
```
Response Body
```
{
  id": "66999fb2fb9c1d2a0e44073f",
  "title": "Bolo de chocolate",
  "description": "Bolo de chocolate caseiro",
  "ingredients": [
    "ovo",
    "chocolate"
  ]
}
```

- GET /recipes - Mostra todos as receitas

```
[
   {
      id": "66999fb2fb9c1d2a0e44073f",
      "title": "Bolo de chocolate",
      "description": "Bolo de chocolate caseiro",
      "ingredients": [
        "ovo",
        "chocolate"
  ]
    },
    {
      id": "669ae12715ab477c76036d85",
      "title": "Doce de leite ninho",
      "description": "Doce de leite ninho saboroso",
      "ingredients": [
        "Açúcar",
        "Leite ninho",
        "Leite condensado"
    }
]
```

- PUT /recipes/{id} - Atualiza as receitas

```
{
  "id":"66999fb2fb9c1d2a0e44073f",
  "title": "Bolo de chocolate ",
  "description": "Bolo de chocolate caseiro",
  "ingredients": [
    "Ovo",
    "Chocolate",
    "Açucar"
  ]
}
```

- DELETE /recites/{id} - Deleta receitas po id
```
Receita com id:66999fb2fb9c1d2a0e44073f foi deletada com sucesso!
```
- GET /recipe/{id} - Mostra uma receita por id

```
{
  "id":"66999fb2fb9c1d2a0e44073f",
  "title": "Bolo de chocolate ",
  "description": "Bolo de chocolate caseiro",
  "ingredients": [
    "Ovo",
    "Chocolate",
    "Açucar"
  ]
  
```
- GET /recipe/ingredient - Lista as receitas que possuem determinado ingrediente.
- Ex: /recipes/ingredients?ingredient=ninho

```
{
      id": "669ae12715ab477c76036d85",
      "title": "Doce de leite ninho",
      "description": "Doce de leite ninho saboroso",
      "ingredients": [
        "Açúcar",
        "Leite ninho",
        "Leite condensado"
    }
```

- GET /recipe/search - Pesquisa de receitas por palavra pelos campos de title e description
- Ex: /recipe/search?search=choco
```
  {
    "id": "5bc932949531144888cc2bd1",
    "title": "Torta de chocolate",
    "description": "Torta de chocolate com morango",
    "ingredients": [
      "chocolate",
      "morango"
    ]
  }

```
- POST /recipe/{id}/comment - Adiciona um comentário em uma receita.

Request body
```
{
  "comment": "Muito gostoso!"
}
```
Response body
```
{
  "id": "669eeadf267bc94a41962ac1",
  "comment": "Muito gostoso!"
}
```
- PUT /recipe/{id}/comment/{commentId} - Atualiza  um comentário de uma receita

```
  "comment": "Muito saboroso!"

```

- DELETE /recipe/{id}/comment/{commentId} - Remove um comentário de uma receita.


```
Comentário apagado com sucesso!
```