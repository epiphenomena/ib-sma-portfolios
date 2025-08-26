# IB SMA Portfolios -- WORK IN PROGRESS

A Java project for managing SMA (Separately Managed Accounts) portfolios with Interactive Brokers.


## Data Files

This project works with data files from a specified directory (passed to CLI as an argument, example files in example_data dir):

- `account_settings.json` - Account configuration
- `allocator_settings.json` - Allocator settings
- `portfolio_settings.json` - Portfolio configuration
- `prices.tsv` - Price data
- `shorting.tsv` - Shorting information
- `plain_ranks_*.tsv` - Ranking data for longs and shorts