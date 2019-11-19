#!/bin/sh
echo "Processing text ..."
java -jar TP.jar $1 $2
sleep 2
echo "Results ready " + $2
