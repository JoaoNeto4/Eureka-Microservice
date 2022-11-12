FROM maven:3.8.5-openjdk-11 as build
WORKDIR /app
COPY . .
RUN mnv clean package -DskipTests

FROM openjdk:11
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar
ENTRYPOINT java .ajr app.jar