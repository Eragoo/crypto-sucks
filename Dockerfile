FROM openjdk:11 as builder

WORKDIR /app
COPY . /app

RUN ./gradlew clean build

FROM openjdk:11.0-jre-slim

WORKDIR /app

COPY --from=builder /app/build/libs/core.jar /app

EXPOSE 8000

CMD java -jar /app/core.jar
