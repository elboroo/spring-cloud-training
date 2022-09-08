FROM openjdk:17-slim
ADD target/shop-service.jar /
CMD java -jar \
    -Dspring.profiles.active=$PROFILE \
    -Dspring.config.import=$CONFIGURATION_SERVER \
    shop-service.jar
