FROM java:8
MAINTAINER keryhu@hotmail.com
VOLUME /tmp
LABEL can-autoremove="true"
RUN mkdir /app
EXPOSE 8001
ADD account-activate-0.0.1-SNAPSHOT.jar /app/app.jar
COPY runboot.sh /app/
RUN bash -c 'touch  /app/app.jar'
WORKDIR /app
RUN chmod a+x runboot.sh
CMD /app/runboot.sh



