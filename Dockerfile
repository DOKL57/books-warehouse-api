FROM adoptopenjdk:17-jre-hotspot
EXPOSE 8080
ADD target/simple-warehouse-api-0.0.1-SNAPSHOT.jar simple-warehouse-api
ENTRYPOINT ["java", "-jar", "/simple-warehouse-api.jar"]

