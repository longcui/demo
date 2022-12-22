# we use Eclipse Termurin, one of the most popular official images with a build-worthy JDK.
FROM eclipse-temurin:17-jdk-jammy

VOLUME /tmp
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]