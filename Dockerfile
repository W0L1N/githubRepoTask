FROM eclipse-temurin
ARG JAR_FILE=target/githubRepoTask-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} githubRepoTask.jar
EXPOSE 8080
ENTRYPOINT ["java" ,"-jar", "/githubRepoTask.jar"]
