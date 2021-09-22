FROM maven:3.8.1-jdk-11 AS MAVEN_BUILD

WORKDIR /build/

COPY pom.xml /build/
COPY src /build/src/

RUN mvn clean package
RUN mvn package

FROM openjdk:11

COPY images /images
COPY --from=MAVEN_BUILD build/target/*.jar demo-0.0.1-SNAPSHOT.jar

EXPOSE 9091

CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
