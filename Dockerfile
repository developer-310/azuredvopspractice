FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
RUN addgroup -S devopsgroup && adduser -S devopsuser -G devopsgroup
COPY target/webapp-1.0.0.jar app.jar
RUN chown -R devopsuser:devopsgroup /app
USER devopsuser
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
