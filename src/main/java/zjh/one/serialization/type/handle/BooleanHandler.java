package zjh.one.serialization.type.handle;

import org.apache.olingo.client.api.domain.ClientValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * String 类型
 *
 * @author zhoujianghui
 */
public class BooleanHandler extends Handler {

    @Override
    public String getType() {
        return Boolean.class.toString();
    }

    @Override
    public <T> void handle(T t, ClientValue clientValue, String name) {
        try {
            // 调用setter方法设置属性值
            Method m = t.getClass().getMethod("set" + name, Boolean.class);
            m.invoke(t, Boolean.valueOf(clientValue.asPrimitive().toString()));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
