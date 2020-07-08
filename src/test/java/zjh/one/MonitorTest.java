package zjh.one;

import org.junit.Before;

public class MonitorTest {

    public MonitorFactory factory;

    @Before
    public void baseBefore() throws Exception {
        factory = new MonitorFactory(MonitorConfig.builder()
                .base("http://xendesktop.appdev.centerm.com/Citrix/Monitor/OData/v1/Data")
                .domain("appdev.centerm.com")
                .passWord("zzz")
                .userName("zzz")
                .build());
    }
}
