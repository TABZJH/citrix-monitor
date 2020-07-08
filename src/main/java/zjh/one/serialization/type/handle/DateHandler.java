package zjh.one.serialization.type.handle;

import org.apache.olingo.client.api.domain.ClientValue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * String 类型
 *
 * @author zhoujianghui
 */
public class DateHandler extends Handler {
    private static final String GENERALIZED_TIME_STYLE = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(GENERALIZED_TIME_STYLE);

    public static void main(String[] args) throws ParseException {
        String generalizedTime = "2020-06-03T08:34:52.927";
        String[] times = generalizedTime.split("\\.");
        LocalDateTime dateTime = LocalDateTime.parse(times[0], FORMATTER);
        Date date = Date.from(dateTime.toInstant(ZoneOffset.UTC));
        System.out.println(date);
    }

    @Override
    public String getType() {
        return Date.class.toString();
    }

    @Override
    public <T> void handle(T t, ClientValue clientValue, String name) {
        try {
            // 调用setter方法设置属性值
            Method m = t.getClass().getMethod("set" + name, Date.class);
            m.invoke(t, getDateFromGeneralizedTime(clientValue.asPrimitive().toValue().toString()));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Date getDateFromGeneralizedTime(String generalizedTime) {
        String[] times = generalizedTime.split("\\.");
        LocalDateTime dateTime = LocalDateTime.parse(times[0], FORMATTER);
        return Date.from(dateTime.toInstant(ZoneOffset.UTC));
    }
}
