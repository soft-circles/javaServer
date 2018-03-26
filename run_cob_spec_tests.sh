#!/bin/bash
set -e
cd cob_spec
mvn package
java -jar fitnesse.jar -c "PassingTests?suite&format=text"
