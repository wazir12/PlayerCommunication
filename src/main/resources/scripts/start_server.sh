#!/bin/bash

# Navigate to the project root directory
cd "$(dirname "$0")/../../../.."

mvn clean install

# Compile the project (assuming Maven is used)
mvn compile

# Run the Server class
mvn exec:java -Dexec.mainClass="org.threesixtyT.ServerPlayer"