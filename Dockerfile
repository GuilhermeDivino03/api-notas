FROM maven:3.8.4-jdk-8 as build
RUN mkdir /opt/nota
WORKDIR /opt/nota
COPY . /opt/notas


FROM openjdk:17-alpine
COPY --from=build /opt/notas/target/Notas*.jar Nota.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]