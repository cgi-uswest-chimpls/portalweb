FROM openjdk:8
EXPOSE 8080
ADD build/libs/portalweb.jar portalweb.jar
ENTRYPOINT ["java","-jar","/portalweb.jar"]
