FROM azul/zulu-openjdk-alpine:11-jre

EXPOSE 8081

COPY target/springBoot-5-hw-01-0.0.1-SNAPSHOT.jar myapp.jar

ENTRYPOINT ["java","-jar","/myapp.jar"]