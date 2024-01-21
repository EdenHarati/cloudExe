FROM openjdk:11
RUN mkdir /app
COPY src/main/java/TodoServer/JavaServerApplication.java /app
WORKDIR /app
CMD java Main