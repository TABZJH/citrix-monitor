package zjh.one;

import org.junit.Before;

public class MonitorTest {

    public MonitorFactory factory;

    @Before
    public void baseBefore() throws Exception {
        factory = new MonitorFactory(MonitorConfig.builder()
                .base("http://xendesktop.zjh.one/Citrix/Monitor/OData/v1/Data")
                .domain("zjh.one")
                .passWord("##########")
                .userName("zzz")
                .build());
    }
}
