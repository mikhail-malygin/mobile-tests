package config;

import org.aeonbits.owner.Config;



@Config.Sources({
        "classpath:config/${deviceHost}.properties"
})
public interface EmulatorDeviceConfig extends Config {

    String deviceName();
    String platformVersion();
    String appiumUrl();

}
