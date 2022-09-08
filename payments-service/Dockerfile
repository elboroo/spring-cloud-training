FROM openjdk:17-slim
ADD target/payments-service.jar /
CMD java -jar \
    -Dspring.profiles.active=$PROFILE \
    -Dspring.config.import=$CONFIGURATION_SERVER \
    payments-service.jar
