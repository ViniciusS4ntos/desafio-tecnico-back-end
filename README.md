# CRUD Desafio API - Gerenciamento de Usuários e Cartões

API RESTful desenvolvida em Java e Spring Boot com autenticação JWT, focada no gerenciamento de usuários e cartões (`user-controller`, `cartao-controller` e `authentication-controller`).

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Security / JWT
- Banco de Dados Relacional (H2 / PostgreSQL)
- Docker & Docker Compose

---

## Pré-requisitos

Certifique-se de ter instalado em sua máquina:

- Java JDK 21+ ([Adoptium](https://adoptium.net/))
- Maven ([Apache Maven](https://maven.apache.org/))
- Docker e Docker Compose ([Documentação oficial](https://docs.docker.com/compose/))

![Pré-requisitos](WhatsApp%20Image%202026-10-15%20at%2000.40.13.jpeg)

---

## Seleção de Banco de Dados (H2 vs. PostgreSQL)

Você pode escolher se a aplicação utilizará o banco em memória **H2** ou o banco **PostgreSQL**.

### Execução Local (sem Docker)

Defina o perfil ativo no arquivo `src/main/resources/application.properties`:

```properties
spring.profiles.active=h2
```

Ou, para utilizar PostgreSQL:

```properties
spring.profiles.active=prod
```

### Execução via Docker

No arquivo `docker-compose.yml`, configure a variável de ambiente correspondente ao perfil desejado:

```yaml
environment:
  - SPRING_PROFILES_ACTIVE=prod
```

Para utilizar o perfil H2, altere para:

```yaml
environment:
  - SPRING_PROFILES_ACTIVE=h2
```

**Importante:** ao utilizar PostgreSQL via Docker, verifique se o serviço do banco de dados, as credenciais, a porta e as configurações de conexão estão definidos corretamente no `docker-compose.yml` e nos arquivos de configuração da aplicação.



## Como Rodar Localmente (Sem Docker)

### 1. Clonar e Acessar o Repositório

```bash
git clone https://github.com/ViniciusS4ntos/desafio-tecnico-back-end.git
cd desafio-tecnico-back-end
```

### 2. Executar a Aplicação

Configure o perfil do banco de dados desejado na seção **Seleção de Banco de Dados (H2 vs. PostgreSQL)** e execute:

```bash
mvn spring-boot:run
```

---

## Como Rodar com Docker

Você também pode executar a aplicação utilizando Docker e Docker Compose, sem precisar instalar o Java e o Maven para executar o projeto diretamente na sua máquina.

### 1. Clonar e Acessar o Repositório

```bash
git clone https://github.com/ViniciusS4ntos/desafio-tecnico-back-end.git
cd desafio-tecnico-back-end
```

### 2. Iniciar os Containers

Certifique-se de que o Docker Desktop esteja aberto e em execução. Na pasta raiz do projeto, onde está localizado o arquivo `docker-compose.yml`, execute:

```bash
docker compose up --build
```

O comando irá construir a imagem da aplicação, quando necessário, e iniciar os serviços definidos no Docker Compose.

Para executar os containers em segundo plano, utilize:

```bash
docker compose up --build -d
```

### 3. Verificar os Logs

Para acompanhar a inicialização da aplicação e verificar se ocorreu algum erro, execute:

```bash
docker compose logs -f
```

Para visualizar os logs somente da aplicação, caso o serviço esteja definido com o nome `app` no arquivo `docker-compose.yml`, utilize:

```bash
docker compose logs -f app
```

### 4. Acessar a Aplicação

Após a inicialização dos containers, acesse a documentação interativa do Swagger:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### 5. Parar os Containers

Para parar e remover os containers criados pelo Compose, execute:

```bash
docker compose down
```

Esse comando não remove automaticamente os volumes nomeados do Docker, portanto os dados persistidos nesses volumes podem continuar armazenados.

Para remover também os volumes do projeto, utilize:

```bash
docker compose down -v
```

**Atenção:** o comando `docker compose down -v` pode apagar os dados armazenados nos volumes associados ao projeto.

---

## Swagger e Autenticação JWT

Após iniciar a aplicação, seja localmente ou via Docker, você pode utilizar o Swagger para testar os endpoints de forma visual e prática.

**Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Como autenticar no Swagger

1. Acesse o endpoint `/auth/login` e realize o login.
2. Copie o token JWT retornado pela API.
3. Clique no botão **Authorize** no Swagger.
4. Informe o token com o prefixo `Bearer `, caso o esquema de segurança configurado no Swagger exija esse formato.

Exemplo:

```text
Bearer seu_token_aqui
```

---

