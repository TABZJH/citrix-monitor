package zjh.one.ntlm;

import zjh.one.MonitorConfig;
import org.apache.http.auth.params.AuthPNames;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.olingo.client.core.http.NTLMAuthHttpClientFactory;
import org.apache.olingo.commons.api.http.HttpMethod;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义HttpClientFactory
 */
public class NtlmHttpClientFactory extends NTLMAuthHttpClientFactory {

    public NtlmHttpClientFactory(MonitorConfig monitorConfig) {
        super(monitorConfig.getUserName(), monitorConfig.getPassWord(), null, monitorConfig.getDomain());
    }

    @Override
    public DefaultHttpClient create(HttpMethod method, URI uri) {
        DefaultHttpClient defaultHttpClient = super.create(method, uri);
        // 连接时间
        defaultHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
        // 数据传输时间
        defaultHttpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
        // 设置NTLM认证，不设置无法进行认证
        List<String> arrayList = new ArrayList<String>() {{
            add(AuthPolicy.NTLM);
        }};
        defaultHttpClient.getParams().setParameter(AuthPNames.TARGET_AUTH_PREF, arrayList);
        return defaultHttpClient;
    }
}

