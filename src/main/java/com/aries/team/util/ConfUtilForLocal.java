package com.aries.team.util;

import com.aries.team.entity.Prop;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Load adapter configuration from configuration file
 */
public class ConfUtilForLocal {

    /**
     * Properties instances to load the configuration
     */
    private static Properties properties = null;

    private static final String TEAM_URL = "url";

    private static final String TEAM_FATAL_URL = "url_fatal";

    private static final String PROXY_ON = "proxy_on" ;

    private static final String PROXY_HOST ="proxy_host";

    private static final String PROXY_PORT ="proxy_port";
    /**
     * Initialize the Properties object and load the configuration from the configuration file
     * The configuration file name and path must be set in <b>VIEW_SERVER_HOME/conf/server_view.conf<b> using the
     * <b>adapter_config_path</b> option
     */
    public static void load() {
        properties = new Properties();
        FileInputStream in = null;
        String path = "/usr/local/jennifer/jennifer5/server.view/ext/team.adapter/adapter.properties";
        try {
            if (path != null) {
                in = new FileInputStream(path);
                properties.load(in);
            }
        } catch (IOException io) {
            System.out.println(io.getStackTrace());
        }
    }

    /**
     * Get a configuration value using the provided key
     *
     * @param key configuration key
     * @return String configuration value
     */
    public static String getValue(String key) {
        if (properties == null)
            load();
        return properties.getProperty(key);
    }

    /**
     * Getter for the properties object.
     *
     * @return the properties object
     */
    public static Properties getProperties() {
        if (properties == null)
            load();
        return properties;
    }

    /**
     *
     * @return
     */
    public static Prop getLocalTeamProperties() {
        Prop prop = new Prop();
        prop.setUrl(getValue(TEAM_URL));
        prop.setUrl_fatal(getValue(TEAM_FATAL_URL));
        prop.setIsProxy(getValue(PROXY_ON));
        prop.setProxyHost(getValue(PROXY_HOST));
        prop.setProxyPort(getValue(PROXY_PORT));
        return prop;
    }
}
