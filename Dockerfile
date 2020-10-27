# 使用JDK8作为基础镜像
FROM openjdk:8-jdk-alpine

VOLUME /tmp

COPY build/libs/git-ops-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
