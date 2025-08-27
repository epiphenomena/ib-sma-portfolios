package com.ib.sma.portfolios;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ib.sma.data.SettingsLoader;
import com.ib.sma.data.RankFileLoader;
import com.ib.sma.data.PriceFileLoader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PortfolioDataLoader {
    
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    public static void main(String[] args) {
        String dataDir = "example_data";
        
        try {
            // Load settings files
            JsonNode portfolioSettings = SettingsLoader.loadPortfolioSettings(dataDir + "/portfolio_settings.json");
            JsonNode accountSettings = SettingsLoader.loadAccountSettings(dataDir + "/account_settings.json");
            JsonNode allocatorSettings = SettingsLoader.loadAllocatorSettings(dataDir + "/allocator_settings.json");
            
            System.out.println("Loaded portfolio settings with " + portfolioSettings.size() + " portfolios");
            System.out.println("Loaded account settings with " + accountSettings.size() + " accounts");
            System.out.println("Loaded allocator settings");
            
            // Load rank files
            List<RankFileLoader.RankEntry> longs = RankFileLoader.loadRankFile(dataDir + "/plain_ranks_longs.tsv");
            List<RankFileLoader.RankEntry> iceLongs = RankFileLoader.loadRankFile(dataDir + "/plain_ranks_ice_longs.tsv");
            List<RankFileLoader.RankEntry> shorts = RankFileLoader.loadRankFile(dataDir + "/plain_ranks_shorts.tsv");
            
            System.out.println("Loaded " + longs.size() + " long positions");
            System.out.println("Loaded " + iceLongs.size() + " ICE long positions");
            System.out.println("Loaded " + shorts.size() + " short positions");
            
            // Load prices file
            List<PriceFileLoader.PriceEntry> prices = PriceFileLoader.loadPricesFile(dataDir + "/prices.tsv");
            System.out.println("Loaded " + prices.size() + " price entries");
            
            // Display sample data
            System.out.println("\nSample long position:");
            if (!longs.isEmpty()) {
                System.out.println(longs.get(0));
            }
            
            System.out.println("\nSample ICE long position:");
            if (!iceLongs.isEmpty()) {
                System.out.println(iceLongs.get(0));
            }
            
            System.out.println("\nSample short position:");
            if (!shorts.isEmpty()) {
                System.out.println(shorts.get(0));
            }
            
            System.out.println("\nSample price entry:");
            if (!prices.isEmpty()) {
                System.out.println(prices.get(0));
            }
            
        } catch (IOException e) {
            System.err.println("Error loading data files: " + e.getMessage());
            e.printStackTrace();
        }
    }
}