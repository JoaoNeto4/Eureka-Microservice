## Eureka-Microservice

## Descrição
- Estudo de API de Microserviços Restfull usando Spring Cloud Eureka


## Prerequisitos (Testado apenas em Sistema Linux)
- ### PASSO 1
```
$ sudo apt-get install git
$ sudo apt-get install docker
```
- Certifique-se de que tenha instalado o Java 11 corretamente
```
$ sudo apt-get install openjdk-11-jdk
$ java --version
```
- Se a saída do comando anterior for diferente do Java 11 você pode alterá-lo com:
```
$ sudo update-alternatives --config java
```
- Para evitar problemas certifique-se de que a variável de ambiente esteja apontando para o JDK-11
```
$ export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/
```
- ### PASSO 2
- Clone este repositório a partir da Branch Main
```
$ gh repo clone JoaoNeto4/Eureka-Microservice
```
- Crie uma rede para o Docker Container
```
$ docker network create  cursoms-network
```
- Execute um Container do RabbitMQ, em seguida acesse-o via Brawser e cria a Queue "emissao-cartoes" (login=guest password=guest)
```
$ docker run -it --rm --name cursoms-rabbitmq -p 5672:5672 -p 15672:15672 --network cursoms-network rabbitmq:3.11-management
```
- Execute um Container do Keycloack, emseguida acesse-o via Browse e crie um Realm "mscourserealm" depois importe o  arquivo "Keycloack_mscredito.json", em seguida também Set o campo "Frontend URL" com "http://cursoms-keycloack:8080"
```
docker run -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin --network cursoms-network --name cursoms-keycloack quay.io/keycloak/keycloak:20.0.1 start-dev
```
- ### PASSO 3
- Navegue até o microserviço "eurekaserver", crie a build e a execute
```
$ cd Eureka-Microservice/eurekaserver
$ docker build --tag cursoms-eureka .
$ docker run --name cursoms-eureka -p 8761:8761 --network cursoms-network cursoms-eureka
```
- ### PASSO 4
- Navegue até o microserviço "mscartoes", crie a build e a execute
```
$ cd Eureka-Microservice/mscartoes
$ docker build --tag cursoms-cartoes .
$ docker run --name cursoms-cartoes -e RABBITMQ_SERVER=cursoms-rabbitmq -e EUREKA_SERVER=cursoms-eureka -P --network cursoms-network cursoms-cartoes
```
- ### PASSO 5
- Navegue até o microserviço "msclientes", crie a build e a execute
```
$ cd Eureka-Microservice/msclientes
$ docker build --tag cursoms-clientes .
$ docker run --name cursoms-clientes --network cursoms-network -e EUREKA_SERVER=cursoms-eureka cursoms-clientes
```
- ### PASSO 6
- Navegue até o microserviço "msavaliadorcredito", crie a build e a execute
```
$ cd Eureka-Microservice/msavaliadorcredito
$ docker build --tag cursoms-avaliadorcredito .
$ docker run --name cursoms-avaliadorcredito --network cursoms-network -e RABBITMQ_SERVER=cursoms-rabbitmq -e EUREKA_SERVER=cursoms-eureka cursoms-avaliadorcredito
```
- ### PASSO 7
- Navegue até o microserviço "mscloudgateway", crie a build e a execute
```
$ cd Eureka-Microservice/mscloudgateway
$ docker build --tag cursoms-gateway .
$ docker run --name cursoms-gateway -p 8080:8080 -e EUREKA_SERVER=cursoms-eureka -e KEYCLOACK_SERVER=cursoms-keycloack -e KEYCLOACK_PORT=8080 --network=cursoms-network cursoms-gateway
```
- ### PASSO 8
- Caso queira, você poderá importar o arquivo "Eureka-MicroServices_TESTES.json" no Postman para testá-lo ou agir por conta
- ### OBSERVAÇÔES
- Se você for usuário com conhecimento sobre o assunto, você também pode executar os serviços individualmente pela sua IDE preferida, e com isso está API também conta com o Swagger que esta acessível apenas para execução local sem Container, mas para que tudo ocorra bem neste caso precisará remover o mapeamento do Keycloack "Frontend URL" e setá-lo como nulo.

# #JoaoNeto4
