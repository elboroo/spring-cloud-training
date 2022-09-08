FROM openjdk:17-slim
ADD target/warehouse-service.jar /
CMD java -jar \
    -Dspring.profiles.active=$PROFILE \
    -Dspring.config.import=$CONFIGURATION_SERVER \
    warehouse-service.jar
