FROM openjdk:17-slim
ADD target/gateway-server.jar /
CMD java -jar \
    -Dspring.profiles.active=$PROFILE \
    -Dspring.config.import=$CONFIGURATION_SERVER \
    gateway-server.jar