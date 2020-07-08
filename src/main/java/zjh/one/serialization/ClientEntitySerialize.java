package zjh.one.serialization;

import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientValue;
import zjh.one.monitor.data.ODataObject;
import zjh.one.serialization.type.handle.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @author zhoujianghui
 */
public class ClientEntitySerialize {

    private static HashMap<String, Handler> handlerMap = new HashMap<>(5);

    static {
        Handler handler = new StringHandler();
        handlerMap.put(handler.getType(), handler);
        handler = new IntegerHandler();
        handlerMap.put(handler.getType(), handler);
        handler = new BooleanHandler();
        handlerMap.put(handler.getType(), handler);
        handler = new LongHandler();
        handlerMap.put(handler.getType(), handler);
        handler = new DateHandler();
        handlerMap.put(handler.getType(), handler);
    }

    public static <T extends ODataObject> T serialize(ClientEntity entity, Class<T> clazz) {
        T t;
        try {
            t = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
        t.setId(entity.getId());
        HashMap<String, ClientValue> property = new HashMap<>(entity.getProperties().size());
        entity.getProperties().forEach(clientProperty -> property.put(clientProperty.getName().toLowerCase(), clientProperty.getValue()));

        for (Field field : t.getClass().getDeclaredFields()) {
            ClientValue clientValue = property.get(field.getName().toLowerCase());
            if (clientValue == null) {
                continue;
            }
            setProperty(t, field, clientValue);
        }
        return t;
    }

    private static <T extends ODataObject> void setProperty(T t, Field field, ClientValue clientValue) {
        field.setAccessible(true);
        // 获取属性的名字
        String name = field.getName();
        // 将属性的首字符大写，方便构造get，set方法
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        // 获取属性的类型
        String type = field.getGenericType().toString();
        Handler handler = handlerMap.get(type);
        if (handler != null) {
            handler.handle(t, clientValue, name);
        }
    }
}
