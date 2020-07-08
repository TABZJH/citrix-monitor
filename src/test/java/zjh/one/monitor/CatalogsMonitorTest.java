package zjh.one.monitor;

import zjh.one.MonitorTest;
import org.junit.Before;
import org.junit.Test;

public class CatalogsMonitorTest extends MonitorTest {
    CatalogsMonitor monitor;

    @Before
    public void setUp() throws Exception {
        monitor = factory.catalogsMonitor();
    }

    @Test
    public void list() {
        monitor.list();
    }
}