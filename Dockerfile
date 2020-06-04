FROM alpine:latest
MAINTAINER Arkajyoti Nag(arka.imps@gmail.com)
RUN apk update
RUN apk fetch openjdk8
RUN apk add openjdk8
COPY pom.xml /RemoteWebDriver/pom.xml
COPY src /RemoteWebDriver/src
COPY testng.xml /RemoteWebDriver/testng.xml
COPY target/RemoteWebDriver-0.0.1-SNAPSHOT.jar /RemoteWebDriver/RemoteWebDriver-0.0.1-SNAPSHOT.jar
WORKDIR /RemoteWebDriver