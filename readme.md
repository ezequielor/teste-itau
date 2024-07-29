# Projeto de Validação de JWT

Este projeto é uma aplicação Spring Boot que valida tokens JWT (JSON Web Tokens) de acordo com regras predefinidas. O sistema analisa os tokens e verifica se eles atendem a requisitos específicos, como a presença e a validade de certos campos. 

## Funcionalidade

A aplicação fornece um endpoint REST que recebe um token JWT e valida-o. As principais regras de validação incluem:

- O token deve conter exatamente três reivindicações (claims).
- O campo "Name" deve ser uma string com até 256 caracteres e não pode conter dígitos.
- O campo "Role" deve ser um dos seguintes valores: "Admin", "Member", "External".
- O campo "Seed" deve ser um número primo.

## Estrutura do Projeto

### 1. `JwtController`

Responsável pelo endpoint REST que valida o token JWT. O controlador possui um endpoint GET `/validate` que recebe um token JWT como parâmetro de consulta e retorna se o token é válido.

- **Endpoint:** `GET /validate`
- **Parâmetros:** `token` (string)
- **Respostas:**
  - `200 OK`: Trazendo a validação do JWT em boolean.
  

### 2. `JwtValidationResponse`

Classe de resposta que encapsula o resultado da validação do token. Contém um único campo booleano `valid` que indica se o token é válido ou não.

### 3. `JwtServiceImpl`

Implementação do serviço de validação de JWT. Utiliza a classe `ValidateClaims` para validar os tokens com base nas regras definidas.

### 4. `ValidateClaims`

Classe utilitária que realiza a validação real das reivindicações no token JWT. Decodifica o token e verifica se as reivindicações estão corretas e atendem aos critérios definidos.

## Configuração

1. **Clone o repositório:**
   ```sh
   git clone https://github.com/ezequielor/teste-itau.git

2. **Navegue até o diretorio:**
    cd teste-itau

3. **Rodando a aplicação:**
   - o projeto não foi configurado com properties por ambientes, então executando o RUN do Springboot o mesmo já inicializa sem a necessidade de ajuste de perfil
   - o mesmo inicializa na porta padrão 8080, tem documentação basica do swagger para ser chamado via browser ou serviços como postman
   - o mesmo contem configuração do github actions para fazer deploy em uma EC2 na AWS
   - existe um repositorio que pode ser clonado para montar uma EC2 na AWS utilizando Scripts Terraform e Github Actions: https://github.com/ezequielor/infra-itau.git
   - o mesmo foi exposto na AWS no seguinte endereço: http://3.224.63.121:8080/swagger-ui/index.html
   - link para collections via AWS: https://www.postman.com/zakkbr/workspace/teste-itau/collection/37307811-7f262eb8-bc5e-4da5-af37-d38fc7c5dd28




