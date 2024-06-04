FROM tomcat:10-jdk11-corretto
# Set the working directory inside the container
WORKDIR /usr/local/tomcat

# Remove default webapps
RUN rm -rf webapps/*

# Copy the WAR file to the webapps directory
COPY build/libs/jpl-ferrari-jobhunt-0.0.1-SNAPSHOT.war webapps/ROOT.war

# Expose the default port for Tomcat
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
