# API Management Person

Esta API tem como finalidade gerenciar cadastro de pessoas. E uma API simples apenas como forma de estudo e aprimoramento de tecnologias.
O objetivo desta API e cadastrar, consultar, lista, atualizar e deletar cadastros de pessoas.  

## Tecnologias

 - [`Spring boot`](https://spring.io) - Framework de desenvolvimento Java.
 - [`Spring Hateoas`](https://spring.io/projects/spring-hateoas) - Hipermídia como o motor do estado do aplicativo 
 - [`Lombok`](https://projectlombok.org/) - Biblioteca java que auxilia como ferramentas de construção.
 - [`JPA/Hibernate`](https://hibernate.org/orm/) - Framework para persistência de dados / ORM.
 - [`H2 Database Engine`](https://mvnrepository.com/artifact/com.h2database/h2) - Banco de dados executado em memória, utilizado nos testes.
 - [`Swagger`](https://swagger.io/) - Framework para projetar, construir, documentar e usar serviços da Web RESTful. 
 - [`jUnit5`](https://junit.org/junit5/docs/current/user-guide/) - Testes de unitários
 - [`Mockk`](https://mockk.io) - Ferramenta para mocks na realização dos testes
 - [`Jacoco`](https://www.jacoco.org) - Ferramenta para cobertura de tests

## Detalhes importantes
É necessário ter instalado na sua máquina os seguintes programas:

* A aplicação foi desenvolvida no java-jdk13. [`Java`](https://www.oracle.com/br/java/) 
* Motor de build da aplicação e atraves do Mavem. [`Maven`](https://maven.apache.org)
  
## Arquitetura

<img src="documentation/images/CleanArchitecture.jpg" width="400" >

A estrutura do projeto foi desenvolvido pensando no conceito do 
[`Clean Architecture`](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html), 
que divide a aplicação em módulos:

* **Configuration** - Configurações do Framework, injeção de dependências, define
as implementações e realiza a união do projeto como um todo

* **Core** - Regra de negócio, essa módulo é livre de qualquer tipo de framework
para deixar a regra independente de tecnologia e de fácil manutenção, nessa camada
temos duas vertentes:
    * **entity** - Entidades, objetos que representam o negócio
    * **use case** - Casos de uso do projeto, como um `Service`, implementa 
    funcionalidades e regras especificas de cada funcionalidade.

* **Data Provider** - Camada responsável em fornecer os dados para o `core`
implementando suas interfaces.

* **Entry Points** - Responsável em fornecer os end-points, essa
camada trata os dados que o usuário envia processa utilizando os `use cases`
do `core` e trata seu retorno.

A idéia de utilizar o `Clean Architecture` é para tornar nossos sistemas
manutenível, testável e flexível a alterações drásticas, atravéz dessa estrutura
também temos um ganho ao lidarmos com várias pessoas alterando o mesmo código; 

