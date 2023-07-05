FROM maven:3.6.0-jdk-11-slim AS build
WORKDIR /home/app
COPY pom.xml .
COPY src src
ENV MAVEN_CONFIG=''
RUN mvn package

FROM tomcat:latest

LABEL maintainer="Debasish"

COPY --from=build /home/app/target/C:\Users\shayan\IdeaProjects\lms-web-app\target\lms-web-app-0.0.1.jar /usr/local/tomcat/webapps/LoginWebApp-1.war
#ADD ./target/LoginWebApp-1.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]