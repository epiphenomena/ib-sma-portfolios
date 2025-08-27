#!/bin/bash
# Final verification script for the IB SMA Portfolios data loader implementation

echo "=== IB SMA Portfolios Data Loader Implementation ==="
echo ""
echo "Checking project structure..."
echo "-----------------------------"
find src -name "*.java" | sort
echo ""
echo "Checking Maven dependencies..."
echo "----------------------------"
grep -A 5 "<dependencies>" pom.xml | grep -E "(jackson|junit)" | sed 's/^[[:space:]]*//'
echo ""
echo "Compiling project..."
echo "-------------------"
mvn compile
echo ""
echo "Running unit tests..."
echo "--------------------"
mvn test
echo ""
echo "Running demo..."
echo "--------------"
mvn exec:java -Dexec.mainClass="com.ib.sma.portfolios.PortfolioDataLoader"
echo ""
echo "=== Implementation Verification Complete ==="