package top.ghimi.web.wrapper.dal.dataobject;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @author ghimi
 * @date 2022/10/11
 */
@Data
@Table(name = "tb_role")
public class RoleDO {
    private Long id;
    private String name;
    private String status;
    private Date createTime;
    private Date updateTime;
}
