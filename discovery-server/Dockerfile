FROM openjdk:17-slim
ADD target/discovery-server.jar /
CMD java -jar \
    -Dspring.profiles.active=$PROFILE \
    -Dspring.config.import=$CONFIGURATION_SERVER \
    discovery-server.jar