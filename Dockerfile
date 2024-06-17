# 자바 1.8 버전이므로 openjdk:8-jdk
# 일반적으로 alpine버전을 많이 사용함
FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY build/libs/webservice-1.0.1-SNAPSHOT-20240613120512.jar /app/app.jar

EXPOSE 8081 8082

ENTRYPOINT ["java", "-jar", "app.jar"]