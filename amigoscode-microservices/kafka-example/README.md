### Notes
I use docker image to start KAFKA bootstrap server: <br>
`docker pull apache/kafka:4.2.0` <br>
`docker run -p 9092:9092 apache/kafka:4.2.0` <br>
I create a topic first (inside the container): <br>
`/opt/kafka/bin $ ./kafka-topics.sh --create --topic amigoscode --bootstrap-server localhost:9092` <br>
Then I can consume my messages which I produce in my app: <br>
`kafka-console-consumer.sh --topic amigoscode --from-beginning --bootstrap-server localhost:9092`