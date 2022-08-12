package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${host}.properties"
})
public interface MobileConfig extends Config{

    @Key("device")
    @Config.DefaultValue("Galaxy S22")
    String getDevice();

    @Key("osVersion")
    @Config.DefaultValue("12.0")
    String getOsVersion();
}
