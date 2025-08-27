#!/bin/bash
# Script to run the portfolio data loader demo

echo "Compiling the project..."
mvn compile

if [ $? -eq 0 ]; then
    echo "Running the portfolio data loader demo..."
    mvn exec:java -Dexec.mainClass="com.ib.sma.portfolios.PortfolioDataLoader"
else
    echo "Compilation failed. Please check the source code for errors."
    exit 1
fi