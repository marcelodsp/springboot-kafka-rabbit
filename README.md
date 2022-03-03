# Spring Boot + Kafka + RabbitMQ + MongoDB + MySQL + WebFlux + MVC
Aplicações de exemplo simples utilizando o framework Spring Boot, Kafka e RabbitMQ.

## Aplicação kafka-teste

Neste exemplo é implementada uma aplicação de testes utilizando o Spring Boot em modo reativo (WebFlux), persistindo dados em uma base NoSql MongoDB e tratando mensagens com Kafka como producer e RabbitMB como consumer.

## Aplicação rabbit-teste

Neste exmplo é implentada uma aplicação Spring Boot MVC simples, não reativa, persistindo dados em uma base relacional MySQL e tratando mensagens com RabbitMB como producer e Kafka como consumer.

## Docker
Existem duas estruturas composer onde é possível iniciar rapidamente os bancos de dados utilizados nas aplicações como também os brokers Kafka e RabbitMB, além de aplicações utilitárias como Kafdrop e Mongo Express