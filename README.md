# TODO List

Projeto em Java de uma API de lista de tarefas

### Funcionalidades

- [x] Cadastrar Usuário
- [x] Cadastrar Tarefa
- [x] Atualizar Tarefa
- [x] Listar Tarefas do usuário logado

### Tecnologias Utilizadas

- Java 17
- Spring Boot
- H2 Database
- API DOG
- Lombok
- Maven


### Endpoint Base

- URL Base da API: `https://todolist-2r4f.onrender.com`

### Autenticação

Esta api não requer autenticação, porém saiba que a mesma está em servidor de teste, portanto demoras podem ocorrer nas requisições.

### Recursos Disponíveis

| Método |   Endpoint   | Descrição |
| ------ | -----------  | --------- |
| POST   | `/users/`    | Cria um novo usuário |
| POST   | `/tasks/`    | Cria uma nova tarefa |
| GET    | `/tasks/`    | Lista todas as tarefas do usuário |
| PUT    | `/tasks/:id` | Atualiza uma tarefa |

Lembrando que os endpoints `tasks` precisam ser enviados junto com o header `Authorization: Basic` com o usuário e senha criados no endpoint `/users/`

### Sucessos

A API retorna alguns códigos de sucesso, consulte abaixo para entender cada um deles.

| Status| Descrição  |
| ----- | ---------- |
|  200  | Executado  |
|  201  | Criado     |
|  204  | Atualizado |

### Erros

A API pode retornar alguns códigos de erro HTTP em caso de solicitações inválidas. Consulte a tabela a seguir para obter detalhes sobre os códigos de erro possíveis.

| Status| Descrição                |
| ----- | ------------------------ |
|  400  | Solicitação inválida     |
|  401  | Usuário não autorizado   |
|  404  | Tarefa não encontrada    |
|  500  | Erro interno do servidor |


### Dev

- [Hendrew Neres](https://github.com/drewneres)
