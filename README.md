# Java Server
[![Build Status](https://travis-ci.org/akiraandy/javaServer.svg?branch=master)](https://travis-ci.org/akiraandy/javaServer)

## A HTTP server written in Java

If you have gradle installed on your system:

```gradle build```

Add cob_spec submodule

```
git submodule init
git submodule update
```

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

Server will run on port 5000 and public directory will default to the current project directory.

