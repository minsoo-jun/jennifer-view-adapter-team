package adapter.jennifer.team.util;

import adapter.jennifer.team.entity.Prop;
import com.jennifersoft.view.adapter.util.LogUtil;
import com.jennifersoft.view.config.ConfigValue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Load adapter configuration from configuration file
 */
public class ConfUtil {

    /**
     * Properties instances to load the configuration
     */
    private static Properties properties = null;

    /**
     * Initialize the Properties object and load the configuration from the configuration file
     * The configuration file name and path must be set in <b>VIEW_SERVER_HOME/conf/server_view.conf<b> using the
     * <b>adapter_config_path</b> option
     */
    public static void load() {
        properties = new Properties();
        FileInputStream in = null;
        String path = ConfigValue.adapter_config_path;
        if (isEmpty(path)) {
            path = "/usr/local/jennifer/jennifer5/server.view/ext/team.adapter/adapter.properties";
        }
        try {
            if (path != null) {
                in = new FileInputStream(path);
                properties.load(in);
            }
        } catch (IOException io) {
            LogUtil.error("Failed to load configuration file: " + io.toString());
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
    public static Prop getTeamProperties() {
        Prop prop = new Prop();
        prop.setUrl(getValue("url"));
        prop.setUrl_fatal(getValue("url_fatal"));
        prop.setIsProxy(getValue("proxy_on"));
        prop.setProxyHost(getValue("proxy_host"));
        prop.setProxyPort(getValue("proxy_port"));
        LogUtil.error("getTeamProperties : setted");
        return prop;
    }
}
