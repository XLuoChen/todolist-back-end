#!/bin/bash

docker run --name todolist-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=TodoList -e MYSQL_USER=todolist -e MYSQL_PASSWORD=todolist -d mysql:5.6

docker run -p 8080:8080 --name todolist-api --link todolist-mysql:mysql_machine -d chengxiuluo/todolist-backend:latest