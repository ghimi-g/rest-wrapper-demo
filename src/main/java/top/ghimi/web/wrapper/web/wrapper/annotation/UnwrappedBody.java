package top.ghimi.web.wrapper.web.wrapper.annotation;

import top.ghimi.web.wrapper.web.wrapper.response.ResultWrapper;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 标注方法返回不被 {@link  ResultWrapper}  封装
 * @author ghimi
 * @date 2022/10/12
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface UnwrappedBody {
}
