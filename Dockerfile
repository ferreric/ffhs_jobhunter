# Build Stage
FROM gradle:8.7-jdk17-jammy AS BUILD

WORKDIR /app

# Cache dependencies
COPY . .

RUN chmod +x gradlew
RUN echo $PATH
RUN gradle build --no-daemon

################################################################################

# Tomcat Stage
FROM tomcat:11.0-jdk17
#WORKDIR /usr/local/tomcat
# Remove default webapps
RUN rm -rf webapps/*

# Copy the WAR file to the webapps directory
COPY --from=BUILD /app/build/libs/jpl-ferrari-jobhunt-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose the default port for Tomcat
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]