package com.aries.team.util;

import com.aries.extension.util.LogUtil;
import com.aries.team.entity.Message;
import com.aries.team.entity.Prop;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * Created by minsoo.jun on 2018-10-24.
 */
public class Client {
    /**
     * Default connection time out value
     */
    private final int CONNECTION_TIME_OUT = 3000;

    /**
     * HipchatMessage instance
     */
    private Message message;

    /**
     * Default constructor
     *
     * @param message HipchatMessage object
     */
    public Client(Message message) {
        this.message = message;
    }

    /**
     * Push message to slack using simple URLConnection
     *
     * @return Return either "ok" if message was sent, or null if message was not sent or an exception occured.
     */
    public String push() {

        HttpURLConnection connection = null;
        try {
            Prop prop = this.message.getProp();
            URL url = new URL(prop.getUrl());
            if ("WARNING".equals(message.getEventLevel())){
                url = new URL(prop.getUrl());
            }else if("FATAL".equals(message.getEventLevel())){
                url = new URL(prop.getUrl_fatal());
            }

            if ("on".equals(prop.getIsProxy())) {
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(prop.getProxyHost(), Integer.parseInt(prop.getProxyPort())));
                connection = (HttpURLConnection) url.openConnection(proxy);
            } else {
                connection = (HttpURLConnection) url.openConnection();
            }

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(CONNECTION_TIME_OUT);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            String payload = message.getText();
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(payload);

            out.flush();
            out.close();

            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null)
                response.append(line + "\n");

            reader.close();
            return response.toString();
        } catch (Exception ex) {
            LogUtil.error(ex.getMessage());
            return null;
        } finally {
            if (connection != null)
                connection.disconnect();
        }
    }

}
