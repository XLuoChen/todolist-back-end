FROM java:8

VOLUME /tmp

ADD ./target/todo-list-api-1.0.jar todolist.jar

RUN bash -c 'touch /todolist.jar'

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/todolist.jar"]