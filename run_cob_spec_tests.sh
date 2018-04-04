#!/bin/bash
set -e
./gradlew run
cd cob_spec
mvn package
java -jar fitnesse.jar -c "PassingTests?suite&format=text"
