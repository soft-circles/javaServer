#!/bin/bash
set -e
cd cob_spec
mvn package
java -jar fitnesse.jar -c "HttpTestSuite.ResponseTestSuite.SimpleGet?test&format=text"
