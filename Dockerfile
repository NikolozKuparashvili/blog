# Use official Java 17 image
FROM eclipse-temurin:17-jdk-alpine
# Copy the built jar file into the image
COPY target/blog-0.0.1-SNAPSHOT.jar app.jar
# Command to run the app
ENTRYPOINT ["java","-jar","/app.jar"]