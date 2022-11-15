package top.ghimi.web.wrapper.web.rest;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Validator;
import top.ghimi.web.wrapper.dal.dataobject.UserDO;
import top.ghimi.web.wrapper.dal.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ghimi
 * @date 2022/10/11
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    public void login(@RequestParam String username, @RequestParam String password) {
        Example example = new Example(UserDO.class);
        if (Validator.isEmail(username)) {
            example.createCriteria().andEqualTo("email", username);
        } else if (Validator.isMobile(username)) {
            example.createCriteria().andEqualTo("phone", username);
        } else {
            example.createCriteria().andEqualTo("name", username);
        }
        List<UserDO> users = userMapper.selectByExample(example);
        if (users.size() != 1) {
            String join = users.stream().map(UserDO::getId).map(String::valueOf)
                .collect(Collectors.joining(","));
            log.warn("found multiple user for [{}], ids [{}]", username, join);
        }
        users.stream().findFirst().ifPresent(user -> {
            if (passwordEncoder.matches(password, user.getPassword())) {
                StpUtil.login(user.getId());
            }
        });
    }

    /**
     * 查看登录状态
     *
     * @return login status 
     */
    @RequestMapping("status")
    public Boolean status() {
        return StpUtil.isLogin();
    }

    /**
     *
     * @return
     */
    @RequestMapping("result")
    public String result() {
        return "success";
    }
}
