#!/bin/bash

today=`date +%Y%m%d`

mvn clean package

docker build -t chengxiuluo/todolist-backend:${today} .
docker build -t chengxiuluo/todolist-backend:latest .

docker push chengxiuluo/todolist-backend:${today}
docker push chengxiuluo/todolist-backend:latest

#docker rmi chengxiuluo/todolist-backend:${today}
#docker rmi chengxiuluo/todolist-backend:latest