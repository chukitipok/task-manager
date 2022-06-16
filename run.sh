#!/bin/bash
mvn clean package
java -jar ./target/task-manager-1.0-SNAPSHOT.jar -c "hello world"


