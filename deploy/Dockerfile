FROM kylincloud2.hub/openjdk-jre:8
COPY platform-auth.jar /
COPY /etc/localtime /etc/
EXPOSE 20002


CMD ["java", "-Duser.timezone=GMT+8", "-Xmx2048M", "-Xms2048M", "-jar", "/platform/platform-auth.jar"]
