# Controle financeiro

Aplicação desenvolvida com o intuito de buscar valores de ações integrado com a B3, podendo ser realizado cadastro de usuários e contas financeiras vinculadas ao usuário.

## ❗️ Link Documentação

- 📄 [Spring](https://spring.io/)
- 📄 [Hibernate](https://hibernate.org/)
- 📄 [Docker](https://www.docker.com/)


## ❗️ Modelo das tabelas no banco de dados:

![image](https://github.com/user-attachments/assets/c6149cb4-836c-45c1-9bfb-7ca4c021687d)

## Desenvolvimento

Posicionado na pasta raiz do projeto configure os dados necessarios para rodar a aplicação no arquivo .env

### Exemplo
```env
DB_USER=root  
DB_PASS=root
DB_NAME=db_teste
DB_ROOT_PASS=teste
TOKEN=9hf02ks334sdgh2
```
#
Rode o comando para iniciar o ambiente de desenvolvimento
```
docker-compose up --build -d
```
