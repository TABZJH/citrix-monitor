package zjh.one.serialization.type.handle;

import org.apache.olingo.client.api.domain.ClientValue;
import zjh.one.monitor.data.ODataObject;

/**
 * class java.lang.String
 * class java.lang.Integer
 * class java.lang.Boolean
 * class java.lang.Long
 * class java.util.Date
 * @author zhoujianghui
 */
public abstract class Handler {

    /**
     * 类型
     *
     * @return
     */
    public abstract String getType();

    /**
     * 处理
     *
     * @param t
     * @param clientValue
     * @param name
     */
    public abstract <T> void handle(T t, ClientValue clientValue, String name);

}
