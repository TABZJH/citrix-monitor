package zjh.one.monitor;

import org.junit.Before;
import org.junit.Test;
import zjh.one.MonitorTest;

public class CatalogsMonitorTest extends MonitorTest {
    CatalogsMonitor monitor;

    @Before
    public void setUp() throws Exception {
        monitor = factory.catalogsMonitor();
    }

    @Test
    public void list() {
        monitor.list().forEach(System.out::println);
    }
}