package top.ghimi.web.wrapper.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author ghimi
 * @date 2022/10/11
 */
@MapperScan("cn.lg14.pg3.webgame.**.dal.mapper")
@Configuration
public class MybatisConfiguration {
}
