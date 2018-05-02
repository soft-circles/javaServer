# Java Server
[![Build Status](https://travis-ci.org/akiraandy/javaServer.svg?branch=master)](https://travis-ci.org/akiraandy/javaServer)

## A HTTP server written in Java

Add cob_spec submodule

```
git submodule init
git submodule update
```

If you have gradle installed on your system:

```gradle build```

Then to run the server:

```gradle run```

To run the tests:

```gradle test```

If you do not have gradle installed:
Build:

```./gradlew build```

Run:

```./gradlew run```

Test:

```./gradlew test```

## Passing in Arguments

To run server form the command line do the following:

```$bash
gradle run -P args="-p 1234 -d ./myDirectory"
```

Add the ```-P``` flag followed by ```args=" "``` with arguments passed in between the quotation marks.

#### Options

-p [number], --port [number] : Sets the port to [number] instead of the default of 5000

-d [path], --directory [path] : Sets working directory to [path] instead of default "./cob_spec"

Server will run on port 5000 and public directory will default to the current project directory.

## Cob Spec

To run cob spec tests enter the following:
```
chmod +x run_cob_spec_tests.sh
./run_cob_spec_tests.sh
```
