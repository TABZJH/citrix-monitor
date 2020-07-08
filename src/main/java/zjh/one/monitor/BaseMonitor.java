package zjh.one.monitor;

import zjh.one.monitor.data.catalogs.Catalog;
import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;

/**
 * @author zhoujianghui
 */
public abstract class BaseMonitor {
    private String baseUrl;
    private ODataClient client;

    public BaseMonitor(ODataClient client, String base) {
        this.client = client;
        this.baseUrl = base;
    }

    /**
     * 获取ID
     *
     * @return
     */
    public abstract String getId();

    /**
     * 转换实体
     *
     * @param entity
     * @return
     */
    public abstract Catalog convert(ClientEntity entity);

    /**
     * 获取请求客户端
     *
     * @return
     */
    public ODataClient getClient() {
        return client;
    }

    /**
     * 获取实体集合
     *
     * @param segment
     * @return
     */
    public ClientEntitySet getEntitySet(String segment) {
        ODataEntitySetRequest<ClientEntitySet> request = getClient()
                .getRetrieveRequestFactory()
                .getEntitySetRequest(getClient()
                        .newURIBuilder(baseUrl)
                        .appendEntitySetSegment(segment)
                        .build());
        ODataRetrieveResponse<ClientEntitySet> response = request.execute();
        return response.getBody();
    }
}
