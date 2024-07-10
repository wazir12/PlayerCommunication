#!/bin/bash

# Compile the project (assuming Maven is used)
mvn compile

# Run the ClientMain class
java -cp target/classes org.threesixtyT.ClientPlayer