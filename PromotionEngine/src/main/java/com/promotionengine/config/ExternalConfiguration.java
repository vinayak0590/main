package main.java.com.promotionengine.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.io.InputStream;

public class ExternalConfiguration {

	private static class HelperHolder {
        protected static final ExternalConfiguration externalConfig = new ExternalConfiguration();
    }

    private ConfigProperties configProperties;

    private ExternalConfiguration() {
        initConfigProperties();
    }

    public static ExternalConfiguration getInstance() {
        return HelperHolder.externalConfig;
    }
    
    public ConfigProperties getConfigProperties() {
		return configProperties;
	}

	private void initConfigProperties() {
        try {
            
        	ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.findAndRegisterModules();
            
            InputStream inputStream = ExternalConfiguration.class.getClassLoader().getResourceAsStream("appconfig.yaml");
            this.configProperties = mapper.readValue(inputStream, ConfigProperties.class);
            
        } catch (IOException exception) {
            throw new IllegalStateException("Exception while initializing config property");
        }
    }
}
