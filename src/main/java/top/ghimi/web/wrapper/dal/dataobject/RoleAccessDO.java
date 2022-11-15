package top.ghimi.web.wrapper.dal.dataobject;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author ghimi
 * @date 2022/10/11
 */
@Data
@Table(name = "tb_role_access")
public class RoleAccessDO {
    private Long id;
    private Long roleId;
    private Long accessId;
    private Date createTime;
}
