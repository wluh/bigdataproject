#!/bin/sh

javac -classpath `yarn classpath` -d . "$1Mapper.java"
javac -classpath `yarn classpath` -d . "$1Reducer.java"
javac -classpath `yarn classpath`:. -d . "$1.java"
jar -cvf "$1.jar" *.class

hdfs dfs -put "$2" /user/wsl300/project/rawdata

hadoop jar "$1.jar" "$1" /user/wsl300/project/rawdata/"$2" "$3"
