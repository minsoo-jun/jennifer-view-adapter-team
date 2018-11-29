package com.aries.team.util;

import com.aries.team.entity.Prop;
import com.aries.extension.util.PropertyUtil;
import java.util.Properties;


/**
 * Load adapter configuration from configuration file
 */
public class ConfUtil {

    /**
     * Properties instances to load the configuration
     */
    private static Properties properties = null;

    private static final String ADAPTER_ID = "team";

    private static final String TEAM_URL = "url";

    private static final String TEAM_FATAL_URL = "url_fatal";

    private static final String PROXY_ON = "proxy_on" ;

    private static final String PROXY_HOST ="proxy_host";

    private static final String PROXY_PORT ="proxy_port";

    /**
     * Get a configuration value using the provided key
     * @param key configuration key. Set this key value in the adapter configuration menu in JENNIFER client.
     * @return String configuration value
     */
    public static String getValue(String key) {
        return PropertyUtil.getValue(ADAPTER_ID, key);
    }


    /**
     *
     * @return
     */
    public static Prop getTeamProperties() {
        Prop prop = new Prop();
        prop.setUrl(getValue(TEAM_URL));
        prop.setUrl_fatal(getValue(TEAM_FATAL_URL));
        prop.setIsProxy(getValue(PROXY_ON));
        prop.setProxyHost(getValue(PROXY_HOST));
        prop.setProxyPort(getValue(PROXY_PORT));
        return prop;
    }
}
