package com.ib.sma.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RankFileLoader {
    
    public static class RankEntry {
        private String symbol;
        private double rank;
        private String criteria;
        
        public RankEntry(String symbol, double rank, String criteria) {
            this.symbol = symbol;
            this.rank = rank;
            this.criteria = criteria;
        }
        
        public String getSymbol() { return symbol; }
        public double getRank() { return rank; }
        public String getCriteria() { return criteria; }
        
        @Override
        public String toString() {
            return "RankEntry{symbol='" + symbol + "', rank=" + rank + ", criteria='" + criteria + "'}";
        }
    }
    
    public static List<RankEntry> loadRankFile(String filePath) throws IOException {
        List<RankEntry> entries = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip header lines that start with #
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("#")) {
                    break;
                }
            }
            
            // Process data lines
            if (line != null && !line.startsWith("#")) {
                // Process the first data line we already read
                RankEntry entry = parseLine(line);
                if (entry != null) {
                    entries.add(entry);
                }
                
                // Process the rest of the lines
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty() && !line.startsWith("#")) {
                        entry = parseLine(line);
                        if (entry != null) {
                            entries.add(entry);
                        }
                    }
                }
            }
        }
        
        return entries;
    }
    
    private static RankEntry parseLine(String line) {
        String[] parts = line.split("\\t");
        if (parts.length >= 3) {
            try {
                String symbol = parts[0];
                double rank = Double.parseDouble(parts[1]);
                String criteria = parts[2];
                return new RankEntry(symbol, rank, criteria);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing line: " + line);
                return null;
            }
        }
        return null;
    }
}