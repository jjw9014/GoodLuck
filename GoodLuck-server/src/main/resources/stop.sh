#!/bin/bash
#please remove all OTS(uppercase) to your own service/application name
JAR_NAME="`pwd`/GoodLuck-server.jar"
echo --------------------------stop begin----------------------------------------
echo ----jar is $JAR_NAME

PID=$(ps -ef | grep $JAR_NAME | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    echo ----$JAR_NAME Application is already stopped
else
    echo ----kill $PID
    kill -9 $PID
    echo ----killed $PID $JAR_NAME Application.
	sleep 1s
fi

echo ----ps check $JAR_NAME.
ps -ef | grep  --color=auto $JAR_NAME | grep -v grep
echo ----ps check $JAR_NAME end.
echo --------------------------stop end-----------------------------------------
