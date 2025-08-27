package com.ib.sma.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SettingsLoader {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    public static JsonNode loadPortfolioSettings(String filePath) throws IOException {
        return objectMapper.readTree(new File(filePath));
    }
    
    public static JsonNode loadAccountSettings(String filePath) throws IOException {
        return objectMapper.readTree(new File(filePath));
    }
    
    public static JsonNode loadAllocatorSettings(String filePath) throws IOException {
        return objectMapper.readTree(new File(filePath));
    }
}