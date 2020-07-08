package zjh.one.monitor;

import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import zjh.one.monitor.data.catalogs.Catalog;
import zjh.one.serialization.ClientEntitySerialize;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author zhoujianghui
 */
public class CatalogsMonitor extends BaseMonitor {

    private static final String ID = "Catalogs";

    public CatalogsMonitor(ODataClient client, String base) {
        super(client, base);
    }

    @Override
    public Catalog convert(ClientEntity entity) {
        if (entity == null) {
            return null;
        }
        return ClientEntitySerialize.serialize(entity, Catalog.class);
    }

    @Override
    public String getId() {
        return ID;
    }

    /**
     * 获取实体列表
     *
     * @return
     */
    public List<Catalog> list() {
        ClientEntitySet entitySet = getEntitySet(getId());
        if (entitySet == null) {
            return null;
        }
        return entitySet.getEntities().stream().map(this::convert).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
