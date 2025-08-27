package com.ib.sma.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RankFileLoaderTest {
    
    @TempDir
    Path tempDir;
    
    @Test
    public void testParseLine() throws IOException {
        // Create a temporary TSV file with test data
        String testContent = "# Test header\n" +
                            "AAPL\t1.5\tTech Sector\n" +
                            "GOOGL\t2.0\tTech Sector\n";
        
        Path testFile = tempDir.resolve("test_ranks.tsv");
        Files.write(testFile, testContent.getBytes());
        
        // Load the file
        List<RankFileLoader.RankEntry> entries = RankFileLoader.loadRankFile(testFile.toString());
        
        // Verify results
        assertEquals(2, entries.size());
        assertEquals("AAPL", entries.get(0).getSymbol());
        assertEquals(1.5, entries.get(0).getRank());
        assertEquals("Tech Sector", entries.get(0).getCriteria());
        
        assertEquals("GOOGL", entries.get(1).getSymbol());
        assertEquals(2.0, entries.get(1).getRank());
        assertEquals("Tech Sector", entries.get(1).getCriteria());
    }
}