FROM eclipse-temurin:21-jre
WORKDIR /work/

# Quarkus (fast-jar)
COPY build/quarkus-app/lib/ /work/lib/
COPY build/quarkus-app/app/ /work/app/
COPY build/quarkus-app/quarkus/ /work/quarkus/
COPY build/quarkus-app/quarkus-run.jar /work/quarkus-run.jar

EXPOSE 8080
CMD ["java","-jar","/work/quarkus-run.jar"]
