package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config/browserstackUrl.properties")
public interface BrowserstackUrl extends Config{
    String url();
}
