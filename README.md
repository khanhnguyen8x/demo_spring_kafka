# demo_spring_kafka

Demo spring boot with kafka

## Create docker compose kafka
file docker-compose.yml
```yml

version: '3'
services:
zookeeper:
image: bitnami/zookeeper:latest
environment:
- ALLOW_ANONYMOUS_LOGIN=yes

kafka:
image: bitnami/kafka:latest
depends_on:
- zookeeper
environment:
- KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181    
- KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://kafka:29092,PLAINTEXT_HOST://localhost:9092
- KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
- KAFKA_LISTENERS=PLAINTEXT://:29092,PLAINTEXT_HOST://:9092
- ALLOW_PLAINTEXT_LISTENER=yes
ports:
- "9092:9092"

kafka-ui:
image: provectuslabs/kafka-ui:latest
ports:
- "8585:8080"
environment:
- KAFKA_CLUSTERS_0_NAME=local
- KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS=kafka:29092
- KAFKA_CLUSTERS_0_KAFKAPROTOCOL=PLAINTEXT

```


## Create topic
```bash 

```


## Run project branch dev/mode_single
```bash
curl --location 'http://localhost:8080/send' \
--header 'Content-Type: application/json' \
--data '{"topic": "int.notification-system.notification", "message": "hello", "total": 1000}'
```
Result
```bash
Total time for processing: 312120 ms
```

## Run project branch dev/mode_multiple_thread
```bash
curl --location 'http://localhost:8080/send' \
--header 'Content-Type: application/json' \
--data '{"topic": "int.notification-system.notification", "message": "hello", "total": 1000}'
```
Result
```bash
Total time for processing: 116978 ms
```


## Run project branch dev/mode_batch
```bash
curl --location 'http://localhost:8080/send' \
--header 'Content-Type: application/json' \
--data '{"topic": "int.notification-system.notification", "message": "hello", "total": 1000}'
```
Result
```bash
Total time for processing: 6300 ms
```

## Conclusion
The best way to send message to kafka is using batch mode.