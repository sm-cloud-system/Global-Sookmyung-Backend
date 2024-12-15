FROM amd64/eclipse-temurin:17-jdk

COPY ./build/libs/global-0.0.1-SNAPSHOT.jar global-sookmyung.jar

CMD ["java", "-Duser.timezone=Asia/Seoul", "-jar", "global-sookmyung.jar"]