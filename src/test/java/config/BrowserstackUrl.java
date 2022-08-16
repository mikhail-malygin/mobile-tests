package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:browserstackUrl.properties")
public interface BrowserstackUrl extends Config{
    String url();
}
