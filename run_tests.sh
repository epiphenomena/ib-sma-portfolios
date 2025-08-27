#!/bin/bash
# Script to run unit tests

echo "Compiling the project..."
mvn compile

if [ $? -eq 0 ]; then
    echo "Running unit tests..."
    mvn test
else
    echo "Compilation failed. Please check the source code for errors."
    exit 1
fi