package zjh.one.monitor.data.catalogs;

import lombok.Data;
import lombok.NoArgsConstructor;
import zjh.one.monitor.data.ODataObject;

import java.util.Date;

/**
 * @author zhoujianghui
 */
@Data
@NoArgsConstructor
public class Catalog extends ODataObject {

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
