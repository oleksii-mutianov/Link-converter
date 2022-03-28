FROM bellsoft/liberica-openjre-alpine:17.0.2

COPY build/libs/*.jar /app/link-converter.jar

EXPOSE 8080

CMD java $JAVA_OPTS -jar /app/link-converter.jar
