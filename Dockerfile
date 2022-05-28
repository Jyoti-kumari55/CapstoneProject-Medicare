From openjdk:8
Expose 8080
Add target/Medicare-0.0.1-SNAPSHOT.war Medicare-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/Medicare-0.0.1-SNAPSHOT.war"]
