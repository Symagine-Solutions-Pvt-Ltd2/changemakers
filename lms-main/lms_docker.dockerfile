FROM openjdk:11
ADD /target/change_maker-0.0.1.jar change_maker-0.0.1.jar
EXPOSE $application_port
ENTRYPOINT ["java", "-jar", "change_maker-0.0.1.jar"]