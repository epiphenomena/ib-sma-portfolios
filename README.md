# IB SMA Portfolios Data Loader

This project provides Java classes to load and parse all the data files used in the IB SMA Portfolios system.

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── ib/
│               └── sma/
│                   ├── data/
│                   │   ├── SettingsLoader.java
│                   │   ├── RankFileLoader.java
│                   │   └── PriceFileLoader.java
│                   └── portfolios/
│                       └── PortfolioDataLoader.java
└── test/
    └── java/
        └── com/
            └── ib/
                └── sma/
                    └── data/
                        └── RankFileLoaderTest.java
```

## Components

### SettingsLoader.java
Handles loading of JSON configuration files:
- `portfolio_settings.json`
- `account_settings.json`
- `allocator_settings.json`

### RankFileLoader.java
Handles loading of TSV rank files:
- `plain_ranks_longs.tsv`
- `plain_ranks_ice_longs.tsv`
- `plain_ranks_shorts.tsv`

Each rank file entry contains:
- Symbol
- Rank value
- Selection criteria

### PriceFileLoader.java
Handles loading of the `prices.tsv` file which contains:
- Symbol
- Price
- Date
- Sector

### PortfolioDataLoader.java
Main demonstration class that shows how to use all the loaders together.

## Usage

### Prerequisites
- Java 11 or higher
- Maven

### Running the Demo

You can run the demo in two ways:

1. Using Maven directly:
   ```bash
   mvn compile
   mvn exec:java -Dexec.mainClass="com.ib.sma.portfolios.PortfolioDataLoader"
   ```

2. Using the provided script:
   ```bash
   ./run_demo.sh
   ```

### Running Tests

1. Using Maven:
   ```bash
   mvn test
   ```

2. Using the provided script:
   ```bash
   ./run_tests.sh
   ```

## Expected Output

When running the demo, you should see output similar to:

```
Loaded portfolio settings with 4 portfolios
Loaded account settings with 4 accounts
Loaded allocator settings
Loaded 9238 long positions
Loaded 9289 ICE long positions
Loaded 9238 short positions
Loaded 9238 price entries

Sample long position:
RankEntry{symbol='AABVF', rank=0.0, criteria='MktCap > 50 | Universe(NOOTC)=TRUE | Price > 3 | Minimum Daily Volume'}

Sample ICE long position:
RankEntry{symbol='AABVF', rank=0.0, criteria='MktCap > 50 | Universe(NOOTC)=TRUE | Price > 3 | Minimum Daily Volume'}

Sample short position:
RankEntry{symbol='AABVF', rank=0.0, criteria='MktCap > 500 | Universe(NOOTC)=TRUE | Price > 3 | Minimum Daily Volume'}

Sample price entry:
PriceEntry{symbol='A:USA', price=119.15, date=2025-04-30, sector='HEALTHCARE'}
```

## Extending the Implementation

This implementation provides a solid foundation for loading the data files. To extend it for your specific needs:

1. Modify the data classes in each loader to include additional fields if needed
2. Add validation logic to ensure data integrity
3. Implement additional filtering or sorting capabilities
4. Add support for writing data back to files if needed
5. Integrate with your portfolio management system