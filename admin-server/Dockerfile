FROM openjdk:17-slim
ADD target/admin-server.jar /
CMD java -jar \
    -Dserver.port=$PORT \
    admin-server.jar