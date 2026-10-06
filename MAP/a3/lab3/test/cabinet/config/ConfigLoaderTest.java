package cabinet.config;

import cabinet.config.ConfigLoader;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ConfigLoaderTest {

    @Test
    void testLoadValidPropertiesFile() throws IOException {
        // Creăm un fișier temporar de configurare
        String configPath = "test-config.properties";
        try (FileWriter writer = new FileWriter(configPath)) {
            writer.write("key1=value1\n");
            writer.write("key2=value2\n");
        }

        // Testăm ConfigLoader
        ConfigLoader configLoader = new ConfigLoader(configPath);
        assertEquals("value1", configLoader.getProperty("key1"));
        assertEquals("value2", configLoader.getProperty("key2"));
    }

    @Test
    void testDefaultValueForMissingKey() throws IOException {
        // Creăm un fișier temporar de configurare
        String configPath = "test-default-config.properties";
        try (FileWriter writer = new FileWriter(configPath)) {
            writer.write("key1=value1\n");
        }

        // Testăm ConfigLoader
        ConfigLoader configLoader = new ConfigLoader(configPath);
        assertEquals("default", configLoader.getProperty("missingKey", "default"));
    }

    @Test
    void testExceptionForMissingFile() {
        String invalidPath = "nonexistent-config.properties";
        Exception exception = assertThrows(RuntimeException.class, () -> new ConfigLoader(invalidPath));
        assertTrue(exception.getMessage().contains("Eroare la citirea fișierului"));
    }

    @Test
    void testMissingKeyReturnsNull() throws IOException {
        // Creăm un fișier temporar de configurare
        String configPath = "test-null-config.properties";
        try (FileWriter writer = new FileWriter(configPath)) {
            writer.write("key1=value1\n");
        }

        // Testăm ConfigLoader
        ConfigLoader configLoader = new ConfigLoader(configPath);
        assertNull(configLoader.getProperty("missingKey"));
    }
}
