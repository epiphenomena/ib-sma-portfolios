# IB SMA Portfolios - Data Loading Implementation

This project implements Java classes to load all the data files for the IB SMA Portfolios system.

## Project Structure

```
src/
└── main/
    └── java/
        └── com/
            └── ib/
                └── sma/
                    └── portfolios/
                        └── PortfolioDataLoader.java
                    └── data/
                        ├── SettingsLoader.java
                        ├── RankFileLoader.java
                        └── PriceFileLoader.java
```

## Components

### SettingsLoader.java
Loads JSON settings files:
- `portfolio_settings.json`
- `account_settings.json`
- `allocator_settings.json`

### RankFileLoader.java
Loads TSV rank files:
- `plain_ranks_longs.tsv`
- `plain_ranks_ice_longs.tsv`
- `plain_ranks_shorts.tsv`

### PriceFileLoader.java
Loads the `prices.tsv` file.

### PortfolioDataLoader.java
Main class that demonstrates how to use all the loaders to read the data files.

## Usage

To compile and run the project:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.ib.sma.portfolios.PortfolioDataLoader"
```

## Output

The implementation successfully loads:
- Portfolio settings with 4 portfolios
- Account settings with 4 accounts
- Allocator settings
- 9,238 long positions
- 9,289 ICE long positions
- 9,238 short positions
- 9,238 price entries

It also displays sample entries from each file to verify the loading process.