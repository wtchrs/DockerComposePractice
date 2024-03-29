# build
FROM gradle:7.6.1-jdk17-alpine as build
WORKDIR /workspace/app

COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./
COPY src src

RUN gradle bootJar
RUN mkdir -p build/libs/dependency && (cd build/libs/dependency; jar -xf ../*.jar)

# run
FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/libs/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF

EXPOSE 8080

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "wtchrs.dockercomposepractice.DockerComposePracticeApplication"]
