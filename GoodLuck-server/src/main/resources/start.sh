#!/bin/bash
#input your  service/application name
JAR_NAME="`pwd`/GoodLuck-server.jar"
echo --------------------------start/restart begin----------------------------------------
echo ----jar is $JAR_NAME

# dump目录
DIR_DUMP="`pwd`/dump"
if [ ! -d $DIR_DUMP ]; then
    mkdir $DIR_DUMP
fi

PID=$(ps -ef | grep $JAR_NAME | grep -v grep | awk '{ print $2 }')

# flag , only Y will restart
YES_OR_NO='Y'
if [ -z "$PID" ]
then
    echo ----$JAR_NAME service/application is already stopped
else
    #if will kill and restart
    echo -n  "$JAR_NAME service/application is already running ,do you want to restart it? type:Y(restart)/N(no operation) >"
    read  YES_OR_NO

    if [[ $YES_OR_NO = 'Y' ]]; then
        echo "----you type $YES_OR_NO,now will restart .... "
        echo ----kill $PID
        kill $PID
        echo ----killed $PID $JAR_NAME service/application.
        sleep 1s
    else
        echo "$YES_OR_NO,no the shell will exist"
    fi
fi

sleep 1s

if [[ $YES_OR_NO = 'Y' ]]; then
    CURRENT_PATH=$(pwd)
    echo ----pwd is $CURRENT_PATH
    echo ----now will start up the Application

    nohup java -Xms512M -Xmx512M  -XX:PermSize=128M -XX:MaxPermSize=128M -XX:+UseParallelOldGC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=$DIR_DUMP -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:dump/heap_trace.txt  -jar $JAR_NAME > console.out 2>&1 &

    sleep 1s

    echo ----ps check $JAR_NAME.
    ps -ef | grep  --color=auto $JAR_NAME | grep -v grep
    echo ----ps check $JAR_NAME end.

    echo ---- java -jar $JAR_NAME '&' executed , please check.
else
    echo "..."
fi
echo --------------------------start/restart end-----------------------------------------