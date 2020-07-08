package zjh.one.monitor.data.catalogs;

import zjh.one.monitor.data.ODataObject;
import lombok.Builder;
import lombok.Data;

import java.net.URI;
import java.util.Date;

/**
 * @author zhoujianghui
 */
@Data
@Builder
public class Catalog extends ODataObject {

    private URI id;

    private String guid;

    private String name;

    private Integer lifecycleState;

    private Integer provisioningType;

    private Integer persistentUserChanges;

    private Boolean isMachinePhysical;

    private Integer allocationType;

    private Integer sessionSupport;

    private String provisioningSchemeId;

    private Date createdDate;

    private Date modifiedDate;
}
