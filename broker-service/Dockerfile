FROM openjdk:17-slim
ADD target/broker-service.jar /
CMD java -jar \
    -Dspring.profiles.active=$PROFILE \
    -Dspring.config.import=$CONFIGURATION_SERVER \
    broker-service.jar
