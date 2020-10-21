# Desafio Guiabolso

#### Regras de negócio

Requisição:

- a `requisição` será um __GET__
- a `requisição` deve respeitar o formato `[GET] /<id>/transacoes/<ano>/<mes>`
- os parâmetros `<id>`, `<ano>` e `<mês>` são o `conjunto de dados`

Transação:

- dado um `conjunto de dados`, será retornada uma lista de transações
- cada _transação_ segue o [contrato de transação](#Contrato)
- a lista de transações tem `no mínimo uma transação por mês`
- a lista de transações tem `quantidade total de transações variável entre os meses`
- dado dois `conjuntos de dados` iguais, as respostas são as mesmas
- isso significa que, para um mesmo id, mês e ano, é retornada a mesma lista

Id:

- o id de usuário deve ser um `número inteiro` de 1.000 a 100.000.000

Ano:
- o ano deve ser um `número inteiro` maior que 1900

Mês:
- o mês deve ser um `número inteiro` de 1 a 12

#### Retorno

Descrição:

- cada transação possui uma `descrição aleatória legível`
- cada descrição tem no mínimo `10 caracteres`
- cada descrição não supera `60 caracteres`

Valor:

- cada transação possui um `valor aleatório` representado por um `número inteiro`
- o valor da transação está entre `-9.999.999 e 9.999.999`, inclusive

Data:

- cada transação possui uma `data aleatória` 
- o campo data é um `timestamp` 
- o campo data tem o tipo `long`
- a data aleatória está `dentro do range de ano e mês` dados

Duplicidade:

- caso o conjunto de transações tenha duas ou mais transações com a `mesma descrição, data e valor`, todas, menos uma, `possui o campo duplicated com valor true`
- ao iterar 12 meses em um mesmo ano, `no mínimo 3 meses possuem uma transação duplicada`

Tratamento de erro de input:

Exemplo de retorno de erro de input:
```
[GET] /999/transacoes/1899/13
{
    "codigo": 406,
    "mensagens": {
        "ano": "Deve ser um valor inteiro maior que 1900",
        "mes": "Deve ser um valor inteiro entre 1 e 12",
        "id": "Deve ser um valor inteiro entre 1000 e 100000000"
    },
    "descricao_codigo": "Not Acceptable"
}
```

#### Contrato

Transação

```
[GET] /<id>/transacoes/<ano>/<mes>

Content-type: application/json

[
  {
     "descricao": "string(10, 120)"
     "data": "long(timestamp)"
     "valor": "integer(-9.999.999, 9.999.999)"
     "duplicated": "boolean"
  }  
]
```

#### Execução da aplicação:
Pré requisitos:
- Ter o docker instalado.

No terminal, dentro da pasta raiz do projeto:
```
 -> sudo docker build -t transacoes/transacoes-app .
 -> sudo docker run -p 8080:8080 transacoes/transacoes-app
```
