gradle test
cd cob_spec
mvn package
java -jar fitnesse.jar -c "HttpTestSuite.ResponseTestSuite.SimpleGet?test&format=text"
