package top.ghimi.web.wrapper.dal.dataobject;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author ghimi
 * @date 2022/10/11
 */
@Data
@Table(name = "tb_user_role")
public class UserRoleDO {
    private Long id;
    private Long userId;
    private Long roleId;
    private Date createTime;
}
