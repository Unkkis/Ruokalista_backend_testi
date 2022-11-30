FROM eclipse-temurin:17-jdk-jammy

RUN mkdir /opt/app
COPY target/RuokalistaApp-1.jar /opt/app

CMD ["java", "-jar", "/opt/app/RuokalisstaApp-1.jar"]