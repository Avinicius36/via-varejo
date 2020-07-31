# Developer Portal Rest Service

O Developer Portal Rest Service é um serviço para prover as funcionalidades necessárias para manipulação e administração do Portal do Desenvolvedor da Via Varejo.

## Primeiros Passos

A aplicação é escrita em Java 8 utilizando Spring Boot com bibliotecas auxiliares para prover Log, Auditoria, Segurança, Criação de Testes Unitários, Acesso a dados entre outros recursos a fim de facilitar o desenvolvimento de microsserviços. 

### Pre-requisitos

Como pré-requisito para desenvolvimento: 

	-JDK 8 (https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
	-Eclipse Release 2019 ou posterior (https://www.eclipse.org/downloads/)
	-Maven 3.6 ou posterior (https://maven.apache.org/download.cgi)
	-Docker Desktop (https://www.docker.com/products/docker-desktop)

Como pré-requisito para execução em ambiente produtivo: 
 
	-Docker


## Build

Para executar o build da aplicação basta executar o arquivo build-app.bat (Será necessário configurar a variavel de ambiente DOCKER_REPOSITORY_NAME com o nome do repositório DockerHub ou o Container Registry padrão)

Será executado o build do maven, build do docker e push da imagem gerada para o repositório. 

## Run

Para executar a aplicação utilizando o Docker basta executar o arquivo run-app.bat após o build.  

Para execução utilizando o eclipse basta teclar F11 na classe DevPortalApplication.java. 

Após a execução poderá ser verificado o Swagger UI: 

````
http://localhost:8091/devportal_rs
````

Obs.: Se executado via eclipse a porta será a 8091

## Built With

* [Docker](https://www.docker.com/) - The container
* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

O projeto se encontra versionado no GIT e esta separado em duas partes:

O Backend (Developer Portal Rest Service):
https://gitlab.engdb.com.br/via-varejo/developer-portal-service

E o front (Angular): 
[A definir]

## Authors

* **Arthur Fernandes** - *Initial work*

## License

This project is exclusive to be used under Engineering do Brasil only

## Acknowledgments

* [Testing in Spring Boot](https://www.baeldung.com/spring-boot-testing)
* [Project Lombok](https://projectlombok.org/)
* [Spring Data MongoDB] (https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.repositories)
* [Mongo DB Manual] (https://docs.mongodb.com/manual/)

