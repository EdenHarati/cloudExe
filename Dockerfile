#FROM openjdk:11
#RUN mkdir /app
#COPY src/main/java/TodoServer/JavaServerApplication.java /app
#WORKDIR /app
#CMD java Main

FROM openjdk:11
WORKDIR /app
COPY target/exe3-server-1.0-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "exe3-server-1.0-SNAPSHOT.jar"]
#CMD java JavaServerApplication