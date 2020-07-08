package zjh.one;

import zjh.one.monitor.CatalogsMonitor;
import zjh.one.ntlm.NtlmHttpClientFactory;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.EdmMetadataRequest;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.format.ContentType;

/**
 * 监控工厂
 *
 * @author zhoujianghui
 */
public class MonitorFactory {

    public final ODataClient client;
    private final Edm edm;

    /**
     * 配置信息
     */
    private MonitorConfig monitorConfig;

    public MonitorFactory(MonitorConfig monitorConfig) {
        this.monitorConfig = monitorConfig;
        /**
         * httpClient请求工厂
         */
        NtlmHttpClientFactory httpClientFactory = new NtlmHttpClientFactory(monitorConfig);

        client = ODataClientFactory.getClient();
        client.getConfiguration().setHttpClientFactory(httpClientFactory);
        client.getConfiguration().setDefaultPubFormat(ContentType.APPLICATION_ATOM_XML);

        EdmMetadataRequest metadataRequest = client.getRetrieveRequestFactory().getMetadataRequest(monitorConfig.getBase());
        edm = metadataRequest.execute().getBody();
    }

    /**
     * 获取计算机目录监控对象
     *
     * @return
     */
    public CatalogsMonitor catalogsMonitor() {
        return new CatalogsMonitor(client, monitorConfig.getBase());
    }


}
