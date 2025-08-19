# IB SMA Portfolios

A Clojure project for managing SMA (Separately Managed Accounts) portfolios with Interactive Brokers.

## Prerequisites

- Clojure CLI tools installed

## Usage

To run the project:

```bash
clj -M:run
```

To run tests:

```bash
clj -M:test
```

## Dependencies

- `org.clojure/clojure` - The core Clojure language
- `org.clojure/data.json` - JSON parsing library
- `clj-http/clj-http` - HTTP client library
- `cheshire/cheshire` - Another JSON library for Clojure

## Project Structure

- `src/` - Source code
- `test/` - Test code
- `deps.edn` - Dependency management

## Data Files

This project works with data files from `/home/unveiled/work/P123/script/ib_data/`:

- `account_settings.json` - Account configuration
- `allocator_settings.json` - Allocator settings
- `portfolio_settings.json` - Portfolio configuration
- `prices.tsv` - Price data
- `shorting.tsv` - Shorting information
- `plain_ranks_*.tsv` - Ranking data for longs and shorts

## Available Data Modules

### Short Info Data

The `ib-sma-portfolios.shortinfo` namespace provides access to shorting data from the `shorting.tsv` file.

The data is available as `shortinfo-data`, which is a vector of maps with the following keys:
- `:SYM` - Symbol
- `:CUR` - Currency
- `:NAME` - Company name
- `:CON` - Contract ID
- `:ISIN` - ISIN code
- `:REBATERATE` - Rebate rate
- `:FEERATE` - Fee rate
- `:AVAILABLE` - Available quantity (as string, may be ">10000000")
- `:FIGI` - FIGI identifier

Example usage:
```clojure
(require '[ib-sma-portfolios.shortinfo :as shortinfo])

;; Get all shortinfo records
shortinfo/shortinfo-data

;; Find a specific symbol
(filter #(= "AAPL" (:SYM %)) shortinfo/shortinfo-data)

;; Get symbols with high availability
(->> shortinfo/shortinfo-data
     (filter #(> (Long/parseLong (:AVAILABLE %)) 1000000))
     (take 5))
```

### Prices Data

The `ib-sma-portfolios.prices` namespace provides access to price data from the `prices.tsv` file.

The data is available as `prices-data`, which is a vector of maps with the following keys:
- `:SYMBOL` - Symbol with country code
- `:PRICE` - Price (as string)
- `:DATE` - Date
- `:SECTOR` - Sector

Example usage:
```clojure
(require '[ib-sma-portfolios.prices :as prices])

;; Get all prices records
prices/prices-data

;; Find a specific symbol
(filter #(= "AAPL:USA" (:SYMBOL %)) prices/prices-data)

;; Get stocks with high prices
(->> prices/prices-data
     (filter #(> (Double/parseDouble (:PRICE %)) 100.0))
     (take 5))
```

### Settings Data

The `ib-sma-portfolios.settings` namespace provides access to settings data from the JSON files:
- `account_settings.json`
- `allocator_settings.json` 
- `portfolio_settings.json`

The data is available as:
- `account-settings` - Account configuration data
- `allocator-settings` - Allocator configuration data
- `portfolio-settings` - Portfolio configuration data

Example usage:
```clojure
(require '[ib-sma-portfolios.settings :as settings])

;; Get all account settings
settings/account-settings

;; Get specific account info
(get settings/account-settings "U1667447")

;; Get allocator settings
settings/allocator-settings

;; Get portfolio settings
settings/portfolio-settings

;; Get specific portfolio info
(get settings/portfolio-settings "Omega")
```