package com.ib.sma.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PriceFileLoader {
    
    public static class PriceEntry {
        private String symbol;
        private double price;
        private LocalDate date;
        private String sector;
        
        public PriceEntry(String symbol, double price, LocalDate date, String sector) {
            this.symbol = symbol;
            this.price = price;
            this.date = date;
            this.sector = sector;
        }
        
        public String getSymbol() { return symbol; }
        public double getPrice() { return price; }
        public LocalDate getDate() { return date; }
        public String getSector() { return sector; }
        
        @Override
        public String toString() {
            return "PriceEntry{symbol='" + symbol + "', price=" + price + ", date=" + date + ", sector='" + sector + "'}";
        }
    }
    
    public static List<PriceEntry> loadPricesFile(String filePath) throws IOException {
        List<PriceEntry> entries = new ArrayList<>();
        
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
                PriceEntry entry = parseLine(line);
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
    
    private static PriceEntry parseLine(String line) {
        String[] parts = line.split("\t");
        if (parts.length >= 4) {
            try {
                String symbol = parts[0];
                double price = Double.parseDouble(parts[1]);
                LocalDate date = LocalDate.parse(parts[2]);
                String sector = parts[3];
                return new PriceEntry(symbol, price, date, sector);
            } catch (Exception e) {
                System.err.println("Error parsing line: " + line);
                return null;
            }
        }
        return null;
    }
}