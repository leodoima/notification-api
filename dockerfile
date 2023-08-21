# FROM gradle:jdk17 as gradleimage
#COPY . /home/gradle/source
#WORKDIR /home/gradle/source
#RUN ./gradlew clean build jar

FROM openjdk:17
# COPY --from=gradleimage /home/gradle/source/build/libs/notification-api.jar /app/
COPY ./build/libs/notification-api.jar /app/
WORKDIR /app
ENTRYPOINT ["java", "-jar", "notification-api.jar", "--TOTAL_VOICE_TOKEN=84f1d2eb31f60dd2733d18c15ee151d8"]