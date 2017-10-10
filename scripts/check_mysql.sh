#!/bin/bash

USER=root
PASS=root
remote_server_ip=`cat /etc/hosts |grep 'mysql_machine'|awk '{print $1}'`

default_ip=192.168.30.25

mysqladmin -h$remote_server_ip -u$USER ping -p$PASS &>/dev/null  ###user should have mysql permission on remote server. Ideally you should use different user than root.

 if [ $? -eq 0 ]
   then
     echo "connecting successfully"
   else
     if [ "$remote_server_ip" == "$default_ip" ]
       then
         sudo sed -i 's/192.168.30.25/192.168.30.15/g' /etc/hosts
       else
          sudo sed -i 's/192.168.30.15/192.168.30.25/g' /etc/hosts
     fi
 fi