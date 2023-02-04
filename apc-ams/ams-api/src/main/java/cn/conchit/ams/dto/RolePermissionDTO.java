package cn.conchit.ams.dto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
/**
 * @author： hetao
 */
@Data
@Accessors(chain = true)
public class RolePermissionDTO {
    private Long roleId;
    private List<Long> permissionIds;
    private Long menuId;
}
