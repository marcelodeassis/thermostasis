# Thermostasis API - Desafio Técnico

### Instruções

Entre no seu workspace e ...
1. git clone https://github.com/marcelodeassis/thermostasis.git
1. cd thermostasis
1. mvn package -DskipTests
1. docker build -t thermostasis-api -f docker\Dockerfile .
     >Da raiz do projeto, "buildamos" a imagem definida no Dockerfile, que usa o Jar do projeto. Primeiro argumento é o nome da imagem, e o segundo é o path do Dockerfile, e o ponto no final pra indicar que ele será usado no diretório corrente
1.  docker-compose -f docker/docker-compose.yml up
    > Se tudo der certo, o docker compose vai iniciar a imagem do Themostasis, recém criada; instalar a imagem do Postgres e do Flyway, já criando o banco e a tabela necessária para os logs de acesso.

---
### Resultados
1. http://localhost:8080/api/swagger-ui/index.html
    > Página da documentação (Swagger) da API
2. http://localhost:8080/api/index
    > Frontend simples, usando a lib Leaflet pra mostrar um mapa e usar a API se baseando nas coordenadas do clique no mapa

---



###### *Termostase* é o termo usado para definir nosso ajuste natural de temperatura corporal 

###### <sub><sup>(Mas foi uma coincidência, pois para batizar o projeto estava pensando em "thermo" + êxtase, que eu em inglês não tem esse sentido)</sup></sub>




 
