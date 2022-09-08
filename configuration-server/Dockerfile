FROM openjdk:17-slim
ADD target/configuration-server.jar /
CMD java -jar \
    -Dserver.port=$PORT \
    -Dspring.cloud.config.server.git.uri=$GIT_URI \
    -Dspring.cloud.stream.kafka.binder.brokers=$KAFKA_BROKERS \
    configuration-server.jar
