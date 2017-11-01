#!/bin/bash

mysql_container_name=todolist-mysql
app_container_name=todolist-api

docker stop ${mysql_container_name}
docker stop ${app_container_name}

docker run --name ${mysql_container_name} --rm -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=TodoList -e MYSQL_USER=todolist -e MYSQL_PASSWORD=todolist -v "$(pwd)/data":/var/lib/mysql -d mysql:5.6

docker run -p 8080:8080 --name ${app_container_name} --rm --link ${mysql_container_name}:mysql_machine -d chengxiuluo/todolist-backend:latest