package adapter.jennifer.team.entity;

/**
 * Created by minsoo.jun on 2018-10-24.
 */
public class Prop {
    private String url;
    private String url_fatal;
    private String isProxy;
    private String proxyHost;
    private String proxyPort;

    public String getUrl_fatal() {
        return url_fatal;
    }

    public void setUrl_fatal(String url_fatal) {
        this.url_fatal = url_fatal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsProxy() {
        return isProxy;
    }

    public void setIsProxy(String isProxy) {
        this.isProxy = isProxy;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }
}
