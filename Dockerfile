FROM bellsoft/liberica-openjdk-alpine:17.0.3.1
ARG JAR_FILE=build/libs/store-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app.jar
RUN mkdir "minio"
VOLUME /minio
COPY ./images/*.jpg /minio
ENTRYPOINT ["java", "-jar", "app.jar"]