package zjh.one;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhoujianghui
 */
@Data
@Builder
public class MonitorConfig {

    private String base;

    private String userName;

    private String passWord;

    private String domain;

    public MonitorConfig(String base, String userName, String passWord, String domain) {
        this.base = base;
        this.userName = userName;
        this.passWord = passWord;
        this.domain = domain;
    }
}
