#!/bin/bash

today=`date +%Y%m%d`

mvn clean package

cat ~/my_password.txt | docker login --username chengxiuluo --password-stdin

sudo docker build -t chengxiuluo/todolist-backend:${today} .
sudo docker build -t chengxiuluo/todolist-backend:latest .

sudo docker push chengxiuluo/todolist-backend:${today}
sudo docker push chengxiuluo/todolist-backend:latest

#docker rmi chengxiuluo/todolist-backend:${today}
#docker rmi chengxiuluo/todolist-backend:latest