FROM openjdk:8-jre
MAINTAINER Lusifer <topsale@vip.qq.com>
ENV APP_VERSION 1.0.0-SNAPSHOT
RUN mkdir /app
COPY target/qfdmy-apiserver-dashboard-$APP_VERSION.jar /app/app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar", "--spring.profiles.active=prod"]
EXPOSE 8080